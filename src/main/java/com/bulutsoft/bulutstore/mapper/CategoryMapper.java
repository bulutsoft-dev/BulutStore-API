package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.Category;
import com.bulutsoft.bulutstore.request.CategoryRequest;
import com.bulutsoft.bulutstore.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * Category <-> CategoryDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryResponse toResponse(Category category);
    Category toEntity(CategoryRequest request);
    List<CategoryResponse> toResponseList(List<Category> categories);
    List<Category> toEntityList(List<CategoryRequest> requests);
}
