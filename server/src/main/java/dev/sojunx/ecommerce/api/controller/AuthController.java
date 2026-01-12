package dev.sojunx.ecommerce.api.controller;

import dev.sojunx.ecommerce.api.domain.entities.user.User;
import dev.sojunx.ecommerce.api.dto.command.SignInCommand;
import dev.sojunx.ecommerce.api.dto.command.SignUpCommand;
import dev.sojunx.ecommerce.api.dto.helper.ApiResponse;
import dev.sojunx.ecommerce.api.service.auth.CookieService;
import dev.sojunx.ecommerce.api.service.auth.CustomUserDetailsService;
import dev.sojunx.ecommerce.api.service.auth.JwtService;
import dev.sojunx.ecommerce.api.service.auth.RefreshTokenService;
import dev.sojunx.ecommerce.api.service.core.UserService;
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
    private final RefreshTokenService refreshTokenService;
    private final CookieService cookieService;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    @PostMapping("/refresh")
    ResponseEntity<?> refresh(HttpServletRequest req, HttpServletResponse res) {
        // Extract refresh token from cookie
        var cookie = cookieService.extractCookie(req, "refresh_token");
        if (cookie.isEmpty())
            throw new RuntimeException("Cookie not found");

        // Revoke old token
        var token = refreshTokenService.revoke(cookie.get().getValue());
        if (token == null)
            throw new RuntimeException("Token not found");

        // Get user details
        var user = token.getUser();

        // Generate new token
        var access = jwtService.generateToken(user);
        var refresh = jwtService.generateRefreshToken(user);

        // Save new token
        refreshTokenService.save(user, refresh);

        // Set cookie for refresh token
        cookieService.setCookie(res, "refresh_token", refresh, refreshExpiration);

        return new ResponseEntity<>(
                ApiResponse.success(
                        "Signed out successfully",
                        Map.of("access_token", access)
                ),
                HttpStatus.OK
        );
    }

    @PostMapping("/sign-out")
    ResponseEntity<?> signout(HttpServletRequest req, HttpServletResponse res) {
        // Revoke refresh token
        var cookie = cookieService.extractCookie(req, "refresh_token");
        cookie.ifPresent(value -> refreshTokenService.revoke(value.getValue()));

        // Clear cookie
        cookieService.clearCookie(res, "refresh_token");

        return new ResponseEntity<>(ApiResponse.success("Signed out successfully"), HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    ResponseEntity<?> signup(@RequestBody SignUpCommand command) {
        userService.save(command);

        return new ResponseEntity<>(ApiResponse.success("Signed up successfully"), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    ResponseEntity<?> signin(@RequestBody SignInCommand command, HttpServletResponse res) {
        // Authenticate user
        manager.authenticate(new UsernamePasswordAuthenticationToken(command.email(), command.password()));
        var user = (User) userDetailsService.loadUserByUsername(command.email());

        // Generate tokens
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Revoke all tokens and save new token
        // NOTES: This is not a good practice, but for demo purpose only
        // Maybe revoke one token, not all tokens
        refreshTokenService.revokeAll(user);
        refreshTokenService.save(user, refreshToken);

        // Set cookie for refresh token
        cookieService.setCookie(res, "refresh_token", refreshToken, refreshExpiration);

        return new ResponseEntity<>(
                ApiResponse.success(
                        "Signed in successfully",
                        Map.of("access_token", token)
                ),
                HttpStatus.OK
        );
    }
}
