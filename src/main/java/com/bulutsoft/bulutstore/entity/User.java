package com.bulutsoft.bulutstore.entity;

/**
 * Uygulama kullanıcısı entity'si.
 * Tüm roller (USER, DEVELOPER, ADMIN) tek tabloda tutulur.
 * Kullanıcı adı, e-posta, şifre, rol ve statü bilgilerini içerir.
 * createdAt ve updatedAt alanları otomatik olarak güncellenir.
 */
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /** Benzersiz kullanıcı ID'si (otomatik artan) */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Benzersiz kullanıcı adı */
    @Column(nullable = false, unique = true, length = 50)
    private String username;

    /** Benzersiz e-posta adresi */
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    /** Şifre (hashlenmiş olarak saklanmalı) */
    @Column(nullable = false)
    private String password;

    /** Kullanıcı rolü (USER, DEVELOPER, ADMIN) */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    /** Kullanıcı statüsü (ACTIVE, PENDING, REJECTED) */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status;

    /** Kullanıcının developer başvurusu durumu (NONE, PENDING, APPROVED, REJECTED) */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private DeveloperApplicationStatus developerApplicationStatus = DeveloperApplicationStatus.NONE;

    /** Developer başvuru tarihi */
    private LocalDateTime developerApplicationDate;

    /** Oluşturulma zamanı */
    private LocalDateTime createdAt;
    /** Son güncellenme zamanı */
    private LocalDateTime updatedAt;

    /** Geliştirici görünen adı (isteğe bağlı) */
    @Column(length = 100)
    private String displayName;

    /** Geliştirici web sitesi (isteğe bağlı) */
    @Column(length = 255)
    private String website;

    /** Kullanıcının developer başvuru metni */
    @Column(length = 2000)
    private String developerApplicationText;

    /**
     * Kayıt eklenmeden önce zaman damgalarını ayarlar.
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Kayıt güncellenmeden önce updatedAt alanını günceller.
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
