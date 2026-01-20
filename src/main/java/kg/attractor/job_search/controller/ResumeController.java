package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.dto.ResumeEditDto;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping
    public String getResumes(Model model){
        model.addAttribute("resumes", resumeService.getAllResumes());
        return "resumes/resume";
    }

    @GetMapping("/create/{applicantId}")
    public String createGet(Model model, @PathVariable Integer applicantId) {
        model.addAttribute("resume", new ResumeDto());
        model.addAttribute("applicantId", applicantId);
        return "resumes/create";
    }

    @PostMapping("/create/{applicantId}")
    public String createPost(@Valid @ModelAttribute("resume") ResumeDto dto, BindingResult bindingResult, @PathVariable Integer applicantId, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("applicantId", applicantId);
            return "resumes/create";
        }

        resumeService.create(dto, applicantId);
        return "redirect:/resumes";
    }

    @GetMapping("/{id}/edit")
    public String editGet(@PathVariable Integer id, Model model) {
        model.addAttribute("resume", resumeService.getById(id));
        return "resumes/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable Integer id, @Valid @ModelAttribute("resume") ResumeEditDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            dto.setId(id);
            return "resumes/edit";
        }

        resumeService.edit(id, dto);
        return "redirect:/resumes";
    }
}
