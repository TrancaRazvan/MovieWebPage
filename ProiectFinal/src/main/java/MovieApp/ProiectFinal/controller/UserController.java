package MovieApp.ProiectFinal.controller;

import MovieApp.ProiectFinal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import MovieApp.ProiectFinal.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createUsers(@RequestBody User user) {
        return userService.createUser(user);
    }
}
