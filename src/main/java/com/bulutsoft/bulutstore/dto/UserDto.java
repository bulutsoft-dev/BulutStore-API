package com.bulutsoft.bulutstore.dto;

import com.bulutsoft.bulutstore.entity.Role;
import com.bulutsoft.bulutstore.entity.UserStatus;
import lombok.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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

    @NotBlank(message = "Kullanıcı adı boş olamaz.")
    @Size(max = 50)
    private String username;

    @NotBlank(message = "E-posta boş olamaz.")
    @Email(message = "Geçerli bir e-posta adresi giriniz.")
    @Size(max = 100)
    private String email;

    private Role role;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

