package dev.sojunx.ecommerce.api.controller;

import dev.sojunx.ecommerce.api.application.dto.command.SignInCommand;
import dev.sojunx.ecommerce.api.application.dto.command.SignUpCommand;
import dev.sojunx.ecommerce.api.application.dto.core.ApiResponse;
import dev.sojunx.ecommerce.api.application.service.*;
import dev.sojunx.ecommerce.api.domain.entities.CustomUserDetails;
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

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager manager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final SessionService sessionService;
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
        var token = sessionService.revoke(cookie.get().getValue());
        if (token == null)
            throw new RuntimeException("Token not found");

        // Get user details
        var userDetails = new CustomUserDetails(token.getUser());

        // Generate new token
        var access = jwtService.generateToken(userDetails);
        var refresh = jwtService.generateRefreshToken(userDetails);

        // Save new token
        sessionService.save(userDetails.user(), refresh);

        // Set cookie for refresh token
        cookieService.setCookie(response, "refresh_token", refresh, refreshExpiration);

        var res = ApiResponse.success("Signed out successfully", Map.of("access_token", access));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/sign-out")
    ResponseEntity<?> signout(HttpServletRequest req, HttpServletResponse res) {
        // Revoke refresh token
        var cookie = cookieService.extractCookie(req, "refresh_token");
        cookie.ifPresent(value -> sessionService.revoke(value.getValue()));

        // Clear cookie
        cookieService.clearCookie(res, "refresh_token");

        return new ResponseEntity<>(ApiResponse.success("Signed out successfully"), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    ResponseEntity<?> signup(@RequestBody SignUpCommand command) {
        var user = userService.save(command);

        var res = ApiResponse.success("Signed up successfully", Map.of("user", user));
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    ResponseEntity<?> signin(@RequestBody SignInCommand command, HttpServletResponse response) {
        // Authenticate user
        manager.authenticate(new UsernamePasswordAuthenticationToken(command.email(), command.password()));
        var userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(command.email());

        // Generate tokens
        var access = jwtService.generateToken(userDetails);
        var refresh = jwtService.generateRefreshToken(userDetails);

        sessionService.revokeAll(userDetails.user());
        sessionService.save(userDetails.user(), refresh);

        // Set cookie for refresh token
        cookieService.setCookie(response, "refresh_token", refresh, refreshExpiration);

        var res = ApiResponse.success("Signed in successfully", Map.of("access_token", access));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
