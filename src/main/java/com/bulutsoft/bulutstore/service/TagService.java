package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.dto.TagDto;
import java.util.List;
import java.util.Optional;

/**
 * Etiket işlemleri için servis arayüzü.
 * Temel CRUD ve etikete özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler TagDto ile yapılır.
 */
public interface TagService {
    List<TagDto> getAllTags();
    Optional<TagDto> getTagById(Long id);
    TagDto createTag(TagDto tagDto);
    TagDto updateTag(Long id, TagDto tagDto);
    void deleteTag(Long id);
    Optional<TagDto> getTagByName(String name);
}
