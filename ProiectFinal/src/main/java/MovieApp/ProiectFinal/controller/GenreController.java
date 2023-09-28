package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.model.Genre;
import MovieApp.ProiectFinal.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private final GenreService genreService;

    @PostMapping("/save")
    public ResponseEntity<Genre> saveGenre(@RequestBody Genre genre){
        Genre savedGenre = genreService.saveGenre(genre);
        if(savedGenre != null){
            return new ResponseEntity<Genre>(savedGenre, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Genre>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/show")
    @ResponseBody
    public List<Genre> showAllGenres(){
        return genreService.findAll();
    }
}
