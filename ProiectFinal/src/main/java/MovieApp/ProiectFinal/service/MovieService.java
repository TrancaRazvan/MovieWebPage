package MovieApp.ProiectFinal.service;

import MovieApp.ProiectFinal.dto.MovieWithGenresDTO;
import MovieApp.ProiectFinal.model.Genre;
import MovieApp.ProiectFinal.model.Movie;
import MovieApp.ProiectFinal.repository.GenreRepository;
import MovieApp.ProiectFinal.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class MovieService {
    @Autowired
    private final MovieRepository movieRepository;
    @Autowired
    private final GenreRepository genreRepository;

    public List<MovieWithGenresDTO> findAll() {
        return movieRepository.findAll().stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
   public List<MovieWithGenresDTO> findAllMoviesWithGenre(String genre){
        List<Movie> moviesWithGenre = new ArrayList<>();
        List<Movie> movies = movieRepository.findAll();
        for (Movie movie: movies){
            Set<Genre> genres = movie.getMovieGenres();
            for (Genre movieGenre : genres){
                if (movieGenre.toString().equalsIgnoreCase(genre)){
                    moviesWithGenre.add(movie);
                }
            }
        }
        return moviesWithGenre.stream().map(this::convertEntityToDto).collect(Collectors.toList());
   }
    public ResponseEntity<?> saveMovie(Movie movie) {
        if (movie != null) {
            movieRepository.save(movie);
            return new ResponseEntity<>(convertEntityToDto(movie), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public List<MovieWithGenresDTO> findById(Long movieId) {
        return movieRepository.findById(movieId).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public boolean deleteById(Long movieId) {
        if (movieRepository.findById(movieId).isPresent()) {
            movieRepository.deleteById(movieId);
            return true;
        }
        return false;
    }

    @Transactional
    public ResponseEntity<?> addGenreToMovie(Long movieId, Long genreId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (movie != null && genre != null) {
            Set<Genre> movieGenres = movie.getMovieGenres();
            if (movieGenres.contains(genre)) {
                return ResponseEntity.ok("That genre is already added.");
            }
            movieGenres.add(genre);
            movie.setMovieGenres(movieGenres);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertEntityToDto(movie));
        }
        return ResponseEntity.notFound().build();
    }

    private MovieWithGenresDTO convertEntityToDto(Movie movie) {
        MovieWithGenresDTO movieWithGenresDTO = new MovieWithGenresDTO();
        movieWithGenresDTO.setId(movie.getId());
        movieWithGenresDTO.setImageurl(movie.getImageurl());
        movieWithGenresDTO.setDescription(movie.getDescription());
        movieWithGenresDTO.setTitle(movie.getTitle());
        movieWithGenresDTO.setRating(movie.getRating());
        movieWithGenresDTO.setReleaseYear(movie.getReleaseYear());
        movieWithGenresDTO.setMovieGenres(movie.getMovieGenres());
        movieWithGenresDTO.setCreator(movie.getCreator());
        movieWithGenresDTO.setTrailer(movie.getTrailer());
        return movieWithGenresDTO;
    }

    public List<Movie> searchMoviesByTitle(String query) {
        return movieRepository.findByTitle(query);
    }
}
