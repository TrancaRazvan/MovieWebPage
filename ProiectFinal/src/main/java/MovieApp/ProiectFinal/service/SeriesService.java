package MovieApp.ProiectFinal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import MovieApp.ProiectFinal.repository.SeriesRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class SeriesService {
    private final SeriesRepository seriesRepository;

}
