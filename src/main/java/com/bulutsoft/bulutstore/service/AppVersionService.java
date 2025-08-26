package com.bulutsoft.bulutstore.service;

import java.util.List;
import java.util.Optional;

/**
 * Uygulama sürüm işlemleri için servis arayüzü.
 * Temel CRUD ve uygulamaya özel sürüm listeleme metotlarını tanımlar.
 * Tüm işlemler AppVersionDto ile yapılır.
 */
public interface AppVersionService {
    List<AppVersionDto> getAllAppVersions();
    Optional<AppVersionDto> getAppVersionById(Long id);
    AppVersionDto createAppVersion(AppVersionDto appVersionDto);
    AppVersionDto updateAppVersion(Long id, AppVersionDto appVersionDto);
    void deleteAppVersion(Long id);
    List<AppVersionDto> getAppVersionsByApp(Long appId);
}
