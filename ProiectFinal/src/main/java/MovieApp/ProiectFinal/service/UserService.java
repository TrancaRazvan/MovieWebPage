package MovieApp.ProiectFinal.service;


import MovieApp.ProiectFinal.exception.RegistrationException;
import MovieApp.ProiectFinal.registration.RegistrationValidator;
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

    public void registerUser(User user) throws RegistrationException {

        if (!RegistrationValidator.isUsernameValid(user.getUsername())) {
            throw new RegistrationException("Invalid username.");
        }
        if (!RegistrationValidator.isEmailValid(user.getEmail())) {
            throw new RegistrationException("Invalid email.");
        }
        if (!RegistrationValidator.isPasswordValid(user.getPassword())) {
            throw new RegistrationException("Invalid password.");
        }
        if (!RegistrationValidator.isDateOfBirthValid(String.valueOf(user.getDateOfBirth()))) {
            throw new RegistrationException("Invalid date of birth.");
        }
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Guest not found"));
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(id);
    }

}
