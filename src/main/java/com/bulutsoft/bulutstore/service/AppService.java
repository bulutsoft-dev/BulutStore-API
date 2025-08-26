package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.entity.Category;
import java.util.List;
import java.util.Optional;

/**
 * Uygulama işlemleri için servis arayüzü.
 * Temel CRUD ve uygulamaya özel iş mantığı metotlarını tanımlar.
 */
public interface AppService {
    List<App> getAllApps();
    Optional<App> getAppById(Long id);
    App createApp(App app);
    App updateApp(Long id, App app);
    void deleteApp(Long id);
    List<App> getAppsByDeveloper(User developer);
    List<App> getAppsByCategory(Category category);
    List<App> searchAppsByName(String name);
}

