package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.dto.ReviewDto;
import java.util.List;
import java.util.Optional;

/**
 * Yorum işlemleri için servis arayüzü.
 * Temel CRUD ve uygulama/kullanıcıya özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler ReviewDto ile yapılır.
 */
public interface ReviewService {
    List<ReviewDto> getAllReviews();
    Optional<ReviewDto> getReviewById(Long id);
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto updateReview(Long id, ReviewDto reviewDto);
    void deleteReview(Long id);
    List<ReviewDto> getReviewsByApp(Long appId);
    List<ReviewDto> getReviewsByUser(Long userId);
    Optional<ReviewDto> getReviewByAppAndUser(Long appId, Long userId);
}
