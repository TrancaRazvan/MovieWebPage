package MovieApp.ProiectFinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import MovieApp.ProiectFinal.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
