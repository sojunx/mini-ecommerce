package dev.sojunx.ecommerce.authorize.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        var email = userDetails.getUsername();

        return new ResponseEntity<>(Map.of("message", "authenticated", "data", email), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    ResponseEntity<?> getUsers() {

        return new ResponseEntity<>(Map.of("message", "authenticated"), HttpStatus.NOT_IMPLEMENTED);
    }
}
