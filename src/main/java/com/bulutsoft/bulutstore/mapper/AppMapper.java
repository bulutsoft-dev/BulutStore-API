package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.App;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * App <-> AppDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 * Nested mapping için UserMapper, CategoryMapper, TagMapper, AppVersionMapper kullanılır.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class, TagMapper.class, AppVersionMapper.class})
public interface AppMapper {
    AppMapper INSTANCE = Mappers.getMapper(AppMapper.class);
    AppDto toDto(App app);
    App toEntity(AppDto appDto);
    List<AppDto> toDtoList(List<App> apps);
    List<App> toEntityList(List<AppDto> appDtos);
}

