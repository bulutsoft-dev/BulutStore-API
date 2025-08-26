package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.entity.AppVersion;
import com.bulutsoft.bulutstore.entity.App;
import java.util.List;
import java.util.Optional;

/**
 * Uygulama sürüm işlemleri için servis arayüzü.
 * Temel CRUD ve uygulamaya özel sürüm listeleme metotlarını tanımlar.
 */
public interface AppVersionService {
    List<AppVersion> getAllAppVersions();
    Optional<AppVersion> getAppVersionById(Long id);
    AppVersion createAppVersion(AppVersion appVersion);
    AppVersion updateAppVersion(Long id, AppVersion appVersion);
    void deleteAppVersion(Long id);
    List<AppVersion> getAppVersionsByApp(App app);
}

