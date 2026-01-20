package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserRegisterDto;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        //пароли: bmw@bmw.bmw - Bmw11 | merc@merc.merc - Merc1
        return "auth/login";
    }
    @GetMapping("/register")
    public String registerGet(Model model){
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        return "auth/register";
    }
    @PostMapping("/register")
    public String registerPost(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            userService.register(userRegisterDto);
            return "redirect:/auth/login";
        }
        model.addAttribute("userRegisterDto", userRegisterDto);
        return "auth/register";
    }
}
