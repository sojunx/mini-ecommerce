package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.mapper.UserMapper;
import dev.sojunx.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping("/me")
    ResponseEntity<ApiResponse> getUserById(@AuthenticationPrincipal UserDetails userDetails) {
        var email = userDetails.getUsername();
        var user = service.getUserByEmail(email);

        var res = ApiResponse.success("Success", mapper.toDto(user));
        return ResponseEntity.ok(res);
    }
}
