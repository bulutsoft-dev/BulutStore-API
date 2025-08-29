package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.request.DownloadHistoryRequest;
import com.bulutsoft.bulutstore.response.DownloadHistoryResponse;

/**
 * İndirme geçmişi işlemleri için servis arayüzü.
 * Temel CRUD ve uygulama/kullanıcıya özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler DownloadHistoryDto ile yapılır.
 */
public interface DownloadHistoryService {
    DownloadHistoryResponse createDownloadHistory(DownloadHistoryRequest request);
    DownloadHistoryResponse getDownloadCountByAppId(Long appId);
}
