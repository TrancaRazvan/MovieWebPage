package MovieApp.ProiectFinal.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Data
@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Movie{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
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
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> movieGenres = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, releaseYear, rating, imageurl, creator, trailer);
    }

}
