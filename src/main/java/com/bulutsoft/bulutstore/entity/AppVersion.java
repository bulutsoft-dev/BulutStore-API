package com.bulutsoft.bulutstore.entity;

/**
 * Uygulama sürüm entity'si.
 * Bir uygulamanın farklı sürümlerini ve APK dosya yolunu tutar.
 * Her App birden fazla AppVersion'a sahip olabilir.
 * releaseDate alanı otomatik atanır.
 */
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_versions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppVersion {
    /** Benzersiz sürüm ID'si */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Sürümün ait olduğu uygulama */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", nullable = false)
    private App app;

    /** Sürüm numarası (örn: 1.0.0) */
    @Column(nullable = false, length = 20)
    private String version;

    /** APK dosyasının sunucudaki yolu */
    private String apkPath;

    /** Sürümün yayınlanma tarihi */
    private LocalDateTime releaseDate;

    /**
     * Sürüm eklenmeden önce releaseDate alanını ayarlar.
     */
    @PrePersist
    public void prePersist() {
        this.releaseDate = LocalDateTime.now();
    }
}
