package MovieApp.ProiectFinal.service;

import MovieApp.ProiectFinal.model.Series;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import MovieApp.ProiectFinal.repository.SeriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class SeriesService {
    private final SeriesRepository seriesRepository;

    public List<Series> findAll() {
        return seriesRepository.findAll();
    }
}
