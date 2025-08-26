package com.bulutsoft.bulutstore.dto;

import lombok.*;

/**
 * Kategoriye ait dışa açılan alanları temsil eden DTO sınıfı.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long id;

    private String name;
}

