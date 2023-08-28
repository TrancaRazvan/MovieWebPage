package MovieApp.ProiectFinal.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import MovieApp.ProiectFinal.model.User;
import MovieApp.ProiectFinal.repository.UserRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public String createUser(User user) {
        validateUser(user);
        return "Create guest with id: " + userRepository.save(user).getId();
    }

    private void validateUser(User user) {
        if (user == null || user.getUsername().isEmpty() || user.getMail().isEmpty() || user.getPassword().isEmpty()) {
            throw new RuntimeException("Invalid data, please try again");
        }
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Guest not found"));
    }
    public void deleteGuestById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("User not found"));
        userRepository.deleteById(id);
    }

}
