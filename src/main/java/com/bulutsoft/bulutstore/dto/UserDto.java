package com.bulutsoft.bulutstore.dto;

import com.bulutsoft.bulutstore.entity.Role;
import com.bulutsoft.bulutstore.entity.UserStatus;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Kullanıcıya ait dışa açılan alanları temsil eden DTO sınıfı.
 * Şifre gibi hassas bilgiler içermez.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String username;

    private String email;

    private Role role;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

