package MovieApp.ProiectFinal.service;

import MovieApp.ProiectFinal.model.Genre;
import MovieApp.ProiectFinal.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreService {

    @Autowired
    private final GenreRepository genreRepository;

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }
    public List<Genre> findAll(){
        return genreRepository.findAll();
    }

    public Genre findById(Long genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }
}
