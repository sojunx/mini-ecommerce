package dev.sojunx.ecommerce.authorize.api.controller;

import dev.sojunx.ecommerce.authorize.api.domain.entities.User;
import dev.sojunx.ecommerce.authorize.api.dto.request.SignInRequest;
import dev.sojunx.ecommerce.authorize.api.dto.request.SignUpRequest;
import dev.sojunx.ecommerce.authorize.api.dto.response.ApiResponse;
import dev.sojunx.ecommerce.authorize.api.dto.response.TokenResponse;
import dev.sojunx.ecommerce.authorize.api.service.auth.CookieService;
import dev.sojunx.ecommerce.authorize.api.service.auth.CustomUserDetailsService;
import dev.sojunx.ecommerce.authorize.api.service.auth.JwtService;
import dev.sojunx.ecommerce.authorize.api.service.core.TokenService;
import dev.sojunx.ecommerce.authorize.api.service.core.UserService;
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
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final TokenService tokenService;
    private final CookieService cookieService;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    @PostMapping("/refresh")
    ResponseEntity<?> refresh(HttpServletRequest request, HttpServletResponse response) {
        // Extract refresh token from cookie
        var cookie = cookieService.extractCookie(request, "refresh_token");
        if (cookie.isEmpty())
            throw new RuntimeException("Cookie not found");

        // Revoke old token
        var token = tokenService.revoke(cookie.get().getValue());
        if (token == null)
            throw new RuntimeException("Token not found");

        // Get user details
        var user = token.getUser();

        // Generate new token
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Save new token
        tokenService.save(user, refreshToken);

        // Set cookie for refresh token
        cookieService.setCookie(response, "refresh_token", refreshToken, refreshExpiration);

        var res = ApiResponse.success("Refresh successful", new TokenResponse(accessToken));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

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
        var user = (User) userDetailsService.loadUserByUsername(request.email());

        // Generate tokens
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Revoke all tokens and save new token
        // NOTES: This is not a good practice, but for demo purpose only
        // Maybe revoke one token, not all tokens
        tokenService.revokeAll(user);
        tokenService.save(user, refreshToken);

        // Set cookie for refresh token
        cookieService.setCookie(response, "refresh_token", refreshToken, refreshExpiration);

        var res = ApiResponse.success("Sign in successful", new TokenResponse(accessToken));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
