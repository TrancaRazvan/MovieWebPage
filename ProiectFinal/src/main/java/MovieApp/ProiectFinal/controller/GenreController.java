package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.model.Genre;
import MovieApp.ProiectFinal.service.GenreService;
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
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private final GenreService genreService;

    @PostMapping("/save")
    public String saveGenre(@RequestParam String text) {
        boolean saved = genreService.saveGenre(text);
        if (saved){
            return "redirect:/genre/show";
        } else {
            return "error.html";
        }
    }

    @GetMapping("/show")
    public String showAllGenres(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "all-genres.html";
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteSeriesById(@RequestParam Long genreId) {
        boolean deleted = genreService.deleteById(genreId);
        if (deleted) {
            return ResponseEntity.ok("Genre successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
