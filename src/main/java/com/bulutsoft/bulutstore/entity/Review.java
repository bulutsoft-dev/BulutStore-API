package com.bulutsoft.bulutstore.entity;

/**
 * Uygulama yorumu entity'si.
 * Kullanıcıların uygulamalara verdiği puan ve yorumları tutar.
 * Her kullanıcı bir uygulamaya yalnızca bir yorum yapabilir (unique constraint).
 * createdAt alanı otomatik atanır.
 */
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews", uniqueConstraints = {@UniqueConstraint(columnNames = {"app_id", "user_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    /** Benzersiz yorum ID'si */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Yorumun ait olduğu uygulama */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", nullable = false)
    private App app;

    /** Yorumu yapan kullanıcı */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** Puan (1-5 arası) */
    @Column(nullable = false)
    private int rating;

    /** Yorum metni */
    @Lob
    private String comment;

    /** Yorumun oluşturulma zamanı */
    private LocalDateTime createdAt;

    /**
     * Yorum eklenmeden önce createdAt alanını ayarlar.
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
