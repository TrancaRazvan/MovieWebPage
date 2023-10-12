package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.dto.MovieWithGenresDTO;
import MovieApp.ProiectFinal.dto.SeriesWithGenresDTO;
import MovieApp.ProiectFinal.model.Movie;
import MovieApp.ProiectFinal.model.Series;
import MovieApp.ProiectFinal.service.MovieService;
import MovieApp.ProiectFinal.service.SeriesService;
import MovieApp.ProiectFinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class IndexController {
    @Autowired
    private final MovieService movieService;
    @Autowired
    private final SeriesService seriesService;
    @Autowired
    private final UserService userService;


    @GetMapping("/")
    public String showIndexPage(Model model, Authentication authentication) {

        List<MovieWithGenresDTO> movies = movieService.findAll();
        model.addAttribute("movies", movies);

        List<SeriesWithGenresDTO> serieses = seriesService.findAll();
        model.addAttribute("serieses", serieses);

        return "index.html";
    }
    @GetMapping("/home")
    public String showHomePage(Model model, Authentication authentication) {

        List<MovieWithGenresDTO> movies = movieService.findAll();
        model.addAttribute("movies", movies);

        List<SeriesWithGenresDTO> serieses = seriesService.findAll();
        model.addAttribute("serieses", serieses);

        return "home.html";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleError() {
        return "error";
    }


}
