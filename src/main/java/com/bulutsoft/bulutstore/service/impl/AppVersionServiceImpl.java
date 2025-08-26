package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.AppVersion;
import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.repos.AppVersionRepository;
import com.bulutsoft.bulutstore.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Uygulama sürüm işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {
    private final AppVersionRepository appVersionRepository;

    @Autowired
    public AppVersionServiceImpl(AppVersionRepository appVersionRepository) {
        this.appVersionRepository = appVersionRepository;
    }

    @Override
    public List<AppVersion> getAllAppVersions() {
        return appVersionRepository.findAll();
    }

    @Override
    public Optional<AppVersion> getAppVersionById(Long id) {
        return appVersionRepository.findById(id);
    }

    @Override
    @Transactional
    public AppVersion createAppVersion(AppVersion appVersion) {
        return appVersionRepository.save(appVersion);
    }

    @Override
    @Transactional
    public AppVersion updateAppVersion(Long id, AppVersion appVersion) {
        appVersion.setId(id);
        return appVersionRepository.save(appVersion);
    }

    @Override
    @Transactional
    public void deleteAppVersion(Long id) {
        appVersionRepository.deleteById(id);
    }

    @Override
    public List<AppVersion> getAppVersionsByApp(App app) {
        return appVersionRepository.findByApp(app);
    }
}

