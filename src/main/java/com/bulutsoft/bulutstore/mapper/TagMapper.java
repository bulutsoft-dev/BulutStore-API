package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.Tag;
import com.bulutsoft.bulutstore.request.TagRequest;
import com.bulutsoft.bulutstore.response.TagResponse;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * Tag <-> TagDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 */
@Mapper(componentModel = "spring")
public interface TagMapper {
    TagResponse toResponse(Tag tag);
    Tag toEntity(TagRequest tagRequest);
    List<TagResponse> toResponseList(List<Tag> tags);
}
