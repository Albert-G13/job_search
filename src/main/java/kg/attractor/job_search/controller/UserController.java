package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserEditDto;
import kg.attractor.job_search.service.ImageService;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final VacancyService vacancyService;
    private final ResumeService resumeService;

    @GetMapping("{id}/profile")
    public String profile(Model model, @PathVariable Integer id, Pageable page){
        model.addAttribute("userDto", userService.getUserEditById(id));
        model.addAttribute("vacancies", vacancyService.findByAuthorId(id, page));
        model.addAttribute("resumes", resumeService.findByApplicantId(id, page));
        return "users/profile";
    }

    @GetMapping("/{id}/edit")
    public String editProfile(Model model, @PathVariable Integer id) {
        model.addAttribute("userDto", userService.getUserEditById(id));
        return "users/editProfile";
    }

    @PostMapping("/{id}/edit")
    public String editProfile(@PathVariable Integer id,
                              @Valid @ModelAttribute("userDto") UserEditDto userEditDto,
                              BindingResult bindingResult,
                              Model model,
                              @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userEditDto);
            return "users/editProfile";
        }

        if (!file.isEmpty()) {
            String fileName = imageService.saveUploadFile(file, "images");
            userEditDto.setAvatar(fileName);
        }

        userService.edit(id, userEditDto);
        return "redirect:/users/" + id + "/profile";
    }

}
