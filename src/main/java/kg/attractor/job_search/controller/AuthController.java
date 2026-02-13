package kg.attractor.job_search.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.job_search.dto.LoginDto;
import kg.attractor.job_search.dto.ResetPasswordDto;
import kg.attractor.job_search.dto.UserRegisterDto;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.service.RoleService;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/login")
    public String login(Model model) {
        //Пароль у всех учёток : qwe
        model.addAttribute("loginDto", new LoginDto());
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        model.addAttribute("roles", roleService.findAll());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult, Model model, HttpServletRequest request) {
        if (userService.existsByEmail(userRegisterDto.getEmail())) {
            bindingResult.rejectValue(
                    "email",
                    "email.exists",
                    "Данный email уже зарегистрирован"
            );
        }

        if (userService.existsByPhoneNumber(userRegisterDto.getPhoneNumber())) {
            bindingResult.rejectValue(
                    "phoneNumber",
                    "phoneNumber.exists",
                    "Данный номер уже зарегистрирован"
            );
        }
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
        model.addAttribute("roles", roleService.findAll());
        return "auth/register";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "auth/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        try {
            userService.makeResetPasswordLink(request);
            model.addAttribute("message", "We have send a reset password link to you email. Please check.");
        } catch (UsernameNotFoundException | UnsupportedEncodingException e) {
            model.addAttribute("error", e.getMessage());
        } catch (MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }
        return "auth/forgot_password_form";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        try {
            userService.getByResetPasswordToken(token);
            ResetPasswordDto dto = new ResetPasswordDto();
            dto.setToken(token);
            model.addAttribute("resetPassword", dto);
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", "Invalid token");
        }
        return "auth/reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(
            @Valid @ModelAttribute("resetPassword") ResetPasswordDto dto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("token", dto.getToken());
            return "auth/reset_password_form";
        }
        try {
            User user = userService.getByResetPasswordToken(dto.getToken());
            userService.updatePassword(user, dto.getPassword());
            model.addAttribute("message", "You have successfully changed your password");
        } catch (UsernameNotFoundException e) {
            model.addAttribute("message", "Invalid token");
        }
        return "partial/message";
    }
}
