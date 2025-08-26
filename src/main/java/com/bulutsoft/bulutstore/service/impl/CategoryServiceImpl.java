package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.Category;
import com.bulutsoft.bulutstore.mapper.CategoryMapper;
import com.bulutsoft.bulutstore.repos.CategoryRepository;
import com.bulutsoft.bulutstore.service.CategoryService;
import com.bulutsoft.bulutstore.request.CategoryRequest;
import com.bulutsoft.bulutstore.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Kategori işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 * DTO <-> Entity dönüşümleri CategoryMapper ile yapılır.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryMapper.toResponseList(categoryRepository.findAll());
    }

    @Override
    public Optional<CategoryResponse> getCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toResponse);
    }

    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category category = categoryMapper.toEntity(request);
        category.setId(id);
        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<CategoryResponse> getCategoryByName(String name) {
        return categoryRepository.findByName(name).map(categoryMapper::toResponse);
    }
}
