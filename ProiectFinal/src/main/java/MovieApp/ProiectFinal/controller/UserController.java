package MovieApp.ProiectFinal.controller;


import MovieApp.ProiectFinal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import MovieApp.ProiectFinal.service.UserService;


import java.util.List;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "userList.html";
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuestById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/user/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "userRegisterForm.html";
    }

    @PostMapping("/user/register")
    public String handleRegistrationForm(@ModelAttribute("userRegistration") User user) {
        userService.registerUser(user);

        return "registration-success";
    }
}
