package MovieApp.ProiectFinal.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import MovieApp.ProiectFinal.model.User;
import MovieApp.ProiectFinal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

//    public String createUser(User user) {
//        validateUser(user);
//        return "Create guest with id: " + userRepository.save(user).getId();
//    }
//
//    private void validateUser(User user) {
//        if (user == null || user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
//            throw new RuntimeException("Invalid data, please try again");
//        }
//    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Guest not found"));
    }

    public void deleteUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(id);
    }

}
