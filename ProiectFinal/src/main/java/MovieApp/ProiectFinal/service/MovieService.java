package MovieApp.ProiectFinal.service;

import MovieApp.ProiectFinal.model.Movie;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import MovieApp.ProiectFinal.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MovieService {
    private final MovieRepository movieRepository;

    public String createUser(Movie movie) {
        return "Create movie with id: " + movieRepository.save(movie).getId();
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public void deleteMovieById(int id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        movieRepository.deleteById(id);
    }

}
