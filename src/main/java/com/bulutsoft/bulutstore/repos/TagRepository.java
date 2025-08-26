package com.bulutsoft.bulutstore.repos;

import com.bulutsoft.bulutstore.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Tag entity'si için CRUD işlemlerini sağlayan repository arayüzü.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
