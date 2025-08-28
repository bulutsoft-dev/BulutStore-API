package com.bulutsoft.bulutstore.entity;

/**
 * Uygulama entity'si.
 * Bir uygulamanın temel bilgilerini, kategori, geliştirici, etiketler ve durumunu tutar.
 * Uygulamanın indirilme ve puan ortalaması istatistiklerini içerir.
 * createdAt ve updatedAt alanları otomatik güncellenir.
 */
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "apps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class App {
    /** Benzersiz uygulama ID'si */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Uygulama adı */
    @Column(nullable = false, length = 100)
    private String name;

    /** Uygulama açıklaması */
    @Lob
    private String description;

    /** Kısa açıklama (Play Store'daki gibi) */
    @Column(length = 255)
    private String shortDescription;

    /** Uygulamanın ait olduğu kategori */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    /** Uygulamanın geliştiricisi (User tablosundan) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id", nullable = false)
    private User developer;

    /** Uygulamanın onay durumu (PENDING, APPROVED, REJECTED) */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AppStatus status;

    /** Toplam indirilme sayısı */
    private Long downloadsCount = 0L;
    /** Ortalama puan */
    private Double avgRating = 0.0;

    /** Uygulamanın etiketleri (ManyToMany) */
    @ManyToMany
    @JoinTable(
        name = "app_tags",
        joinColumns = @JoinColumn(name = "app_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    /** Oluşturulma zamanı */
    private LocalDateTime createdAt;
    /** Son güncellenme zamanı */
    private LocalDateTime updatedAt;

    /** Uygulama ikonunun URL'si */
    private String iconUrl;

    /** Uygulamanın ekran görüntüleri (virgülle ayrılmış URL listesi) */
    @ElementCollection
    @CollectionTable(name = "app_screenshots", joinColumns = @JoinColumn(name = "app_id"))
    @Column(name = "screenshot_url")
    private List<String> screenshotUrls;

    /** Uygulama dosyası URL'si */
    @Column(name = "file_url", length = 255)
    private String fileUrl;

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
}
