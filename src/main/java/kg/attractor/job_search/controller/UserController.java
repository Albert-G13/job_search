package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.UserEditDto;
import kg.attractor.job_search.service.ImageService;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ImageService imageService;

    @GetMapping("{id}/profile")
    public String profile(Model model, @PathVariable Integer id){
        model.addAttribute("userDto", userService.getUserById(id));
        return "users/profile";
    }
    @GetMapping("/{id}/edit")
    public String editProfile(Model model, @PathVariable Integer id){
        model.addAttribute("userDto", userService.getUserById(id));
        return "users/editProfile";
    }
    @PostMapping("/{id}/edit")
    public String editProfile(@PathVariable Integer id, @Valid @ModelAttribute("userDto") UserEditDto userDto, BindingResult bindingResult, Model model, @RequestParam("file") MultipartFile file) {
        if (bindingResult.hasErrors()){
            model.addAttribute("userDto", userDto);
            return "users/editProfile";
        }

        if (!file.isEmpty()) {
            String fileName = imageService.saveUploadFile(file, "images");
            userDto.setAvatar(fileName);
        }

        userService.edit(id, userDto);
        return "redirect:/users/" + id + "/profile";
    }
}
