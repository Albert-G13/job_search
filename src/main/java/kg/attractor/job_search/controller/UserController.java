package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserEditDto;
import kg.attractor.job_search.service.ImageService;
import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.UserService;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ImageService imageService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;

    @GetMapping("{id}/profile")
    public String profile(Model model, @PathVariable Integer id, @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC, size = 4) Pageable page, Principal principal) {
        String email = principal.getName();
        model.addAttribute("userDto", userService.getUserEditById(id, email));
        model.addAttribute("vacancies", vacancyService.findByAuthorId(id, page));
        model.addAttribute("resumes", resumeService.findByApplicantId(id, page));
        return "users/profile";
    }

    @GetMapping("/{id}/edit")
    public String editProfile(Model model, @PathVariable Integer id, Principal principal) {
        String email = principal.getName();
        model.addAttribute("userDto", userService.getUserEditById(id, email));
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
