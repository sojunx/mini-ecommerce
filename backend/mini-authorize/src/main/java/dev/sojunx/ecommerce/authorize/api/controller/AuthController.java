package dev.sojunx.ecommerce.authorize.api.controller;

import dev.sojunx.ecommerce.authorize.api.dto.SignInRequest;
import dev.sojunx.ecommerce.authorize.api.dto.SignUpRequest;
import dev.sojunx.ecommerce.authorize.api.service.CustomUserDetailsService;
import dev.sojunx.ecommerce.authorize.api.service.JwtService;
import dev.sojunx.ecommerce.authorize.api.service.UserService;
import lombok.RequiredArgsConstructor;
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
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;

    @PostMapping("/sign-up")
    ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        var user = userService.createUser(request);

        return new ResponseEntity<>(Map.of(
                "message", "Sign up successful",
                "data", user
        ), HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    ResponseEntity<?> signin(@RequestBody SignInRequest request) {
        var token = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        authenticationManager.authenticate(token);

        var userDetails = customUserDetailsService.loadUserByUsername(request.email());
        var accessToken = jwtService.generateToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);

        // TODO: Disable all token in database, then save new token

        // TODO: Generate JWT Token
        return new ResponseEntity<>(Map.of(
                "message", "Sign in successful",
                "access_token", accessToken,
                "refresh_token", refreshToken
        ), HttpStatus.OK);
    }
}
