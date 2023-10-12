package MovieApp.ProiectFinal.repository;


import MovieApp.ProiectFinal.model.Movie;
import MovieApp.ProiectFinal.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findByTitle(String title);

}
