package MovieApp.ProiectFinal.repository;

import MovieApp.ProiectFinal.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByTitle(String title);
}
