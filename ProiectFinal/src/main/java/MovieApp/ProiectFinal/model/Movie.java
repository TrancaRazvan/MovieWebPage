package MovieApp.ProiectFinal.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@Table(name = "movies")
@RequiredArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int releaseYear;

    private double rating;
    private String imageurl;

}
