package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.dto.TagDto;
import com.bulutsoft.bulutstore.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * Tag <-> TagDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 */
@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    TagDto toDto(Tag tag);
    Tag toEntity(TagDto tagDto);
    List<TagDto> toDtoList(List<Tag> tags);
    List<Tag> toEntityList(List<TagDto> tagDtos);
}

