package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.DownloadHistory;
import com.bulutsoft.bulutstore.mapper.DownloadHistoryMapper;
import com.bulutsoft.bulutstore.repos.DownloadHistoryRepository;
import com.bulutsoft.bulutstore.repos.AppRepository;
import com.bulutsoft.bulutstore.service.DownloadHistoryService;
import com.bulutsoft.bulutstore.request.DownloadHistoryRequest;
import com.bulutsoft.bulutstore.response.DownloadHistoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * İndirme geçmişi işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 * DTO <-> Entity dönüşümleri DownloadHistoryMapper ile yapılır.
 */
@Service
public class DownloadHistoryServiceImpl implements DownloadHistoryService {
    private final DownloadHistoryRepository downloadHistoryRepository;
    private final AppRepository appRepository;
    private final DownloadHistoryMapper downloadHistoryMapper;

    @Autowired
    public DownloadHistoryServiceImpl(DownloadHistoryRepository downloadHistoryRepository, AppRepository appRepository, DownloadHistoryMapper downloadHistoryMapper) {
        this.downloadHistoryRepository = downloadHistoryRepository;
        this.appRepository = appRepository;
        this.downloadHistoryMapper = downloadHistoryMapper;
    }

    @Override
    @Transactional
    public DownloadHistoryResponse createDownloadHistory(DownloadHistoryRequest request) {
        App app = appRepository.findById(request.getAppId()).orElseThrow(() -> new IllegalArgumentException("App not found"));
        DownloadHistory downloadHistory = DownloadHistory.builder()
                .app(app)
                .build();
        downloadHistoryRepository.save(downloadHistory);
        long count = downloadHistoryRepository.countByApp_Id(request.getAppId());
        return downloadHistoryMapper.toResponse(count);
    }

    @Override
    public DownloadHistoryResponse getDownloadCountByAppId(Long appId) {
        long count = downloadHistoryRepository.countByApp_Id(appId);
        return downloadHistoryMapper.toResponse(count);
    }
}
