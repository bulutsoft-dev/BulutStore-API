package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.entity.Category;
import com.bulutsoft.bulutstore.entity.AppStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * App entity'si için CRUD ve özel sorguların tanımlandığı repository arayüzü.
 * JpaRepository, temel CRUD işlemlerini otomatik olarak sağlar.
 */
@Repository
public interface AppRepository extends JpaRepository<App, Long> {
    /**
     * Belirli bir geliştiriciye ait uygulamaları döndürür.
     */
    List<App> findByDeveloper(User developer);

    /**
     * Belirli bir kategoriye ait uygulamaları döndürür.
     */
    List<App> findByCategory(Category category);

    /**
     * Uygulama adına göre arama yapar (case-insensitive, partial match).
     */
    List<App> findByNameContainingIgnoreCase(String name);

    /**
     * Sadece onaylanmış uygulamaları döndürür.
     */
    List<App> findByStatus(AppStatus status);

    /**
     * Tüm uygulamaları developer ve category ile birlikte döndürür (fetch join).
     */
    @Query("SELECT a FROM App a LEFT JOIN FETCH a.developer LEFT JOIN FETCH a.category")
    List<App> findAllWithDeveloperAndCategory();

    /**
     * Tek bir uygulamayı geliştirici ve kategoriyle birlikte döndürür.
     */
    @Query("SELECT a FROM App a LEFT JOIN FETCH a.developer LEFT JOIN FETCH a.category WHERE a.id = :id")
    Optional<App> findByIdWithDeveloperAndCategory(Long id);
}
