package com.bulutsoft.bulutstore.service;

import java.util.List;
import java.util.Optional;

/**
 * İndirme geçmişi işlemleri için servis arayüzü.
 * Temel CRUD ve uygulama/kullanıcıya özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler DownloadHistoryDto ile yapılır.
 */
public interface DownloadHistoryService {
    List<DownloadHistoryDto> getAllDownloadHistories();
    Optional<DownloadHistoryDto> getDownloadHistoryById(Long id);
    DownloadHistoryDto createDownloadHistory(DownloadHistoryDto downloadHistoryDto);
    DownloadHistoryDto updateDownloadHistory(Long id, DownloadHistoryDto downloadHistoryDto);
    void deleteDownloadHistory(Long id);
    List<DownloadHistoryDto> getDownloadHistoriesByUser(Long userId);
    List<DownloadHistoryDto> getDownloadHistoriesByApp(Long appId);
}
