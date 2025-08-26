package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.entity.Category;
import java.util.List;
import java.util.Optional;

/**
 * Kategori işlemleri için servis arayüzü.
 * Temel CRUD ve kategoriye özel iş mantığı metotlarını tanımlar.
 */
public interface CategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    Optional<Category> getCategoryByName(String name);
}

