package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.AppVersion;
import com.bulutsoft.bulutstore.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * AppVersion entity'si için CRUD işlemlerini sağlayan repository arayüzü.
 * Bir uygulamanın tüm sürümlerini listeleyebilir.
 */
@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {
    /**
     * Belirli bir uygulamanın tüm sürümlerini döndürür.
     * @param app uygulama
     * @return sürüm listesi
     */
    List<AppVersion> findByApp(App app);
}

