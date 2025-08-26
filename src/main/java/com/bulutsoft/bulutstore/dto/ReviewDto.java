package com.bulutsoft.bulutstore.dto;

import lombok.*;
import java.time.LocalDateTime;

/**
 * Uygulama yorumu için dışa açılan alanları temsil eden DTO sınıfı.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {
    private Long id;
    private AppDto app;
    private UserDto user;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}

