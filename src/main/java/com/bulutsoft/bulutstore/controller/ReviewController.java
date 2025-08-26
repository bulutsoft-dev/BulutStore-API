package com.bulutsoft.bulutstore.controller;

import com.bulutsoft.bulutstore.dto.ReviewDto;
import com.bulutsoft.bulutstore.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "Get all reviews")
    @ApiResponse(responseCode = "200", description = "List of reviews")
    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @Operation(summary = "Get review by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Review found"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new review")
    @ApiResponse(responseCode = "201", description = "Review created")
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewDto reviewDto) {
        ReviewDto created = reviewService.createReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Update review")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Review updated"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDto reviewDto) {
        ReviewDto updated = reviewService.updateReview(id, reviewDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete review")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Review deleted"),
        @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}

