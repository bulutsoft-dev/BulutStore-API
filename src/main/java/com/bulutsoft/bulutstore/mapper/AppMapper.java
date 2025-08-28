package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.App;
import com.bulutsoft.bulutstore.entity.AppVersion;
import com.bulutsoft.bulutstore.request.AppRequest;
import com.bulutsoft.bulutstore.response.AppResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

/**
 * App <-> AppDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 * Nested mapping için UserMapper, CategoryMapper, TagMapper, AppVersionMapper kullanılır.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class, TagMapper.class, AppVersionMapper.class})
public interface AppMapper {
    @Mappings({
        @Mapping(source = "iconUrl", target = "iconUrl"),
        @Mapping(source = "screenshotUrls", target = "screenshotUrls"),
        @Mapping(source = "shortDescription", target = "shortDescription"),
        @Mapping(source = "category", target = "category"),
        @Mapping(source = "developer", target = "developer"),
        @Mapping(source = "tags", target = "tags"),
        @Mapping(source = "fileUrl", target = "fileUrl"),
        @Mapping(target = "versionName", source = "app", qualifiedByName = "getLatestVersionName")
    })
    AppResponse toResponse(App app);

    List<AppResponse> toResponseList(List<App> apps);

    @Mapping(source = "iconUrl", target = "iconUrl")
    @Mapping(source = "screenshotUrls", target = "screenshotUrls")
    @Mapping(source = "shortDescription", target = "shortDescription")
    @Mapping(source = "fileUrl", target = "fileUrl")
    App toEntity(AppRequest request);

    @Named("getLatestVersionName")
    default String getLatestVersionName(App app) {
        if (app == null) return null;
        try {
            java.lang.reflect.Field versionsField = app.getClass().getDeclaredField("versions");
            versionsField.setAccessible(true);
            java.util.List<AppVersion> versions = (java.util.List<AppVersion>) versionsField.get(app);
            if (versions == null || versions.isEmpty()) return null;
            return versions.stream()
                .max(java.util.Comparator.comparing(AppVersion::getReleaseDate))
                .map(AppVersion::getVersion)
                .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
