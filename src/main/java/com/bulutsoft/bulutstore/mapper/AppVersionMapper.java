package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.AppVersion;
import com.bulutsoft.bulutstore.request.AppVersionRequest;
import com.bulutsoft.bulutstore.response.AppVersionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * AppVersion <-> AppVersionDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 */
@Mapper(componentModel = "spring")
public interface AppVersionMapper {
    AppVersionMapper INSTANCE = Mappers.getMapper(AppVersionMapper.class);
    AppVersionResponse toResponse(AppVersion appVersion);
    List<AppVersionResponse> toResponseList(List<AppVersion> appVersions);
    AppVersion toEntity(AppVersionRequest request);
}
