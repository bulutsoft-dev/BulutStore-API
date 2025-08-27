package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.request.AppRequest;
import com.bulutsoft.bulutstore.response.AppResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * App <-> AppDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 * Nested mapping için UserMapper, CategoryMapper, TagMapper, AppVersionMapper kullanılır.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class, TagMapper.class, AppVersionMapper.class})
public interface AppMapper {
    @Mapping(source = "iconUrl", target = "iconUrl")
    @Mapping(source = "screenshotUrls", target = "screenshotUrls")
    @Mapping(source = "developer.displayName", target = "developerDisplayName")
    @Mapping(source = "developer.website", target = "developerWebsite")
    AppResponse toResponse(App app);
    List<AppResponse> toResponseList(List<App> apps);
    @Mapping(source = "iconUrl", target = "iconUrl")
    @Mapping(source = "screenshotUrls", target = "screenshotUrls")
    App toEntity(AppRequest request);
}
