package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.request.DownloadHistoryRequest;
import com.bulutsoft.bulutstore.response.DownloadHistoryResponse;

import java.util.List;
import java.util.Optional;

/**
 * İndirme geçmişi işlemleri için servis arayüzü.
 * Temel CRUD ve uygulama/kullanıcıya özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler DownloadHistoryDto ile yapılır.
 */
public interface DownloadHistoryService {
    List<DownloadHistoryResponse> getAllDownloadHistories();
    Optional<DownloadHistoryResponse> getDownloadHistoryById(Long id);
    DownloadHistoryResponse createDownloadHistory(DownloadHistoryRequest request);
    DownloadHistoryResponse updateDownloadHistory(Long id, DownloadHistoryRequest request);
    void deleteDownloadHistory(Long id);
    List<DownloadHistoryResponse> getDownloadHistoriesByUser(Long userId);
    List<DownloadHistoryResponse> getDownloadHistoriesByApp(Long appId);
}
