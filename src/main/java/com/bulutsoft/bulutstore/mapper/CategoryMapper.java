package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.dto.CategoryDto;
import com.bulutsoft.bulutstore.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * Category <-> CategoryDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
    List<CategoryDto> toDtoList(List<Category> categories);
    List<Category> toEntityList(List<CategoryDto> categoryDtos);
}

