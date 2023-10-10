package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.dto.MovieWithGenresDTO;
import MovieApp.ProiectFinal.model.Movie;
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

    @PostMapping("/addMovie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }
    @GetMapping("/{movieId}")
    public String showMovieDescription(@PathVariable Long movieId, Model model){
        List<MovieWithGenresDTO> movie = movieService.findById(movieId);
            model.addAttribute("movies", movie);
            return "movie.html";
    }

    @GetMapping()
    public String showAllMovies(Model model) {
        List<MovieWithGenresDTO> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "movies.html";
    }

    @GetMapping("/findById/{movieId}")
    @ResponseBody
    public List<MovieWithGenresDTO> findMovieById(@PathVariable Long movieId) {
        return movieService.findById(movieId);
    }
    @PostMapping("/save/{movieId}/{genreId}")
    @Transactional
    public ResponseEntity<?> saveGenreToSeries(@PathVariable Long movieId, @PathVariable Long genreId) {
        return movieService.addGenreToMovie(movieId, genreId);
    }
    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Long movieId){
        boolean deleted = movieService.deleteById(movieId);
        if (deleted){
            return ResponseEntity.ok("Movie successfully deleted");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
