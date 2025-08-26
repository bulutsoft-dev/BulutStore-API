package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.request.ReviewRequest;
import com.bulutsoft.bulutstore.response.ReviewResponse;

import java.util.List;
import java.util.Optional;

/**
 * Yorum işlemleri için servis arayüzü.
 * Temel CRUD ve uygulama/kullanıcıya özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler ReviewDto ile yapılır.
 */
public interface ReviewService {
    List<ReviewResponse> getAllReviews();
    Optional<ReviewResponse> getReviewById(Long id);
    ReviewResponse createReview(ReviewRequest request);
    ReviewResponse updateReview(Long id, ReviewRequest request);
    void deleteReview(Long id);
}
