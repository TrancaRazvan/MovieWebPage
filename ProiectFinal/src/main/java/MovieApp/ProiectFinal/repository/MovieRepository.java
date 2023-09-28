package MovieApp.ProiectFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import MovieApp.ProiectFinal.model.Movie;

import java.util.List;

@Repository

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContaining(String name);
}
