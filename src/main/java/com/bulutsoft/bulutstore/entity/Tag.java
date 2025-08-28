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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id != null && id.equals(tag.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
