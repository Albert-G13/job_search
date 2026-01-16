package kg.attractor.job_search.controller;

import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        //пароль у as@pushkin.ru "qwe"
        return "auth/login";
    }
    @GetMapping("/register")
    public String registerGet(){
        return "auth/register";
    }
    @PostMapping("/register")
    public String registerPost(@RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String phoneNumber,
                               @RequestParam Integer roleId){
        userService.register(email, password, phoneNumber, roleId);
        return "redirect:/auth/login";
    }
}
