package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.UserEditDto;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{id}/profile")
    public String cabinet(Model model, @PathVariable Integer id){
        model.addAttribute("user", userService.getUserById(id));
        return "users/profile";
    }
    @GetMapping("/{id}/edit")
    public String editProfile(Model model, @PathVariable Integer id){
        model.addAttribute("user", userService.getUserById(id));
        return "users/editProfile";
    }
    @PostMapping("/{id}/edit")
    public String editProfile(@PathVariable Integer id, UserEditDto userDto) {
        userService.edit(id, userDto);
        return "redirect:/users/" + id + "/profile";
    }
}
