package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.response.DownloadHistoryResponse;
import org.mapstruct.Mapper;

/**
 * DownloadHistory <-> DownloadHistoryDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 * Nested mapping için AppMapper ve UserMapper kullanılır.
 */
@Mapper(componentModel = "spring")
public interface DownloadHistoryMapper {
    default DownloadHistoryResponse toResponse(long downloadCount) {
        return new DownloadHistoryResponse(downloadCount);
    }
}
