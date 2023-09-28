package MovieApp.ProiectFinal.service;

import MovieApp.ProiectFinal.DTO.SeriesWithGenresDTO;
import MovieApp.ProiectFinal.model.Genre;
import MovieApp.ProiectFinal.model.Series;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import MovieApp.ProiectFinal.repository.SeriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class SeriesService {
    private final SeriesRepository seriesRepository;

    public List<Series> findAll() {
        return seriesRepository.findAll();
    }

    public Series saveSeries(Series series) {
        return seriesRepository.save(series);
    }

    public Series findById(Long seriesId) {
        return seriesRepository.findById(seriesId).orElse(null);
    }

    public List<SeriesWithGenresDTO> findAllWithGenres() {
        List<Series> seriesList = seriesRepository.findAll();
        return seriesList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private SeriesWithGenresDTO convertToDTO(Series series) {
        SeriesWithGenresDTO dto = new SeriesWithGenresDTO();
        dto.setId(series.getId());
        dto.setTitle(series.getTitle());
        dto.setDescription(series.getDescription());
        dto.setReleaseYear(series.getReleaseYear());
        dto.setRating(series.getRating());
        dto.setImageurl(series.getImageurl());

        List<String> genreNames = series.getSeriesGenres().stream()
                .map(Genre::getTitle)
                .collect(Collectors.toList());
        dto.setGenres(genreNames);
        return dto;
    }
}
