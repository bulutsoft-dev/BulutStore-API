package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.dto.AppDto;
import java.util.List;
import java.util.Optional;

/**
 * Uygulama işlemleri için servis arayüzü.
 * Temel CRUD ve uygulamaya özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler AppDto ile yapılır.
 */
public interface AppService {
    List<AppDto> getAllApps();
    Optional<AppDto> getAppById(Long id);
    AppDto createApp(AppDto appDto);
    AppDto updateApp(Long id, AppDto appDto);
    void deleteApp(Long id);
    List<AppDto> getAppsByDeveloper(Long developerId);
    List<AppDto> getAppsByCategory(Long categoryId);
    List<AppDto> searchAppsByName(String name);
}
