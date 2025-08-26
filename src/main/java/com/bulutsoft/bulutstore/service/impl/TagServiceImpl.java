package com.bulutsoft.bulutstore.service.impl;

import com.bulutsoft.bulutstore.dto.TagDto;
import com.bulutsoft.bulutstore.entity.Tag;
import com.bulutsoft.bulutstore.mapper.TagMapper;
import com.bulutsoft.bulutstore.repos.TagRepository;
import com.bulutsoft.bulutstore.service.TagService;
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
    public List<TagDto> getAllTags() {
        return tagMapper.toDtoList(tagRepository.findAll());
    }

    @Override
    public Optional<TagDto> getTagById(Long id) {
        return tagRepository.findById(id).map(tagMapper::toDto);
    }

    @Override
    @Transactional
    public TagDto createTag(TagDto tagDto) {
        Tag tag = tagMapper.toEntity(tagDto);
        return tagMapper.toDto(tagRepository.save(tag));
    }

    @Override
    @Transactional
    public TagDto updateTag(Long id, TagDto tagDto) {
        Tag tag = tagMapper.toEntity(tagDto);
        tag.setId(id);
        return tagMapper.toDto(tagRepository.save(tag));
    }

    @Override
    @Transactional
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Optional<TagDto> getTagByName(String name) {
        return tagRepository.findByName(name).map(tagMapper::toDto);
    }
}
