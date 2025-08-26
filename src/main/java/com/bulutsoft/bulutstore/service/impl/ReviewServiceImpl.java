package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.Review;
import com.bulutsoft.bulutstore.mapper.ReviewMapper;
import com.bulutsoft.bulutstore.repos.ReviewRepository;
import com.bulutsoft.bulutstore.service.ReviewService;
import com.bulutsoft.bulutstore.request.ReviewRequest;
import com.bulutsoft.bulutstore.response.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Yorum işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 * DTO <-> Entity dönüşümleri ReviewMapper ile yapılır.
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        return reviewMapper.toResponseList(reviewRepository.findAll());
    }

    @Override
    public Optional<ReviewResponse> getReviewById(Long id) {
        return reviewRepository.findById(id).map(reviewMapper::toResponse);
    }

    @Override
    @Transactional
    public ReviewResponse createReview(ReviewRequest request) {
        Review review = reviewMapper.toEntity(request);
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    @Override
    @Transactional
    public ReviewResponse updateReview(Long id, ReviewRequest request) {
        Review review = reviewMapper.toEntity(request);
        review.setId(id);
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
