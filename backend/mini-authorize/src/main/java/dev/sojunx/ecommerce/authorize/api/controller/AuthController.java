package dev.sojunx.ecommerce.authorize.api.controller;

import dev.sojunx.ecommerce.authorize.api.dto.request.SignInRequest;
import dev.sojunx.ecommerce.authorize.api.dto.request.SignUpRequest;
import dev.sojunx.ecommerce.authorize.api.dto.response.ApiResponse;
import dev.sojunx.ecommerce.authorize.api.dto.response.SignInResponse;
import dev.sojunx.ecommerce.authorize.api.model.CustomUserDetails;
import dev.sojunx.ecommerce.authorize.api.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;
    private final TokenService tokenService;
    private final CookieService cookieService;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    @PostMapping("/sign-out")
    ResponseEntity<?> signout(HttpServletRequest request, HttpServletResponse response) {
        // Revoke refresh token
        var cookie = cookieService.extractCookie(request, "refresh_token");
        cookie.ifPresent(value -> tokenService.revoke(value.getValue()));

        // Clear cookie
        cookieService.clearCookie(response, "refresh_token");

        var res = ApiResponse.success("Sign out successful");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        var user = userService.createUser(request);

        var res = ApiResponse.success("Sign up successful", user);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    ResponseEntity<?> signin(@RequestBody SignInRequest request, HttpServletResponse response) {
        // Authenticate user
        var token = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        authenticationManager.authenticate(token);

        // Get user details
        var userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(request.email());
        var user = userDetails.user();

        // Generate tokens
        var accessToken = jwtService.generateToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);

        // Revoke all tokens and save new token
        tokenService.revokeAll(user);
        tokenService.save(user, refreshToken);

        // Set cookie for refresh token
        cookieService.setCookie(response, "refresh_token", refreshToken, refreshExpiration);

        var res = ApiResponse.success("Sign in successful", new SignInResponse(accessToken));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
