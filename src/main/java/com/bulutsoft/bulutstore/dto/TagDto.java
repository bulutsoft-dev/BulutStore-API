package com.bulutsoft.bulutstore.dto;

import lombok.*;

/**
 * Etikete ait dışa açılan alanları temsil eden DTO sınıfı.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagDto {
    private Long id;

    private String name;
}

