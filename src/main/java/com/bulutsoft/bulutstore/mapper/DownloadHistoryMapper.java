package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.dto.DownloadHistoryDto;
import com.bulutsoft.bulutstore.entity.DownloadHistory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * DownloadHistory <-> DownloadHistoryDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 * Nested mapping için AppMapper ve UserMapper kullanılır.
 */
@Mapper(componentModel = "spring", uses = {AppMapper.class, UserMapper.class})
public interface DownloadHistoryMapper {
    DownloadHistoryMapper INSTANCE = Mappers.getMapper(DownloadHistoryMapper.class);
    DownloadHistoryDto toDto(DownloadHistory downloadHistory);
    DownloadHistory toEntity(DownloadHistoryDto downloadHistoryDto);
    List<DownloadHistoryDto> toDtoList(List<DownloadHistory> downloadHistories);
    List<DownloadHistory> toEntityList(List<DownloadHistoryDto> downloadHistoryDtos);
}

