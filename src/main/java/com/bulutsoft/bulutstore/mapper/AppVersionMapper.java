package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.AppVersion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * AppVersion <-> AppVersionDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 */
@Mapper(componentModel = "spring")
public interface AppVersionMapper {
    AppVersionMapper INSTANCE = Mappers.getMapper(AppVersionMapper.class);
    AppVersionDto toDto(AppVersion appVersion);
    AppVersion toEntity(AppVersionDto appVersionDto);
    List<AppVersionDto> toDtoList(List<AppVersion> appVersions);
    List<AppVersion> toEntityList(List<AppVersionDto> appVersionDtos);
}

