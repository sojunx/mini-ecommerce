package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.dto.request.user.LoginRequest;
import dev.sojunx.ecommerce.dto.request.user.RegisterRequest;
import dev.sojunx.ecommerce.service.AuthService;
import dev.sojunx.ecommerce.utils.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;
    private final CookieUtil cookieUtil;

    @PostMapping("/login")
    ResponseEntity<ApiResponse> login(@RequestBody LoginRequest req, HttpServletResponse httpResponse) {
        var user = service.login(req);
        cookieUtil.setCookie("session_id", user.getId().toString(), httpResponse);

        var res = ApiResponse.success("Logged in successfully");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/register")
    ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest req) {
        service.register(req);

        var res = ApiResponse.success("Registered in successfully");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/logout")
    ResponseEntity<ApiResponse> logout(HttpServletResponse httpResponse) {
        cookieUtil.setCookie("session_id", "", httpResponse);

        var res = ApiResponse.success("Logged out successfully");
        return ResponseEntity.ok(res);
    }
}
