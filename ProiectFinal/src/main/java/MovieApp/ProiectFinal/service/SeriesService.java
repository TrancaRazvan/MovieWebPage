package MovieApp.ProiectFinal.service;

import MovieApp.ProiectFinal.dto.SeriesWithGenresDTO;
import MovieApp.ProiectFinal.model.Genre;
import MovieApp.ProiectFinal.model.Series;
import MovieApp.ProiectFinal.repository.GenreRepository;
import MovieApp.ProiectFinal.repository.SeriesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class SeriesService {
    @Autowired
    private final SeriesRepository seriesRepository;
    @Autowired
    private final GenreRepository genreRepository;

    public List<SeriesWithGenresDTO> findAll() {
        return seriesRepository.findAll().stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> saveSeries(Series series) {
        if (series != null) {
            seriesRepository.save(series);
            return new ResponseEntity<>(series, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public List<SeriesWithGenresDTO> findById(Long seriesId) {
        return seriesRepository.findById(seriesId).stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public boolean deleteById(Long seriesId) {
        if (seriesRepository.findById(seriesId).isPresent()) {
            seriesRepository.deleteById(seriesId);
            return true;
        }
        return false;
    }

    @Transactional
    public ResponseEntity<?> addGenreToSeries(Long seriesId, Long genreId) {
        Series series = seriesRepository.findById(seriesId).orElse(null);
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (series != null && genre != null) {
            Set<Genre> seriesGenres = series.getSeriesGenres();
            if (seriesGenres.contains(genre)) {
                return ResponseEntity.ok("That genre is already added.");
            }
            seriesGenres.add(genre);
            series.setSeriesGenres(seriesGenres);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertEntityToDto(series));
        }
        return ResponseEntity.notFound().build();
    }

    private SeriesWithGenresDTO convertEntityToDto(Series series) {
        SeriesWithGenresDTO seriesWithGenresDTO = new SeriesWithGenresDTO();
        seriesWithGenresDTO.setId(series.getId());
        seriesWithGenresDTO.setImageurl(series.getImageurl());
        seriesWithGenresDTO.setDescription(series.getDescription());
        seriesWithGenresDTO.setTitle(series.getTitle());
        seriesWithGenresDTO.setRating(series.getRating());
        seriesWithGenresDTO.setReleaseYear(series.getReleaseYear());
        seriesWithGenresDTO.setSeriesGenres(series.getSeriesGenres());
        return seriesWithGenresDTO;
    }

}
