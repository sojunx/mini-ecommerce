package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.dto.request.UserLoginRequest;
import dev.sojunx.ecommerce.dto.request.UserLogoutRequest;
import dev.sojunx.ecommerce.dto.request.UserRegisterRequest;
import dev.sojunx.ecommerce.mapper.UserMapper;
import dev.sojunx.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @PostMapping("/register")
    ResponseEntity<ApiResponse> createUser(@RequestBody UserRegisterRequest request) {
        var user = service.createUser(request);

        var res = ApiResponse.success("Success", mapper.toDto(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("/login")
    ResponseEntity<ApiResponse> login(@RequestBody UserLoginRequest request) {
        var user = service.authenticate(request);

        var res = ApiResponse.success("Success", mapper.toDto(user));
        return ResponseEntity.ok(res);
    }

    @PostMapping("/logout")
    ResponseEntity<ApiResponse> logout(@RequestBody UserLogoutRequest request) {
        service.logout(request);

        var res = ApiResponse.success("Success");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse> getUserById(@PathVariable UUID id) {
        var user = service.getUserById(id);

        var res = ApiResponse.success("Success", mapper.toDto(user));
        return ResponseEntity.ok(res);
    }
}
