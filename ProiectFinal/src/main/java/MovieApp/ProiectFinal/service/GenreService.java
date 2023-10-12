package MovieApp.ProiectFinal.service;

import MovieApp.ProiectFinal.model.Genre;
import MovieApp.ProiectFinal.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GenreService {

    @Autowired
    private final GenreRepository genreRepository;

    public boolean saveGenre(String name) {
       Optional<Genre> genre = genreRepository.findByTitle(name);
       if (genre.isPresent()){
           return false;
       }else {
           Genre newGenre = new Genre();
           newGenre.setTitle(name);
           genreRepository.save(newGenre);
           return true;
       }

    }
    public List<Genre> findAll(){
        return genreRepository.findAll();
    }

    public Genre findById(Long genreId) {
        return genreRepository.findById(genreId).orElse(null);
    }

    public boolean deleteById(Long genreId) {
        if (genreRepository.findById(genreId).isPresent()){
            genreRepository.deleteById(genreId);
            return true;
        }else{
            return false;
        }
    }
}
