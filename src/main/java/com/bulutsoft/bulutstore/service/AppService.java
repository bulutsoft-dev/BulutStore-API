package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.request.AppRequest;
import com.bulutsoft.bulutstore.response.AppResponse;

import java.util.List;
import java.util.Optional;

/**
 * Uygulama işlemleri için servis arayüzü.
 * Temel CRUD ve uygulamaya özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler AppDto ile yapılır.
 */
public interface AppService {
    List<AppResponse> getAllApps();
    Optional<AppResponse> getAppById(Long id);
    AppResponse createApp(AppRequest request);
    AppResponse updateApp(Long id, AppRequest request);
    void deleteApp(Long id);
    List<AppResponse> getAppsByDeveloper(Long developerId);
    List<AppResponse> getAppsByCategory(Long categoryId);
    List<AppResponse> searchAppsByName(String name);
}
