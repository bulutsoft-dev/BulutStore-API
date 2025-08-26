package com.bulutsoft.bulutstore.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

    @NotBlank(message = "Etiket adı boş olamaz.")
    @Size(max = 50)
    private String name;
}

