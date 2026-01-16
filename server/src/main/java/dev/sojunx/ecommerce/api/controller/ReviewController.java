package dev.sojunx.ecommerce.api.controller;

import dev.sojunx.ecommerce.api.application.dto.command.AddReviewCommand;
import dev.sojunx.ecommerce.api.application.dto.core.ApiResponse;
import dev.sojunx.ecommerce.api.application.service.product.ReviewService;
import dev.sojunx.ecommerce.api.domain.entities.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;

    @GetMapping("/{id}/rating")
    ResponseEntity<?> getProductRating(@PathVariable UUID id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // This is product id
    @GetMapping("/{id}")
    ResponseEntity<?> getProductReviews(@PathVariable UUID id) {
        var reviews = service.findAllByProductId(id);

        var res = ApiResponse.success("Fetched reviews successfully", Map.of("reviews", reviews));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // This is product id
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{id}")
    ResponseEntity<?> addReviewToProduct(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable UUID id,
            @RequestBody AddReviewCommand command
    ) {
        var user = userDetails.user();
        service.addReview(user, id, command);

        var res = ApiResponse.success("Review added successfully");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    // This is review id
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteReviewFromProduct(@PathVariable UUID id) {
        service.deleteReview(id);

        var res = ApiResponse.success("Review deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
