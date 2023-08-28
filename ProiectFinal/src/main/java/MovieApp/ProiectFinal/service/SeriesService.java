package MovieApp.ProiectFinal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import MovieApp.ProiectFinal.repository.SeriesRepository;

@Controller
@RequiredArgsConstructor

public class SeriesService {
    private final SeriesRepository seriesRepository;

}
