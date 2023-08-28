package MovieApp.ProiectFinal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import MovieApp.ProiectFinal.service.MovieService;

@RequiredArgsConstructor
@RestController
public class MovieController {
    private final MovieService movieService;


}
