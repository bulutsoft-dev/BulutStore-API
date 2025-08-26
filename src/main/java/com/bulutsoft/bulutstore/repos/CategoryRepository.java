package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Category entity'si için CRUD işlemlerini sağlayan repository arayüzü.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
