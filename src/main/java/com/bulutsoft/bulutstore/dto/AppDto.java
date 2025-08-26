package com.bulutsoft.bulutstore.dto;

import com.bulutsoft.bulutstore.entity.AppStatus;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Uygulamaya ait dışa açılan alanları temsil eden DTO sınıfı.
 * İlişkili alanlar için ilgili DTO'lar veya id'ler kullanılır.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppDto {
    private Long id;
    private String name;
    private String description;
    private CategoryDto category;
    private UserDto developer;
    private AppStatus status;
    private Long downloadsCount;
    private Double avgRating;
    private List<TagDto> tags;
    private List<AppVersionDto> versions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

