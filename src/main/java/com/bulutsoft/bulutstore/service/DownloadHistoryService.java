package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.entity.DownloadHistory;
import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import java.util.List;
import java.util.Optional;

/**
 * İndirme geçmişi işlemleri için servis arayüzü.
 * Temel CRUD ve uygulama/kullanıcıya özel iş mantığı metotlarını tanımlar.
 */
public interface DownloadHistoryService {
    List<DownloadHistory> getAllDownloadHistories();
    Optional<DownloadHistory> getDownloadHistoryById(Long id);
    DownloadHistory createDownloadHistory(DownloadHistory downloadHistory);
    DownloadHistory updateDownloadHistory(Long id, DownloadHistory downloadHistory);
    void deleteDownloadHistory(Long id);
    List<DownloadHistory> getDownloadHistoriesByUser(User user);
    List<DownloadHistory> getDownloadHistoriesByApp(App app);
}

