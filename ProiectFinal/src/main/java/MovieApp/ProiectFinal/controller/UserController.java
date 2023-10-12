package MovieApp.ProiectFinal.controller;


import MovieApp.ProiectFinal.model.User;
import MovieApp.ProiectFinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/users/delete")
    public ResponseEntity<?> deleteUserById(@RequestParam Long userId) {
        boolean deleted = userService.deleteUserById(userId);
        if (deleted) {
            return ResponseEntity.ok("User successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/allUsers")
    public String showAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "all-users.html";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String getAdminHomePage() {
        return "admin-index.html";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login.html";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        userService.registerUser(user);
        return user == null ? "redirect:/fail" : "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/login";
    }
}
