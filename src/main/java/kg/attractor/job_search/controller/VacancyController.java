package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.dto.VacancyUpdateDto;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @GetMapping
    public String getVacancies(Model model){
        model.addAttribute("vacancies", vacancyService.getAllVacancies());
        return "vacancies/index";
    }

    @GetMapping("/create/{authorId}")
    public String createGet(Model model, @PathVariable Integer authorId) {
        model.addAttribute("vacancy", new VacancyDto());
        model.addAttribute("authorId", authorId);
        return "vacancies/create";
    }

    @PostMapping("/create/{authorId}")
    public String createPost(@Valid @ModelAttribute("vacancy") VacancyDto dto,BindingResult bindingResult, @PathVariable Integer authorId, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("authorId", authorId);
            return "vacancies/create";
        }
        vacancyService.create(dto, authorId);
        return "redirect:/vacancies";
    }

    @GetMapping("/{id}/edit")
    public String editGet(@PathVariable Integer id, Model model) {
        model.addAttribute("vacancy", vacancyService.getById(id));
        return "vacancies/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable Integer id, @Valid @ModelAttribute("vacancy") VacancyUpdateDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            dto.setId(id);
            model.addAttribute("vacancy", dto);
            return "vacancies/edit";
        }

        vacancyService.edit(id, dto);
        return "redirect:/vacancies";
    }
}
