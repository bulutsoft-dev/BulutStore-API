package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.DownloadHistory;
import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import com.bulutsoft.bulutstore.mapper.DownloadHistoryMapper;
import com.bulutsoft.bulutstore.repos.DownloadHistoryRepository;
import com.bulutsoft.bulutstore.repos.AppRepository;
import com.bulutsoft.bulutstore.repos.UserRepository;
import com.bulutsoft.bulutstore.service.DownloadHistoryService;
import com.bulutsoft.bulutstore.request.DownloadHistoryRequest;
import com.bulutsoft.bulutstore.response.DownloadHistoryResponse;
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
    public List<DownloadHistoryResponse> getAllDownloadHistories() {
        return downloadHistoryMapper.toResponseList(downloadHistoryRepository.findAll());
    }

    @Override
    public Optional<DownloadHistoryResponse> getDownloadHistoryById(Long id) {
        return downloadHistoryRepository.findById(id).map(downloadHistoryMapper::toResponse);
    }

    @Override
    @Transactional
    public DownloadHistoryResponse createDownloadHistory(DownloadHistoryRequest request) {
        DownloadHistory downloadHistory = downloadHistoryMapper.toEntity(request);
        appRepository.findById(request.getAppId()).ifPresent(downloadHistory::setApp);
        if (request.getUserId() != null) {
            userRepository.findById(request.getUserId()).ifPresent(downloadHistory::setUser);
        } else {
            downloadHistory.setUser(null);
        }
        return downloadHistoryMapper.toResponse(downloadHistoryRepository.save(downloadHistory));
    }

    @Override
    @Transactional
    public DownloadHistoryResponse updateDownloadHistory(Long id, DownloadHistoryRequest request) {
        DownloadHistory downloadHistory = downloadHistoryMapper.toEntity(request);
        downloadHistory.setId(id);
        appRepository.findById(request.getAppId()).ifPresent(downloadHistory::setApp);
        if (request.getUserId() != null) {
            userRepository.findById(request.getUserId()).ifPresent(downloadHistory::setUser);
        } else {
            downloadHistory.setUser(null);
        }
        return downloadHistoryMapper.toResponse(downloadHistoryRepository.save(downloadHistory));
    }

    @Override
    @Transactional
    public void deleteDownloadHistory(Long id) {
        downloadHistoryRepository.deleteById(id);
    }

    @Override
    public List<DownloadHistoryResponse> getDownloadHistoriesByUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(u -> downloadHistoryMapper.toResponseList(downloadHistoryRepository.findByUser(u))).orElse(List.of());
    }

    @Override
    public List<DownloadHistoryResponse> getDownloadHistoriesByApp(Long appId) {
        Optional<App> app = appRepository.findById(appId);
        return app.map(a -> downloadHistoryMapper.toResponseList(downloadHistoryRepository.findByApp(a))).orElse(List.of());
    }
}
