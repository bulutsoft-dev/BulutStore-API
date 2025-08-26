package com.bulutsoft.bulutstore.entity;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_id", nullable = false)
    private App app;

    @Column(nullable = false, length = 20)
    private String version;

    private String apkPath;
    private LocalDateTime releaseDate;

    @PrePersist
    public void prePersist() {
        this.releaseDate = LocalDateTime.now();
    }
}

