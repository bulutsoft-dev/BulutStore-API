package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Category entity'si için CRUD işlemlerini sağlayan repository arayüzü.
 * Kategori adına göre arama fonksiyonu içerir.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * Kategori adına göre arama yapar.
     * @param name kategori adı
     * @return kategori (varsa)
     */
    Optional<Category> findByName(String name);
}

