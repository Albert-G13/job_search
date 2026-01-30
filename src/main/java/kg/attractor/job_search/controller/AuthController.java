package kg.attractor.job_search.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserRegisterDto;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
                                            //Пароль у всех учёток : qwe
        return "auth/login";
    }
    @GetMapping("/register")
    public String registerGet(Model model){
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        return "auth/register";
    }
    @PostMapping("/register")
    public String registerPost(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (!bindingResult.hasErrors()) {
            userService.register(userRegisterDto);
            try {
                request.login(userRegisterDto.getEmail(), userRegisterDto.getPassword());
            } catch (ServletException e) {
                return "redirect:/auth/login";
            }

            var auth = SecurityContextHolder.getContext().getAuthentication();
            assert auth != null;
            boolean isEmployer = auth.getAuthorities().stream()
                    .anyMatch(a -> Objects.equals(a.getAuthority(), "EMPLOYER"));

            if (isEmployer) {
                return "redirect:/resumes";
            } else {
                return "redirect:/vacancies";
            }
        }
        model.addAttribute("userRegisterDto", userRegisterDto);
        return "auth/register";
    }
}
