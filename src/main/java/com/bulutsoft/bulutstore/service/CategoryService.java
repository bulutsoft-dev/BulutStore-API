package com.bulutsoft.bulutstore.service;

import java.util.List;
import java.util.Optional;

/**
 * Kategori işlemleri için servis arayüzü.
 * Temel CRUD ve kategoriye özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler CategoryDto ile yapılır.
 */
public interface CategoryService {
    List<CategoryDto> getAllCategories();
    Optional<CategoryDto> getCategoryById(Long id);
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
    Optional<CategoryDto> getCategoryByName(String name);
}
