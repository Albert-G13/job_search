package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.*;
import kg.attractor.job_search.exceptions.EndDateIsAfterNowException;
import kg.attractor.job_search.exceptions.InvalidWorkExperienceAgeException;
import kg.attractor.job_search.exceptions.WorkExperienceDateException;
import kg.attractor.job_search.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final ContactTypeService contactTypeService;
    private final CategoryService categoryService;

    @GetMapping
    public String getResumes(@PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC, size = 4) Pageable page, Model model) {
        model.addAttribute("resumes", resumeService.findAllResumes(page));
        return "resumes/resume";
    }

    @GetMapping("/{id}")
    public String getResumeInfo(@PathVariable Integer id, Model model) {
        model.addAttribute("resume", resumeService.findById(id));
        return "resumes/info";
    }

    @GetMapping("/create/{applicantId}")
    public String createGet(Model model, @PathVariable Integer applicantId) {
        model.addAttribute("resume", new ResumeDto());
        model.addAttribute("applicantId", applicantId);
        model.addAttribute("education", new EducationInfoDto());
        model.addAttribute("workExp", new WorkExperienceInfoDto());
        model.addAttribute("contactTypes", contactTypeService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "resumes/create";
    }

    @PostMapping("/create/{applicantId}")
    public String createPost(@Valid @ModelAttribute("resume") ResumeDto dto, BindingResult bindingResult, @PathVariable Integer applicantId, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("applicantId", applicantId);
            model.addAttribute("education", new EducationInfoDto());
            model.addAttribute("workExp", new WorkExperienceInfoDto());
            model.addAttribute("contactTypes", contactTypeService.findAll());
            model.addAttribute("categories", categoryService.findAll());

            return "resumes/create";
        }
        try {
            resumeService.create(dto, applicantId);
            return "redirect:/resumes";
        } catch (InvalidWorkExperienceAgeException | EndDateIsAfterNowException e) {
            model.addAttribute("error", e.getMessage());
            return "resumes/create";
        }
    }

    @GetMapping("/{id}/edit")
    public String editGet(@PathVariable Integer id, Model model, Principal principal) {
        String email = principal.getName();

        ResumeDto resume = resumeService.getById(id, email);

        model.addAttribute("resume", resume);
        model.addAttribute("education", new EducationInfoDto());
        model.addAttribute("workExp", new WorkExperienceInfoDto());
        model.addAttribute("contactTypes", contactTypeService.findAll());
        model.addAttribute("categories", categoryService.findAll());

        return "resumes/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable Integer id,
                           @Valid @ModelAttribute("resume") ResumeEditDto dto,
                           BindingResult bindingResult,
                           Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("contactTypes", contactTypeService.findAll());
            model.addAttribute("categories", categoryService.findAll());
            return "resumes/edit";
        }

        resumeService.edit(id, dto);
        return "redirect:/resumes";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Integer id, ResumeDto resumeDto) {
        Integer userId = resumeService.update(id, resumeDto);
        return "redirect:/users/" + userId + "/profile";
    }
}
