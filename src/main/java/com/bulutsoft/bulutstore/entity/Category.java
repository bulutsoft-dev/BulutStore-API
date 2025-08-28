package com.bulutsoft.bulutstore.entity;

/**
 * Uygulama kategorisi entity'si.
 * Uygulamalar, bir kategoriye ait olabilir.
 * Kategori adı benzersizdir.
 */
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    /** Benzersiz kategori ID'si */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Benzersiz kategori adı */
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    /** Bu kategoriye ait uygulamalar */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<App> apps;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id != null && id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
