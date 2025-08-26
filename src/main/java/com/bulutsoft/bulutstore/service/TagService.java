package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.entity.Tag;
import java.util.List;
import java.util.Optional;

/**
 * Etiket işlemleri için servis arayüzü.
 * Temel CRUD ve etikete özel iş mantığı metotlarını tanımlar.
 */
public interface TagService {
    List<Tag> getAllTags();
    Optional<Tag> getTagById(Long id);
    Tag createTag(Tag tag);
    Tag updateTag(Long id, Tag tag);
    void deleteTag(Long id);
    Optional<Tag> getTagByName(String name);
}

