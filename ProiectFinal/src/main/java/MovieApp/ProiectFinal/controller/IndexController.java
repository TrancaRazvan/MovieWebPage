package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.model.Movie;
import MovieApp.ProiectFinal.model.Series;
import MovieApp.ProiectFinal.service.MovieService;
import MovieApp.ProiectFinal.service.SeriesService;
import MovieApp.ProiectFinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final MovieService movieService;
    private final SeriesService seriesService;
    private final UserService userService;

    @GetMapping("/")
    public String showIndexPage(Model model) {
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);

        List<Series> serieses = seriesService.findAll();
        model.addAttribute("serieses", serieses);

        return "index.html";
    }
}
