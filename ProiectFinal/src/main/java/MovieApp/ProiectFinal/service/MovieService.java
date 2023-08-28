package MovieApp.ProiectFinal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import MovieApp.ProiectFinal.repository.MovieRepository;

@Controller
@RequiredArgsConstructor

public class MovieService {
    private final MovieRepository movieRepository;
}
