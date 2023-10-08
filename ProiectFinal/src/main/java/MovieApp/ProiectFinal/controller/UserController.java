package MovieApp.ProiectFinal.controller;


import MovieApp.ProiectFinal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import MovieApp.ProiectFinal.service.UserService;


import java.util.List;


@RequiredArgsConstructor
@Controller
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuestById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return ".html";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("user", new User());
        return "login.html";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "register.html";
    }

    @GetMapping("/fail")
    public String fail(){
        return "fail.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user){
        userService.registerUser(user);
        return user == null ? "redirect:/fail" : "redirect:/login";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute("user") User user){
//        boolean succes = userService.verifyUser(user);
//        return succes == true ? "redirect://" : "redirect:/fail";
//
//    }
}
