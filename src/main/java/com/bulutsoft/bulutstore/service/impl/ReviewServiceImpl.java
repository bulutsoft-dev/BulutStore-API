package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.Review;
import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.mapper.ReviewMapper;
import com.bulutsoft.bulutstore.repos.ReviewRepository;
import com.bulutsoft.bulutstore.repos.AppRepository;
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
    private final AppRepository appRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, AppRepository appRepository, UserRepository userRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.appRepository = appRepository;
        this.userRepository = userRepository;
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
        appRepository.findById(request.getAppId()).ifPresent(review::setApp);
        userRepository.findById(request.getUserId()).ifPresent(review::setUser);
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    @Override
    @Transactional
    public ReviewResponse updateReview(Long id, ReviewRequest request) {
        Review review = reviewMapper.toEntity(request);
        review.setId(id);
        appRepository.findById(request.getAppId()).ifPresent(review::setApp);
        userRepository.findById(request.getUserId()).ifPresent(review::setUser);
        return reviewMapper.toResponse(reviewRepository.save(review));
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewResponse> getReviewsByApp(Long appId) {
        Optional<App> app = appRepository.findById(appId);
        return app.map(a -> reviewMapper.toResponseList(reviewRepository.findByApp(a))).orElse(List.of());
    }

    @Override
    public List<ReviewResponse> getReviewsByUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(u -> reviewMapper.toResponseList(reviewRepository.findByUser(u))).orElse(List.of());
    }

    @Override
    public Optional<ReviewResponse> getReviewByAppAndUser(Long appId, Long userId) {
        Optional<App> app = appRepository.findById(appId);
        Optional<User> user = userRepository.findById(userId);
        if (app.isPresent() && user.isPresent()) {
            return reviewRepository.findByAppAndUser(app.get(), user.get()).map(reviewMapper::toResponse);
        }
        return Optional.empty();
    }
}
