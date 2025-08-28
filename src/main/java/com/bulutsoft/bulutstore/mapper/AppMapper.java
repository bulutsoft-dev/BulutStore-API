package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.request.AppRequest;
import com.bulutsoft.bulutstore.response.AppResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

/**
 * App <-> AppDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 * Nested mapping için UserMapper, CategoryMapper, TagMapper, AppVersionMapper kullanılır.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class, TagMapper.class, AppVersionMapper.class})
public interface AppMapper {
    @Mappings({
        @Mapping(source = "iconUrl", target = "iconUrl"),
        @Mapping(source = "screenshotUrls", target = "screenshotUrls"),
        @Mapping(source = "developer.displayName", target = "developerDisplayName"),
        @Mapping(source = "developer.website", target = "developerWebsite"),
        @Mapping(source = "shortDescription", target = "shortDescription"),
        @Mapping(source = "category.id", target = "categoryId"),
        @Mapping(source = "developer.id", target = "developerId"),
        @Mapping(source = "tags", target = "tagIds", qualifiedByName = "tagsToIds")
    })
    AppResponse toResponse(App app);

    List<AppResponse> toResponseList(List<App> apps);

    @Mapping(source = "iconUrl", target = "iconUrl")
    @Mapping(source = "screenshotUrls", target = "screenshotUrls")
    @Mapping(source = "shortDescription", target = "shortDescription")
    App toEntity(AppRequest request);

    @Named("tagsToIds")
    default java.util.List<Long> tagsToIds(java.util.List<com.bulutsoft.bulutstore.entity.Tag> tags) {
        if (tags == null) return null;
        return tags.stream().map(com.bulutsoft.bulutstore.entity.Tag::getId).collect(Collectors.toList());
    }
}
