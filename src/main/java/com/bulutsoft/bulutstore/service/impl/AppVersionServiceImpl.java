package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.AppVersion;
import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.mapper.AppVersionMapper;
import com.bulutsoft.bulutstore.repos.AppVersionRepository;
import com.bulutsoft.bulutstore.repos.AppRepository;
import com.bulutsoft.bulutstore.service.AppVersionService;
import com.bulutsoft.bulutstore.request.AppVersionRequest;
import com.bulutsoft.bulutstore.response.AppVersionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Uygulama sürüm işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 * DTO <-> Entity dönüşümleri AppVersionMapper ile yapılır.
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {
    private final AppVersionRepository appVersionRepository;
    private final AppRepository appRepository;
    private final AppVersionMapper appVersionMapper;

    @Autowired
    public AppVersionServiceImpl(AppVersionRepository appVersionRepository, AppRepository appRepository, AppVersionMapper appVersionMapper) {
        this.appVersionRepository = appVersionRepository;
        this.appRepository = appRepository;
        this.appVersionMapper = appVersionMapper;
    }

    @Override
    public List<AppVersionResponse> getAllAppVersions() {
        return appVersionMapper.toResponseList(appVersionRepository.findAll());
    }

    @Override
    public Optional<AppVersionResponse> getAppVersionById(Long id) {
        return appVersionRepository.findById(id).map(appVersionMapper::toResponse);
    }

    @Override
    @Transactional
    public AppVersionResponse createAppVersion(AppVersionRequest request) {
        AppVersion appVersion = appVersionMapper.toEntity(request);
        appRepository.findById(request.getAppId()).ifPresent(appVersion::setApp);
        return appVersionMapper.toResponse(appVersionRepository.save(appVersion));
    }

    @Override
    @Transactional
    public AppVersionResponse updateAppVersion(Long id, AppVersionRequest request) {
        AppVersion appVersion = appVersionMapper.toEntity(request);
        appVersion.setId(id);
        appRepository.findById(request.getAppId()).ifPresent(appVersion::setApp);
        return appVersionMapper.toResponse(appVersionRepository.save(appVersion));
    }

    @Override
    @Transactional
    public void deleteAppVersion(Long id) {
        appVersionRepository.deleteById(id);
    }

    @Override
    public List<AppVersionResponse> getAppVersionsByApp(Long appId) {
        Optional<App> app = appRepository.findById(appId);
        return app.map(a -> appVersionMapper.toResponseList(appVersionRepository.findByApp(a))).orElse(List.of());
    }
}
