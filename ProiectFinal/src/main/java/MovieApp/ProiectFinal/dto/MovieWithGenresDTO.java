package MovieApp.ProiectFinal.dto;

import MovieApp.ProiectFinal.model.Genre;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class MovieWithGenresDTO {
    private Long id;
    private String title;
    private String description;
    private int releaseYear;
    private double rating;
    private String imageurl;
    private Set<Genre> movieGenres = new HashSet<>();
}