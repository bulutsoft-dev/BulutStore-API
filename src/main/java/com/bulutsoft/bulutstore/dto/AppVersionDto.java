package com.bulutsoft.bulutstore.dto;

import lombok.*;
import java.time.LocalDateTime;

/**
 * Uygulama sürümüne ait dışa açılan alanları temsil eden DTO sınıfı.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppVersionDto {
    private Long id;
    private String version;
    private String apkPath;
    private LocalDateTime releaseDate;
    /**
     * Sürümün ait olduğu uygulama (AppDto)
     */
    private AppDto app;
}
