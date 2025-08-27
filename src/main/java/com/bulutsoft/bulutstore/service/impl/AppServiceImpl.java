package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.entity.Category;
import com.bulutsoft.bulutstore.entity.Tag;
import com.bulutsoft.bulutstore.mapper.AppMapper;
import com.bulutsoft.bulutstore.repos.AppRepository;
import com.bulutsoft.bulutstore.repos.UserRepository;
import com.bulutsoft.bulutstore.repos.CategoryRepository;
import com.bulutsoft.bulutstore.repos.TagRepository;
import com.bulutsoft.bulutstore.service.AppService;
import com.bulutsoft.bulutstore.request.AppRequest;
import com.bulutsoft.bulutstore.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Uygulama işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 * DTO <-> Entity dönüşümleri AppMapper ile yapılır.
 */
@Service
public class AppServiceImpl implements AppService {
    private final AppRepository appRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final AppMapper appMapper;

    @Autowired
    public AppServiceImpl(AppRepository appRepository, UserRepository userRepository, CategoryRepository categoryRepository, TagRepository tagRepository, AppMapper appMapper) {
        this.appRepository = appRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.appMapper = appMapper;
    }

    @Override
    public List<AppResponse> getAllApps() {
        return appMapper.toResponseList(appRepository.findAll());
    }

    @Override
    public Optional<AppResponse> getAppById(Long id) {
        return appRepository.findById(id).map(appMapper::toResponse);
    }

    @Override
    @Transactional
    public AppResponse createApp(AppRequest request, String username) {
        // Giriş yapan kullanıcıyı bul
        User developer = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Developer not found"));
        if (developer.getRole() != com.bulutsoft.bulutstore.entity.Role.DEVELOPER ||
            developer.getStatus() != com.bulutsoft.bulutstore.entity.UserStatus.ACTIVE) {
            throw new IllegalArgumentException("User is not an active developer");
        }
        App app = appMapper.toEntity(request);
        app.setDeveloper(developer);
        categoryRepository.findById(request.getCategoryId()).ifPresent(app::setCategory);
        if (request.getTagIds() != null) {
            app.setTags(request.getTagIds().stream()
                .map(tagRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(java.util.stream.Collectors.toList()));
        }
        return appMapper.toResponse(appRepository.save(app));
    }

    @Override
    @Transactional
    public AppResponse updateApp(Long id, AppRequest request, String username) {
        User developer = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Developer not found"));
        if (developer.getRole() != com.bulutsoft.bulutstore.entity.Role.DEVELOPER ||
            developer.getStatus() != com.bulutsoft.bulutstore.entity.UserStatus.ACTIVE) {
            throw new IllegalArgumentException("User is not an active developer");
        }
        App app = appMapper.toEntity(request);
        app.setId(id);
        app.setDeveloper(developer);
        categoryRepository.findById(request.getCategoryId()).ifPresent(app::setCategory);
        if (request.getTagIds() != null) {
            app.setTags(request.getTagIds().stream()
                .map(tagRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(java.util.stream.Collectors.toList()));
        }
        return appMapper.toResponse(appRepository.save(app));
    }

    @Override
    @Transactional
    public void deleteApp(Long id) {
        appRepository.deleteById(id);
    }

    @Override
    public List<AppResponse> getAppsByDeveloper(Long developerId) {
        Optional<User> developer = userRepository.findById(developerId);
        return developer.map(user -> appMapper.toResponseList(appRepository.findByDeveloper(user))).orElse(List.of());
    }

    @Override
    public List<AppResponse> getAppsByCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.map(cat -> appMapper.toResponseList(appRepository.findByCategory(cat))).orElse(List.of());
    }

    @Override
    public List<AppResponse> searchAppsByName(String name) {
        return appMapper.toResponseList(appRepository.findByNameContainingIgnoreCase(name));
    }
}
