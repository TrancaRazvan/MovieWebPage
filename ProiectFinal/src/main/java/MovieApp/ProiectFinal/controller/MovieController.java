package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.dto.MovieWithGenresDTO;
import MovieApp.ProiectFinal.model.Movie;
import MovieApp.ProiectFinal.repository.MovieRepository;
import MovieApp.ProiectFinal.service.MovieService;
import org.springframework.ui.Model;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private final MovieService movieService;
    @Autowired
    private final MovieRepository movieRepository;

    @PostMapping("/addMovie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping("/{movieId}/{movieTitle}")
    public String showMovieDescription(@PathVariable Long movieId,@PathVariable String movieTitle, Model model) {
        List<MovieWithGenresDTO> movie = movieService.findById(movieId);
        model.addAttribute("movies", movie);
        return "movie.html";
    }

    @GetMapping
    public String showAllMovies(Model model) {
        List<MovieWithGenresDTO> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "moviespage.html";
    }

    @GetMapping("/{genre}")
    public String filterMovieByGenre(@PathVariable String genre, Model model) {
       List<MovieWithGenresDTO> movies = movieService.findAllMoviesWithGenre(genre);
       model.addAttribute("movies", movies);
       return "movies-by-genre.html";
    }


    @GetMapping("/findById/{movieId}")
    @ResponseBody
    public List<MovieWithGenresDTO> findMovieById(@PathVariable Long movieId) {
        return movieService.findById(movieId);
    }

    @PostMapping("/save/{movieId}/{genreId}")
    @Transactional
    public ResponseEntity<?> saveGenreToMovie(@PathVariable Long movieId, @PathVariable Long genreId) {
        return movieService.addGenreToMovie(movieId, genreId);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteMovieById(@RequestParam Long movieId) {
        boolean deleted = movieService.deleteById(movieId);
        if (deleted) {
            return ResponseEntity.ok("Movie successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
