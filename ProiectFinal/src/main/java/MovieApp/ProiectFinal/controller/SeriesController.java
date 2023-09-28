package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.DTO.SeriesWithGenresDTO;
import MovieApp.ProiectFinal.model.Genre;
import MovieApp.ProiectFinal.model.Movie;
import MovieApp.ProiectFinal.model.Series;
import MovieApp.ProiectFinal.service.GenreService;
import MovieApp.ProiectFinal.service.SeriesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private final SeriesService seriesService;
    @Autowired
    private final GenreService genreService;

    @PostMapping("/save")
    public ResponseEntity<Series> saveSeries(@RequestBody Series series) {
        Series savedSeries = seriesService.saveSeries(series);
        if (savedSeries != null) {
            return new ResponseEntity<Series>(savedSeries, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Series>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/show")
    @ResponseBody
    public List<SeriesWithGenresDTO> showAllSeries() {
        return seriesService.findAllWithGenres();
    }

    @PostMapping("/save/{seriesId}/{genreId}")
    @Transactional
    public ResponseEntity<?> saveGenreToSeries(@PathVariable Long seriesId, @PathVariable Long genreId) {
        Series series = seriesService.findById(seriesId);
        Genre genre = genreService.findById(genreId);
        if (genre != null && series != null) {
            Set<Genre> genres = series.getSeriesGenres();

            if (genres == null) {
                genres = new HashSet<>();
            }

            if (!genres.contains(genre)) {
                genres.add(genre);
                series.setSeriesGenres(genres);
                seriesService.saveSeries(series);
                return new ResponseEntity<Series>(series, HttpStatus.CREATED);
            } else {
                return ResponseEntity.ok("That genre is already added.");
            }

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
