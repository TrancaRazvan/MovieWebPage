package MovieApp.ProiectFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "genres")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "genre_id")
    private Long id;
    private String title;

    @ManyToMany(mappedBy = "movieGenres")
    @JsonIgnore
    private Set<Movie> movies;

    @ManyToMany(mappedBy = "seriesGenres")
    @JsonIgnore
    private Set<Series> serieses = new HashSet<>();

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
