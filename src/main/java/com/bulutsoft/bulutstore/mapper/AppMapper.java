package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.request.AppRequest;
import com.bulutsoft.bulutstore.response.AppResponse;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * App <-> AppDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 * Nested mapping için UserMapper, CategoryMapper, TagMapper, AppVersionMapper kullanılır.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class, TagMapper.class, AppVersionMapper.class})
public interface AppMapper {
    AppResponse toResponse(App app);
    List<AppResponse> toResponseList(List<App> apps);
    App toEntity(AppRequest request);
}
