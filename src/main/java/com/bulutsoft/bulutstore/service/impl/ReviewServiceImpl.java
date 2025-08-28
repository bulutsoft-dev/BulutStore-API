package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.Review;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.mapper.ReviewMapper;
import com.bulutsoft.bulutstore.repos.AppRepository;
import com.bulutsoft.bulutstore.repos.ReviewRepository;
import com.bulutsoft.bulutstore.repos.UserRepository;
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
    private final AppRepository appRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper, AppRepository appRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.appRepository = appRepository;
        this.userRepository = userRepository;
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
        App app = appRepository.findById(request.getAppId())
            .orElseThrow(() -> new RuntimeException("App not found"));
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        review.setApp(app);
        review.setUser(user);
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    @Override
    @Transactional
    public ReviewResponse updateReview(Long id, ReviewRequest request) {
        Review existing = reviewRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Review not found"));
        // appId ve userId değişikliğine izin verme
        if (!existing.getApp().getId().equals(request.getAppId()) ||
            !existing.getUser().getId().equals(request.getUserId())) {
            throw new RuntimeException("Cannot change appId or userId of a review");
        }
        existing.setRating(request.getRating());
        existing.setComment(request.getComment());
        return reviewMapper.toResponse(reviewRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found");
        }
        reviewRepository.deleteById(id);
    }
}
