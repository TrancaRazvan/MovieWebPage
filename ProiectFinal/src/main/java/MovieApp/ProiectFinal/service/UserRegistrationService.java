package MovieApp.ProiectFinal.service;

import MovieApp.ProiectFinal.exception.RegistrationException;
import MovieApp.ProiectFinal.model.User;
import MovieApp.ProiectFinal.registration.RegistrationValidator;
import MovieApp.ProiectFinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

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

        if (!RegistrationValidator.isNameValid(user.getUsername())) {
            throw new RegistrationException("Invalid name.");
        }

        if (!RegistrationValidator.isDateOfBirthValid(String.valueOf(user.getDateOfBirth()))) {
            throw new RegistrationException("Invalid date of birth.");
        }


        userRepository.save(user);
    }
}

