package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Tag entity'si için CRUD işlemlerini sağlayan repository arayüzü.
 * Etiket adına göre arama fonksiyonu içerir.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    /**
     * Etiket adına göre arama yapar.
     * @param name etiket adı
     * @return etiket (varsa)
     */
    Optional<Tag> findByName(String name);
}

