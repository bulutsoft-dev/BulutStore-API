package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.dto.ReviewDto;
import com.bulutsoft.bulutstore.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * Review <-> ReviewDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 * Nested mapping için AppMapper ve UserMapper kullanılır.
 */
@Mapper(componentModel = "spring", uses = {AppMapper.class, UserMapper.class})
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
    ReviewDto toDto(Review review);
    Review toEntity(ReviewDto reviewDto);
    List<ReviewDto> toDtoList(List<Review> reviews);
    List<Review> toEntityList(List<ReviewDto> reviewDtos);
}

