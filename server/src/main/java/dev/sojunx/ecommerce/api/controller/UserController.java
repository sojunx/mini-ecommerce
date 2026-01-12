package dev.sojunx.ecommerce.api.controller;

import dev.sojunx.ecommerce.api.dto.response.ApiResponse;
import dev.sojunx.ecommerce.api.service.core.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        var user = userService.getUserByEmail(userDetails.getUsername());

        var res = ApiResponse.success("Authenticated", user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    ResponseEntity<?> getUsers() {

        return new ResponseEntity<>(Map.of("message", "authenticated"), HttpStatus.NOT_IMPLEMENTED);
    }
}
