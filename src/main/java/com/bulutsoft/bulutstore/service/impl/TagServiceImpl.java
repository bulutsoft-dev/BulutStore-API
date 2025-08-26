package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.entity.Tag;
import com.bulutsoft.bulutstore.mapper.TagMapper;
import com.bulutsoft.bulutstore.repos.TagRepository;
import com.bulutsoft.bulutstore.service.TagService;
import com.bulutsoft.bulutstore.request.TagRequest;
import com.bulutsoft.bulutstore.response.TagResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Etiket işlemlerinin iş mantığını ve transaction yönetimini sağlayan servis implementasyonu.
 * DTO <-> Entity dönüşümleri TagMapper ile yapılır.
 */
@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<TagResponse> getAllTags() {
        return tagMapper.toResponseList(tagRepository.findAll());
    }

    @Override
    public Optional<TagResponse> getTagById(Long id) {
        return tagRepository.findById(id).map(tagMapper::toResponse);
    }

    @Override
    @Transactional
    public TagResponse createTag(TagRequest tagRequest) {
        Tag tag = tagMapper.toEntity(tagRequest);
        return tagMapper.toResponse(tagRepository.save(tag));
    }

    @Override
    @Transactional
    public TagResponse updateTag(Long id, TagRequest tagRequest) {
        Tag tag = tagMapper.toEntity(tagRequest);
        tag.setId(id);
        return tagMapper.toResponse(tagRepository.save(tag));
    }

    @Override
    @Transactional
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Optional<TagResponse> getTagByName(String name) {
        return tagRepository.findByName(name).map(tagMapper::toResponse);
    }
}
