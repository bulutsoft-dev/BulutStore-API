package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.AppVersion;
import com.bulutsoft.bulutstore.request.AppVersionRequest;
import com.bulutsoft.bulutstore.response.AppVersionResponse;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * AppVersion <-> AppVersionDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 */
@Mapper(componentModel = "spring")
public interface AppVersionMapper {
    AppVersionResponse toResponse(AppVersion appVersion);
    List<AppVersionResponse> toResponseList(List<AppVersion> appVersions);
    AppVersion toEntity(AppVersionRequest request);
}
