package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.entity.Category;
import com.bulutsoft.bulutstore.entity.Tag;
import com.bulutsoft.bulutstore.entity.AppVersion;
import com.bulutsoft.bulutstore.mapper.AppMapper;
import com.bulutsoft.bulutstore.repos.AppRepository;
import com.bulutsoft.bulutstore.repos.UserRepository;
import com.bulutsoft.bulutstore.repos.CategoryRepository;
import com.bulutsoft.bulutstore.repos.TagRepository;
import com.bulutsoft.bulutstore.repos.AppVersionRepository;
import com.bulutsoft.bulutstore.service.AppService;
import com.bulutsoft.bulutstore.request.AppRequest;
import com.bulutsoft.bulutstore.response.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Uygulama işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 * DTO <-> Entity dönüşümleri AppMapper ile yapılır.
 */
@Service
public class AppServiceImpl implements AppService {
    private final AppRepository appRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final AppMapper appMapper;
    private final AppVersionRepository appVersionRepository;

    @Autowired
    public AppServiceImpl(AppRepository appRepository, UserRepository userRepository, CategoryRepository categoryRepository, TagRepository tagRepository, AppMapper appMapper, AppVersionRepository appVersionRepository) {
        this.appRepository = appRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.appMapper = appMapper;
        this.appVersionRepository = appVersionRepository;
    }

    @Override
    public List<AppResponse> getAllApps() {
        return appMapper.toResponseList(appRepository.findAllWithDeveloperAndCategory());
    }

    @Override
    public Optional<AppResponse> getAppById(Long id) {
        return appRepository.findById(id).map(appMapper::toResponse);
    }

    @Override
    @Transactional
    public AppResponse createApp(AppRequest request, String username) {
        // Giriş yapan kullanıcıyı bul
        User developer = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Developer not found"));
        if (developer.getRole() != com.bulutsoft.bulutstore.entity.Role.DEVELOPER &&
            developer.getRole() != com.bulutsoft.bulutstore.entity.Role.ADMIN) {
            throw new IllegalArgumentException("User is not a developer or admin");
        }
        App app = appMapper.toEntity(request);
        app.setDeveloper(developer);
        app.setStatus(com.bulutsoft.bulutstore.entity.AppStatus.PENDING); // Otomatik PENDING
        categoryRepository.findById(request.getCategoryId()).ifPresent(app::setCategory);
        if (request.getTagIds() != null) {
            app.setTags(request.getTagIds().stream()
                .map(tagRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(java.util.stream.Collectors.toList()));
        }
        // Önce App kaydedilmeli ki id oluşsun
        App savedApp = appRepository.save(app);
        // AppVersion oluştur
        if (request.getVersion() != null && !request.getVersion().isBlank()) {
            AppVersion version = new AppVersion();
            version.setApp(savedApp);
            version.setVersion(request.getVersion());
            version.setApkPath(savedApp.getFileUrl());
            appVersionRepository.save(version);
            // App'ın versions listesine ekle (isteğe bağlı, JPA için gerekmez)
            if (savedApp.getVersions() != null) {
                savedApp.getVersions().add(version);
            }
        }
        return appMapper.toResponse(savedApp);
    }

    @Override
    @Transactional
    public AppResponse updateApp(Long id, AppRequest request, String username) {
        User developer = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("Developer not found"));
        if (developer.getRole() != com.bulutsoft.bulutstore.entity.Role.DEVELOPER ||
            developer.getStatus() != com.bulutsoft.bulutstore.entity.UserStatus.ACTIVE) {
            throw new IllegalArgumentException("User is not an active developer");
        }
        // Var olan App'i bul
        App app = appRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("App not found"));
        // Sadece null olmayan alanları güncelle
        if (request.getName() != null) app.setName(request.getName());
        if (request.getDescription() != null) app.setDescription(request.getDescription());
        if (request.getShortDescription() != null) app.setShortDescription(request.getShortDescription());
        if (request.getIconUrl() != null) app.setIconUrl(request.getIconUrl());
        if (request.getScreenshotUrls() != null) app.setScreenshotUrls(request.getScreenshotUrls());
        if (request.getFileUrl() != null) app.setFileUrl(request.getFileUrl());
        // Kategori güncelle
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            app.setCategory(category);
        }
        // Tag güncelle
        if (request.getTagIds() != null) {
            List<Tag> tags = request.getTagIds().stream()
                .map(tagId -> tagRepository.findById(tagId)
                    .orElseThrow(() -> new IllegalArgumentException("Tag not found: " + tagId)))
                .collect(Collectors.toList());
            app.setTags(tags);
        }
        // Status güncelle (isteğe bağlı, null ise dokunma)
        if (request.getStatus() != null) {
            app.setStatus(request.getStatus());
        }
        // Sürüm güncellemesi: Eğer yeni bir version geldiyse ve farklıysa yeni AppVersion oluştur
        if (request.getVersion() != null && !request.getVersion().isBlank()) {
            String newVersion = request.getVersion();
            Optional<AppVersion> lastVersionOpt = appVersionRepository.findTopByAppIdOrderByReleaseDateDesc(app.getId());
            boolean isSame = lastVersionOpt.isPresent() && lastVersionOpt.get().getVersion().equals(newVersion);
            if (!isSame) {
                AppVersion version = new AppVersion();
                version.setApp(app);
                version.setVersion(newVersion);
                version.setApkPath(app.getFileUrl());
                appVersionRepository.save(version);
            }
        }
        // Version güncellemesi (AppVersion tablosu ile ilişkili ise, burada ayrıca ele alınmalı)
        // Şimdilik sadece App entity'sini güncelliyoruz.
        return appMapper.toResponse(appRepository.save(app));
    }

    @Override
    @Transactional
    public void deleteApp(Long id, String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        App app = appRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("App not found"));
        // Admin ise her app'i silebilir
        if (user.getRole() == com.bulutsoft.bulutstore.entity.Role.ADMIN) {
            appRepository.delete(app);
            return;
        }
        // Developer ise sadece kendi app'ini silebilir
        if (user.getRole() == com.bulutsoft.bulutstore.entity.Role.DEVELOPER &&
            app.getDeveloper().getId().equals(user.getId())) {
            appRepository.delete(app);
            return;
        }
        // Aksi halde yetkisiz
        throw new SecurityException("You are not authorized to delete this app");
    }

    @Override
    public List<AppResponse> getAppsByDeveloper(Long developerId) {
        Optional<User> developer = userRepository.findById(developerId);
        return developer.map(user -> appMapper.toResponseList(appRepository.findByDeveloper(user))).orElse(List.of());
    }

    @Override
    public List<AppResponse> getAppsByCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.map(cat -> appMapper.toResponseList(appRepository.findByCategory(cat))).orElse(List.of());
    }

    @Override
    public List<AppResponse> searchAppsByName(String name) {
        return appMapper.toResponseList(appRepository.findByNameContainingIgnoreCase(name));
    }

    @Override
    @Transactional
    public void approveApp(Long appId) {
        App app = appRepository.findById(appId)
            .orElseThrow(() -> new IllegalArgumentException("App not found"));
        app.setStatus(com.bulutsoft.bulutstore.entity.AppStatus.APPROVED);
        appRepository.save(app);
    }

    @Override
    @Transactional
    public void rejectApp(Long appId) {
        App app = appRepository.findById(appId)
            .orElseThrow(() -> new IllegalArgumentException("App not found"));
        app.setStatus(com.bulutsoft.bulutstore.entity.AppStatus.REJECTED);
        appRepository.save(app);
    }

    @Override
    public List<AppResponse> getApprovedApps() {
        return appMapper.toResponseList(appRepository.findByStatus(com.bulutsoft.bulutstore.entity.AppStatus.APPROVED));
    }
}
