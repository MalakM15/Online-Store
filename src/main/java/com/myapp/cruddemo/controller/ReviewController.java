package com.myapp.cruddemo.controller;

import com.myapp.cruddemo.service.ReviewService;
import com.myapp.cruddemo.dto.ReviewRequestDto;
import com.myapp.cruddemo.dto.ReviewResponseDto;
import com.myapp.cruddemo.mapper.ReviewMapper;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import java.util.List;


@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService,  ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    // GET /api/reviews
    @GetMapping("/products/{productId}")
    public List<ReviewResponseDto> getReviewsByProductId (@PathVariable int productId) {
        return reviewService.getReviewsByProductId(productId).stream()
            .map(reviewMapper::entityToResponseDto)
            .toList();
    }

    @PostMapping("/products/{productId}")
    public ReviewResponseDto createReview (@PathVariable int productId, @RequestBody ReviewRequestDto reviewRequestDto, Authentication authentication){
        
        return reviewMapper.entityToResponseDto(reviewService.createReview(productId, authentication.getName(), reviewRequestDto.getComment()));

    }

}
