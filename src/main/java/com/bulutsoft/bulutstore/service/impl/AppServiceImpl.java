package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.entity.Category;
import com.bulutsoft.bulutstore.mapper.AppMapper;
import com.bulutsoft.bulutstore.repos.AppRepository;
import com.bulutsoft.bulutstore.repos.UserRepository;
import com.bulutsoft.bulutstore.repos.CategoryRepository;
import com.bulutsoft.bulutstore.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Uygulama işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 * DTO <-> Entity dönüşümleri AppMapper ile yapılır.
 */
@Service
public class AppServiceImpl implements AppService {
    private final AppRepository appRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AppMapper appMapper;

    @Autowired
    public AppServiceImpl(AppRepository appRepository, UserRepository userRepository, CategoryRepository categoryRepository, AppMapper appMapper) {
        this.appRepository = appRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.appMapper = appMapper;
    }

    @Override
    public List<AppDto> getAllApps() {
        return appMapper.toDtoList(appRepository.findAll());
    }

    @Override
    public Optional<AppDto> getAppById(Long id) {
        return appRepository.findById(id).map(appMapper::toDto);
    }

    @Override
    @Transactional
    public AppDto createApp(AppDto appDto) {
        App app = appMapper.toEntity(appDto);
        // İlişkili developer ve category id'leri DTO'dan alınmalı, burada set edilebilir
        if (appDto.getDeveloper() != null && appDto.getDeveloper().getId() != null) {
            userRepository.findById(appDto.getDeveloper().getId()).ifPresent(app::setDeveloper);
        }
        if (appDto.getCategory() != null && appDto.getCategory().getId() != null) {
            categoryRepository.findById(appDto.getCategory().getId()).ifPresent(app::setCategory);
        }
        return appMapper.toDto(appRepository.save(app));
    }

    @Override
    @Transactional
    public AppDto updateApp(Long id, AppDto appDto) {
        App app = appMapper.toEntity(appDto);
        app.setId(id);
        if (appDto.getDeveloper() != null && appDto.getDeveloper().getId() != null) {
            userRepository.findById(appDto.getDeveloper().getId()).ifPresent(app::setDeveloper);
        }
        if (appDto.getCategory() != null && appDto.getCategory().getId() != null) {
            categoryRepository.findById(appDto.getCategory().getId()).ifPresent(app::setCategory);
        }
        return appMapper.toDto(appRepository.save(app));
    }

    @Override
    @Transactional
    public void deleteApp(Long id) {
        appRepository.deleteById(id);
    }

    @Override
    public List<AppDto> getAppsByDeveloper(Long developerId) {
        Optional<User> developer = userRepository.findById(developerId);
        return developer.map(user -> appMapper.toDtoList(appRepository.findByDeveloper(user))).orElse(List.of());
    }

    @Override
    public List<AppDto> getAppsByCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.map(cat -> appMapper.toDtoList(appRepository.findByCategory(cat))).orElse(List.of());
    }

    @Override
    public List<AppDto> searchAppsByName(String name) {
        return appMapper.toDtoList(appRepository.findByNameContainingIgnoreCase(name));
    }
}
