package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.AppVersion;
import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.mapper.AppVersionMapper;
import com.bulutsoft.bulutstore.repos.AppVersionRepository;
import com.bulutsoft.bulutstore.repos.AppRepository;
import com.bulutsoft.bulutstore.service.AppVersionService;
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
    public List<AppVersionDto> getAllAppVersions() {
        return appVersionMapper.toDtoList(appVersionRepository.findAll());
    }

    @Override
    public Optional<AppVersionDto> getAppVersionById(Long id) {
        return appVersionRepository.findById(id).map(appVersionMapper::toDto);
    }

    @Override
    @Transactional
    public AppVersionDto createAppVersion(AppVersionDto appVersionDto) {
        AppVersion appVersion = appVersionMapper.toEntity(appVersionDto);
        if (appVersionDto.getApp() != null && appVersionDto.getApp().getId() != null) {
            appRepository.findById(appVersionDto.getApp().getId()).ifPresent(appVersion::setApp);
        }
        return appVersionMapper.toDto(appVersionRepository.save(appVersion));
    }

    @Override
    @Transactional
    public AppVersionDto updateAppVersion(Long id, AppVersionDto appVersionDto) {
        AppVersion appVersion = appVersionMapper.toEntity(appVersionDto);
        appVersion.setId(id);
        if (appVersionDto.getApp() != null && appVersionDto.getApp().getId() != null) {
            appRepository.findById(appVersionDto.getApp().getId()).ifPresent(appVersion::setApp);
        }
        return appVersionMapper.toDto(appVersionRepository.save(appVersion));
    }

    @Override
    @Transactional
    public void deleteAppVersion(Long id) {
        appVersionRepository.deleteById(id);
    }

    @Override
    public List<AppVersionDto> getAppVersionsByApp(Long appId) {
        Optional<App> app = appRepository.findById(appId);
        return app.map(a -> appVersionMapper.toDtoList(appVersionRepository.findByApp(a))).orElse(List.of());
    }
}
