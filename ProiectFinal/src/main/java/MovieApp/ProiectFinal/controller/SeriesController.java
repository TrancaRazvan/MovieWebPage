package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.dto.MovieWithGenresDTO;
import MovieApp.ProiectFinal.dto.SeriesWithGenresDTO;
import MovieApp.ProiectFinal.model.Movie;
import MovieApp.ProiectFinal.model.Series;
import MovieApp.ProiectFinal.repository.SeriesRepository;
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
    @Autowired
    private final SeriesRepository seriesRepository;

    @PostMapping("/addSeries")
    public String  saveSeries(@ModelAttribute("series") Series series) {
        seriesService.saveSeries(series);
        return "redirect:/admin";
    }
    @GetMapping("/addSeries")
    public String showAddSeriesForm(Model model) {
        model.addAttribute("series", new Movie());
        return "add-series.html";
    }

    @GetMapping("/show")
    @ResponseBody
    public List<SeriesWithGenresDTO> showAllSeries() {
        return seriesService.findAll();
    }
    @GetMapping("/{seriesId}/{seriesTitle}")
    public String showSeriesDescription(@PathVariable Long seriesId, Model model){
        List<SeriesWithGenresDTO> series = seriesService.findById(seriesId);
        model.addAttribute("serieses", series);
        return "series.html";
    }
    @GetMapping("/{genre}")
    public String filterSeriesByGenre(@PathVariable String genre, Model model) {
        List<SeriesWithGenresDTO> series = seriesService.findAllSeriesWithGenre(genre);
        model.addAttribute("serieses", series);
        return "series-by-genre.html";
    }
    @GetMapping()
    public String showAllSeries(Model model) {
        List<SeriesWithGenresDTO> series = seriesService.findAll();
        model.addAttribute("serieses", series);
        return "seriespage.html";
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
    @PostMapping("/delete")
    public ResponseEntity<?> deleteSeriesById(@RequestParam Long seriesId){
        boolean deleted = seriesService.deleteById(seriesId);
        if (deleted){
            return ResponseEntity.ok("Series successfully deleted");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
