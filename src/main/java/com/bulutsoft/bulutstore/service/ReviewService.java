package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.entity.Review;
import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import java.util.List;
import java.util.Optional;

/**
 * Yorum işlemleri için servis arayüzü.
 * Temel CRUD ve uygulama/kullanıcıya özel iş mantığı metotlarını tanımlar.
 */
public interface ReviewService {
    List<Review> getAllReviews();
    Optional<Review> getReviewById(Long id);
    Review createReview(Review review);
    Review updateReview(Long id, Review review);
    void deleteReview(Long id);
    List<Review> getReviewsByApp(App app);
    List<Review> getReviewsByUser(User user);
    Optional<Review> getReviewByAppAndUser(App app, User user);
}

