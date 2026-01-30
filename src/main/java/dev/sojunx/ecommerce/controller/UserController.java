package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.dto.request.UserLoginRequest;
import dev.sojunx.ecommerce.dto.request.UserRegisterRequest;
import dev.sojunx.ecommerce.mapper.UserMapper;
import dev.sojunx.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
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
    ResponseEntity<ApiResponse> login(@RequestBody UserLoginRequest request, HttpServletResponse httpResponse) {
        var user = service.authenticate(request);

        var cookie = ResponseCookie.from("session_id", user.getId().toString()).path("/").httpOnly(true);
        httpResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.build().toString());

        var res = ApiResponse.success("Success", mapper.toDto(user));
        return ResponseEntity.ok(res);
    }

    @PostMapping("/logout")
    ResponseEntity<ApiResponse> logout(@AuthenticationPrincipal UserDetails userDetails, HttpServletResponse httpResponse) {
        service.logout(userDetails.getUsername());

        var cookie = ResponseCookie.from("session_id", "");
        httpResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.build().toString());

        var res = ApiResponse.success("Success");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/me")
    ResponseEntity<ApiResponse> getUserById(@AuthenticationPrincipal UserDetails userDetails) {
        var email = userDetails.getUsername();
        var user = service.getUserByEmail(email);

        var res = ApiResponse.success("Success", mapper.toDto(user));
        return ResponseEntity.ok(res);
    }
}
