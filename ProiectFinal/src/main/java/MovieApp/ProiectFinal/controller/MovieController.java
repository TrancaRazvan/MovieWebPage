package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.model.Genre;
import MovieApp.ProiectFinal.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import MovieApp.ProiectFinal.service.MovieService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    @PostMapping("/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){
        Movie savedMovie = movieService.saveMovie(movie);
        if (savedMovie != null){
            return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Movie>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/show")
    @ResponseBody
    public List<Movie> showAllMovies(){
        return movieService.findAll();
    }
}
