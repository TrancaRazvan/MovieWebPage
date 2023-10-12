package MovieApp.ProiectFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Data
@Entity
@Table(name = "series")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Series{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "series_id")
    private Long id;
    private String title;
    private String description;
    private int releaseYear;
    private double rating;
    private String imageurl;
    private String creator;
    private String trailer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(name = "series_genre",
            joinColumns = @JoinColumn(name = "series_id"),
            inverseJoinColumns =@JoinColumn(name = "genre_id"))
    private Set<Genre> seriesGenres = new HashSet<>();
    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, releaseYear, rating, imageurl,creator, trailer);
    }

    @Override
    public String toString() {
        return "Series{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

}
