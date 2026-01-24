package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/profile")
    ResponseEntity<ApiResponse> getProfile(Authentication authentication) {
        var user = service.getUserByEmail(authentication.getName());

        var res = ApiResponse.success("User profile fetched successfully", user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
