package com.bulutsoft.bulutstore.service;

import com.bulutsoft.bulutstore.request.TagRequest;
import com.bulutsoft.bulutstore.response.TagResponse;

import java.util.List;
import java.util.Optional;

/**
 * Etiket işlemleri için servis arayüzü.
 * Temel CRUD ve etikete özel iş mantığı metotlarını tanımlar.
 * Tüm işlemler TagDto ile yapılır.
 */
public interface TagService {
    List<TagResponse> getAllTags();
    Optional<TagResponse> getTagById(Long id);
    TagResponse createTag(TagRequest tagRequest);
    TagResponse updateTag(Long id, TagRequest tagRequest);
    void deleteTag(Long id);
    Optional<TagResponse> getTagByName(String name);
}
