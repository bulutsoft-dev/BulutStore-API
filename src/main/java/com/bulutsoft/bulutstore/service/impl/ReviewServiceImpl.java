package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.dto.ReviewDto;
import com.bulutsoft.bulutstore.entity.Review;
import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.mapper.ReviewMapper;
import com.bulutsoft.bulutstore.repos.ReviewRepository;
import com.bulutsoft.bulutstore.repos.AppRepository;
import com.bulutsoft.bulutstore.repos.UserRepository;
import com.bulutsoft.bulutstore.service.ReviewService;
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
    public List<ReviewDto> getAllReviews() {
        return reviewMapper.toDtoList(reviewRepository.findAll());
    }

    @Override
    public Optional<ReviewDto> getReviewById(Long id) {
        return reviewRepository.findById(id).map(reviewMapper::toDto);
    }

    @Override
    @Transactional
    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        if (reviewDto.getApp() != null && reviewDto.getApp().getId() != null) {
            appRepository.findById(reviewDto.getApp().getId()).ifPresent(review::setApp);
        }
        if (reviewDto.getUser() != null && reviewDto.getUser().getId() != null) {
            userRepository.findById(reviewDto.getUser().getId()).ifPresent(review::setUser);
        }
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Override
    @Transactional
    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
        Review review = reviewMapper.toEntity(reviewDto);
        review.setId(id);
        if (reviewDto.getApp() != null && reviewDto.getApp().getId() != null) {
            appRepository.findById(reviewDto.getApp().getId()).ifPresent(review::setApp);
        }
        if (reviewDto.getUser() != null && reviewDto.getUser().getId() != null) {
            userRepository.findById(reviewDto.getUser().getId()).ifPresent(review::setUser);
        }
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<ReviewDto> getReviewsByApp(Long appId) {
        Optional<App> app = appRepository.findById(appId);
        return app.map(a -> reviewMapper.toDtoList(reviewRepository.findByApp(a))).orElse(List.of());
    }

    @Override
    public List<ReviewDto> getReviewsByUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(u -> reviewMapper.toDtoList(reviewRepository.findByUser(u))).orElse(List.of());
    }

    @Override
    public Optional<ReviewDto> getReviewByAppAndUser(Long appId, Long userId) {
        Optional<App> app = appRepository.findById(appId);
        Optional<User> user = userRepository.findById(userId);
        if (app.isPresent() && user.isPresent()) {
            return reviewRepository.findByAppAndUser(app.get(), user.get()).map(reviewMapper::toDto);
        }
        return Optional.empty();
    }
}
