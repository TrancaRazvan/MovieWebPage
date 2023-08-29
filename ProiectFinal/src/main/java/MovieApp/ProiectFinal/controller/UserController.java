package MovieApp.ProiectFinal.controller;


import MovieApp.ProiectFinal.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import MovieApp.ProiectFinal.service.UserService;

import org.slf4j.Logger;


import java.util.List;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> users = userService.findAll();
        logger.info("Number of users retrieved: {}", users.size()); // Log the number of users retrieved
        model.addAttribute("users", users);
        return "userList.html";
    }

    @PostMapping("/usersAdd")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String createUsers(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuestById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }
}
