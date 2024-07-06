package com.humber.Week8Security.controllers;

import com.humber.Week8Security.models.MyUser;
import com.humber.Week8Security.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController implements org.springframework.boot.web.servlet.error.ErrorController {

    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @Value("${restaurant.name}")
    private String name;

    // custom error page
    @GetMapping("/error")
    public String handleError(){
        return "auth/error";
    }

    // custom login page
    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String message){
        model.addAttribute("message", message);
        model.addAttribute("restaurantName", name);
        return "auth/login";
    }

    // custom logout
    @GetMapping("/logout")
    public String customLogout(HttpServletRequest request,
                               HttpServletResponse response,
                               Authentication authentication) {
        //logout logic
        new SecurityContextLogoutHandler().logout(request, response, authentication);

        return "redirect:/login?message=You have been logged out successfully!";
    }

    //Open the register page
    @GetMapping("/register")
    public String register(Model model, @RequestParam(required = false) String message){
        model.addAttribute("user", new MyUser());
        model.addAttribute("message", message);
        return "auth/register";
    }

    //register post
    @PostMapping("/register")
    public String registerPost(@ModelAttribute MyUser user){
        //save the user
        int saveUserCode = userService.saveUser(user);
        if(saveUserCode == 0){
            return "redirect:/register?message=User already exists!";
        } else {
            return "redirect:/register?message=Registration successful!";
        }
    }

}
