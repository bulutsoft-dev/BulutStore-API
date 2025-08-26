package com.bulutsoft.bulutstore.dto;

import lombok.*;
import java.time.LocalDateTime;

/**
 * Uygulama indirme geçmişi için dışa açılan alanları temsil eden DTO sınıfı.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DownloadHistoryDto {
    private Long id;
    private AppDto app;
    private UserDto user;
    private LocalDateTime downloadedAt;
}

