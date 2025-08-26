package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.request.CategoryRequest;
import com.bulutsoft.bulutstore.response.CategoryResponse;

import java.util.List;
import java.util.Optional;

/**
 * Kategori işlemleri için servis arayüzü.
 * Temel CRUD ve kategoriye özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler CategoryDto ile yapılır.
 */
public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    Optional<CategoryResponse> getCategoryById(Long id);
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(Long id, CategoryRequest request);
    void deleteCategory(Long id);
}
