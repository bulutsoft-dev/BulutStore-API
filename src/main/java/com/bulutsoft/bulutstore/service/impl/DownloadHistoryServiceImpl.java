package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.DownloadHistory;
import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.mapper.DownloadHistoryMapper;
import com.bulutsoft.bulutstore.repos.DownloadHistoryRepository;
import com.bulutsoft.bulutstore.repos.AppRepository;
import com.bulutsoft.bulutstore.repos.UserRepository;
import com.bulutsoft.bulutstore.service.DownloadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * İndirme geçmişi işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 * DTO <-> Entity dönüşümleri DownloadHistoryMapper ile yapılır.
 */
@Service
public class DownloadHistoryServiceImpl implements DownloadHistoryService {
    private final DownloadHistoryRepository downloadHistoryRepository;
    private final AppRepository appRepository;
    private final UserRepository userRepository;
    private final DownloadHistoryMapper downloadHistoryMapper;

    @Autowired
    public DownloadHistoryServiceImpl(DownloadHistoryRepository downloadHistoryRepository, AppRepository appRepository, UserRepository userRepository, DownloadHistoryMapper downloadHistoryMapper) {
        this.downloadHistoryRepository = downloadHistoryRepository;
        this.appRepository = appRepository;
        this.userRepository = userRepository;
        this.downloadHistoryMapper = downloadHistoryMapper;
    }

    @Override
    public List<DownloadHistoryDto> getAllDownloadHistories() {
        return downloadHistoryMapper.toDtoList(downloadHistoryRepository.findAll());
    }

    @Override
    public Optional<DownloadHistoryDto> getDownloadHistoryById(Long id) {
        return downloadHistoryRepository.findById(id).map(downloadHistoryMapper::toDto);
    }

    @Override
    @Transactional
    public DownloadHistoryDto createDownloadHistory(DownloadHistoryDto downloadHistoryDto) {
        DownloadHistory downloadHistory = downloadHistoryMapper.toEntity(downloadHistoryDto);
        if (downloadHistoryDto.getApp() != null && downloadHistoryDto.getApp().getId() != null) {
            appRepository.findById(downloadHistoryDto.getApp().getId()).ifPresent(downloadHistory::setApp);
        }
        if (downloadHistoryDto.getUser() != null && downloadHistoryDto.getUser().getId() != null) {
            userRepository.findById(downloadHistoryDto.getUser().getId()).ifPresent(downloadHistory::setUser);
        }
        return downloadHistoryMapper.toDto(downloadHistoryRepository.save(downloadHistory));
    }

    @Override
    @Transactional
    public DownloadHistoryDto updateDownloadHistory(Long id, DownloadHistoryDto downloadHistoryDto) {
        DownloadHistory downloadHistory = downloadHistoryMapper.toEntity(downloadHistoryDto);
        downloadHistory.setId(id);
        if (downloadHistoryDto.getApp() != null && downloadHistoryDto.getApp().getId() != null) {
            appRepository.findById(downloadHistoryDto.getApp().getId()).ifPresent(downloadHistory::setApp);
        }
        if (downloadHistoryDto.getUser() != null && downloadHistoryDto.getUser().getId() != null) {
            userRepository.findById(downloadHistoryDto.getUser().getId()).ifPresent(downloadHistory::setUser);
        }
        return downloadHistoryMapper.toDto(downloadHistoryRepository.save(downloadHistory));
    }

    @Override
    @Transactional
    public void deleteDownloadHistory(Long id) {
        downloadHistoryRepository.deleteById(id);
    }

    @Override
    public List<DownloadHistoryDto> getDownloadHistoriesByUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(u -> downloadHistoryMapper.toDtoList(downloadHistoryRepository.findByUser(u))).orElse(List.of());
    }

    @Override
    public List<DownloadHistoryDto> getDownloadHistoriesByApp(Long appId) {
        Optional<App> app = appRepository.findById(appId);
        return app.map(a -> downloadHistoryMapper.toDtoList(downloadHistoryRepository.findByApp(a))).orElse(List.of());
    }
}

