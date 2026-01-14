package dev.sojunx.ecommerce.api.controller;

import dev.sojunx.ecommerce.api.application.dto.core.ApiResponse;
import dev.sojunx.ecommerce.api.application.mapper.UserMapper;
import dev.sojunx.ecommerce.api.application.service.UserService;
import dev.sojunx.ecommerce.api.domain.entities.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        var user = mapper.toDto(userDetails.user());

        var res = ApiResponse.success("Fetched user successfully", user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    ResponseEntity<?> getUsers() {
        var users = userService.findAll();

        return new ResponseEntity<>(ApiResponse.success("Fetched users list successfully", users), HttpStatus.OK);
    }
}
