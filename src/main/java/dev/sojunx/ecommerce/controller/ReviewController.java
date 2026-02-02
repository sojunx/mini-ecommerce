package dev.sojunx.ecommerce.controller;

import dev.sojunx.ecommerce.config.CustomUserDetails;
import dev.sojunx.ecommerce.dto.ApiResponse;
import dev.sojunx.ecommerce.dto.request.DeleteReviewRequest;
import dev.sojunx.ecommerce.dto.request.ReviewRequest;
import dev.sojunx.ecommerce.dto.request.UpdateReviewRequest;
import dev.sojunx.ecommerce.mapper.ReviewMapper;
import dev.sojunx.ecommerce.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;
    private final ReviewMapper mapper;

    @GetMapping("/{product_id}")
    ResponseEntity<ApiResponse> getAllReviews(
            @PathVariable UUID product_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "false") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        var reviews = service.findAll(product_id, pageable);

        var res = ApiResponse.success("Success", reviews.map(mapper::toDto));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{product_id}/stats")
    ResponseEntity<ApiResponse> getStats(@PathVariable UUID product_id) {
        var stats = service.getReviewStats(product_id);

        var res = ApiResponse.success("Success", stats);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    ResponseEntity<ApiResponse> createReview(@RequestBody ReviewRequest request, @AuthenticationPrincipal CustomUserDetails userDetails) {
        var review = service.createReview(request, userDetails.user());

        var res = ApiResponse.success("Success", mapper.toDto(review));
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse> updateReview(@PathVariable UUID id, @RequestBody UpdateReviewRequest request) {
        var review = service.updateReview(id, request);

        var res = ApiResponse.success("Success", mapper.toDto(review));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteReview(@PathVariable UUID id, @RequestBody DeleteReviewRequest request) {
        service.deleteReview(id, request);

        var res = ApiResponse.success("Success");
        return ResponseEntity.ok(res);
    }
}
