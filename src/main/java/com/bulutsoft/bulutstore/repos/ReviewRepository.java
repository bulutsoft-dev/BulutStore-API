package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Review entity'si için CRUD işlemlerini sağlayan repository arayüzü.
 * Bir uygulamanın veya kullanıcının tüm yorumlarını listeleyebilir.
 * Aynı kullanıcı ve uygulama için tek yorum kuralını enforce eder.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAppId(Long appId);
}
