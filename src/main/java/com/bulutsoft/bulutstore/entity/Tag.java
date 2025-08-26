package com.bulutsoft.bulutstore.entity;

/**
 * Uygulama etiketi entity'si.
 * Uygulamalar, birden fazla etikete sahip olabilir (ManyToMany).
 * Etiket adı benzersizdir.
 */
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {
    /** Benzersiz etiket ID'si */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Benzersiz etiket adı */
    @Column(nullable = false, unique = true)
    private String name;

    /** Bu etikete sahip uygulamalar */
    @ManyToMany(mappedBy = "tags")
    private List<App> apps;
}
