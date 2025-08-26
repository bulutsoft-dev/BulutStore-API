package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.request.AppVersionRequest;
import com.bulutsoft.bulutstore.response.AppVersionResponse;

import java.util.List;
import java.util.Optional;

/**
 * Uygulama sürüm işlemleri için servis arayüzü.
 * Temel CRUD ve uygulamaya özel sürüm listeleme metotlarını tanımlar.
 * Tüm işlemler AppVersionDto ile yapılır.
 */
public interface AppVersionService {
    List<AppVersionResponse> getAllAppVersions();
    Optional<AppVersionResponse> getAppVersionById(Long id);
    AppVersionResponse createAppVersion(AppVersionRequest request);
    AppVersionResponse updateAppVersion(Long id, AppVersionRequest request);
    void deleteAppVersion(Long id);
}
