package MovieApp.ProiectFinal.repository;


import MovieApp.ProiectFinal.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface SeriesRepository extends JpaRepository<Series, Integer> {
}
