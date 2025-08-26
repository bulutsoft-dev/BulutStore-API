package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.DownloadHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DownloadHistory entity'si için CRUD işlemlerini sağlayan repository arayüzü.
 * Kullanıcı ve uygulama bazında indirme geçmişi sorgulanabilir.
 */
@Repository
public interface DownloadHistoryRepository extends JpaRepository<DownloadHistory, Long> {
}
