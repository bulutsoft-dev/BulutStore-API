package com.bulutsoft.bulutstore.entity;

/**
 * Uygulama indirme geçmişi entity'si.
 * Kullanıcıların hangi uygulamayı ne zaman indirdiğini tutar.
 * Hem istatistik hem de kullanıcı geçmişi için kullanılır.
 * downloadedAt alanı otomatik atanır.
 */
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "download_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DownloadHistory {
    /** Benzersiz indirme kaydı ID'si */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** İndirilen uygulama */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", nullable = false)
    private App app;

    /** İndirme zamanı */
    private LocalDateTime downloadedAt;

    /**
     * Kayıt eklenmeden önce downloadedAt alanını ayarlar.
     */
    @PrePersist
    public void prePersist() {
        this.downloadedAt = LocalDateTime.now();
    }
}
