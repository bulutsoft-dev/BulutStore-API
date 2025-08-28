package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AppVersion entity'si için CRUD işlemlerini sağlayan repository arayüzü.
 * Bir uygulamanın tüm sürümlerini listeleyebilir.
 */
@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {
    // Belirli bir App'a ait en son versiyonu bul
    java.util.Optional<AppVersion> findTopByAppIdOrderByReleaseDateDesc(Long appId);
}
