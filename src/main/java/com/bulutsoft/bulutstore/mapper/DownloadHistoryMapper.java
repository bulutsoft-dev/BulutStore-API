package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.DownloadHistory;
import com.bulutsoft.bulutstore.request.DownloadHistoryRequest;
import com.bulutsoft.bulutstore.response.DownloadHistoryResponse;
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
    DownloadHistoryResponse toResponse(DownloadHistory downloadHistory);
    List<DownloadHistoryResponse> toResponseList(List<DownloadHistory> downloadHistories);
    DownloadHistory toEntity(DownloadHistoryRequest request);
}
