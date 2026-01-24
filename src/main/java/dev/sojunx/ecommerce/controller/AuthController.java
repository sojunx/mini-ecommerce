package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.dto.request.LoginRequest;
import dev.sojunx.ecommerce.dto.request.RegisterRequest;
import dev.sojunx.ecommerce.dto.response.AuthDto;
import dev.sojunx.ecommerce.helper.JwtUtil;
import dev.sojunx.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService service;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        var user = service.createUser(request);

        var res = ApiResponse.success("User registered successfully", user);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        var user = service.getUserByEmail(request.getEmail());
        var token = jwtUtil.generateToken(user.getEmail());

        var res = ApiResponse.success(
                "User logged in successfully",
                AuthDto.builder().token(token).user(user).build()
        );
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
