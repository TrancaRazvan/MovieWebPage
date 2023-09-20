package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import MovieApp.ProiectFinal.service.MovieService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/movies")
    public String showAllMovies(Model model) {
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "movieBox.html";
    }
}
