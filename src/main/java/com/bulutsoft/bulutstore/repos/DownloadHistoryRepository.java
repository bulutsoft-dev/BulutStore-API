package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.DownloadHistory;
import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * DownloadHistory entity'si için CRUD işlemlerini sağlayan repository arayüzü.
 * Kullanıcı ve uygulama bazında indirme geçmişi sorgulanabilir.
 */
@Repository
public interface DownloadHistoryRepository extends JpaRepository<DownloadHistory, Long> {
    /**
     * Belirli bir kullanıcıya ait tüm indirme kayıtlarını döndürür.
     * @param user kullanıcı
     * @return indirme geçmişi listesi
     */
    List<DownloadHistory> findByUser(User user);

    /**
     * Belirli bir uygulamaya ait tüm indirme kayıtlarını döndürür.
     * @param app uygulama
     * @return indirme geçmişi listesi
     */
    List<DownloadHistory> findByApp(App app);
}

