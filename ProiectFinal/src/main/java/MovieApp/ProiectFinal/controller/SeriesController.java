package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.dto.MovieWithGenresDTO;
import MovieApp.ProiectFinal.dto.SeriesWithGenresDTO;
import MovieApp.ProiectFinal.model.Series;
import MovieApp.ProiectFinal.service.GenreService;
import MovieApp.ProiectFinal.service.SeriesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private final SeriesService seriesService;
    @Autowired
    private final GenreService genreService;

    @PostMapping("/addSeries")
    public ResponseEntity<?> saveSeries(@RequestBody Series series) {
        return seriesService.saveSeries(series);
    }

    @GetMapping("/show")
    @ResponseBody
    public List<SeriesWithGenresDTO> showAllSeries() {
        return seriesService.findAll();
    }
    @GetMapping("/{seriesId}")
    public String showMovieDescription(@PathVariable Long seriesId, Model model){
        List<SeriesWithGenresDTO> series = seriesService.findById(seriesId);
        model.addAttribute("serieses", series);
        return "series.html";
    }
    @GetMapping()
    public String showAllSeries(Model model) {
        List<SeriesWithGenresDTO> series = seriesService.findAll();
        model.addAttribute("serieses", series);
        return "serieses.html";
    }

    @GetMapping("/findById/{seriesId}")
    @ResponseBody
    public List<SeriesWithGenresDTO> findSeriesById(@PathVariable Long seriesId) {
        return seriesService.findById(seriesId);
    }
    @PostMapping("/save/{seriesId}/{genreId}")
    @Transactional
    public ResponseEntity<?> saveGenreToSeries(@PathVariable Long seriesId, @PathVariable Long genreId) {
        return seriesService.addGenreToSeries(seriesId, genreId);
    }
    @DeleteMapping("/delete/{seriesId}")
    public ResponseEntity<?> deleteSeriesById(@PathVariable Long seriesId){
        boolean deleted = seriesService.deleteById(seriesId);
        if (deleted){
            return ResponseEntity.ok("Series successfully deleted");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
