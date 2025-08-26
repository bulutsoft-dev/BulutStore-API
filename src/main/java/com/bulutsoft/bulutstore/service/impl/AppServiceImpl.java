package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.entity.Category;
import com.bulutsoft.bulutstore.repos.AppRepository;
import com.bulutsoft.bulutstore.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Uygulama işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 */
@Service
public class AppServiceImpl implements AppService {
    private final AppRepository appRepository;

    @Autowired
    public AppServiceImpl(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public List<App> getAllApps() {
        return appRepository.findAll();
    }

    @Override
    public Optional<App> getAppById(Long id) {
        return appRepository.findById(id);
    }

    @Override
    @Transactional
    public App createApp(App app) {
        return appRepository.save(app);
    }

    @Override
    @Transactional
    public App updateApp(Long id, App app) {
        app.setId(id);
        return appRepository.save(app);
    }

    @Override
    @Transactional
    public void deleteApp(Long id) {
        appRepository.deleteById(id);
    }

    @Override
    public List<App> getAppsByDeveloper(User developer) {
        return appRepository.findByDeveloper(developer);
    }

    @Override
    public List<App> getAppsByCategory(Category category) {
        return appRepository.findByCategory(category);
    }

    @Override
    public List<App> searchAppsByName(String name) {
        return appRepository.findByNameContainingIgnoreCase(name);
    }
}

