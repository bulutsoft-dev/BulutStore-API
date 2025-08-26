package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.Review;
import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Review entity'si için CRUD işlemlerini sağlayan repository arayüzü.
 * Bir uygulamanın veya kullanıcının tüm yorumlarını listeleyebilir.
 * Aynı kullanıcı ve uygulama için tek yorum kuralını enforce eder.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    /**
     * Belirli bir uygulamanın tüm yorumlarını döndürür.
     * @param app uygulama
     * @return yorum listesi
     */
    List<Review> findByApp(App app);

    /**
     * Belirli bir kullanıcının tüm yorumlarını döndürür.
     * @param user kullanıcı
     * @return yorum listesi
     */
    List<Review> findByUser(User user);

    /**
     * Belirli bir kullanıcı ve uygulama için yorumu bulur (unique constraint için).
     * @param app uygulama
     * @param user kullanıcı
     * @return yorum (varsa)
     */
    Optional<Review> findByAppAndUser(App app, User user);
}

