package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.dto.VacancyUpdateDto;
import kg.attractor.job_search.exceptions.WorkExperienceDateException;
import kg.attractor.job_search.service.CategoryService;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;
    private final CategoryService categoryService;

    @GetMapping
    public String getVacancies(@PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC, size = 3) Pageable page, Model model) {
        model.addAttribute("vacancies", vacancyService.findAllVacancies(page));
        return "vacancies/index";
    }

    @GetMapping("/{id}")
    public String getVacancyInfo(@PathVariable Integer id, Model model) {
        model.addAttribute("vacancy", vacancyService.getById(id));
        return "vacancies/info";
    }

    @GetMapping("/create/{authorId}")
    public String createGet(Model model, @PathVariable Integer authorId) {
        model.addAttribute("vacancy", new VacancyDto());
        model.addAttribute("authorId", authorId);
        model.addAttribute("categories", categoryService.findAll());
        return "vacancies/create";
    }

    @PostMapping("/create/{authorId}")
    public String createPost(@Valid @ModelAttribute("vacancy") VacancyDto dto, BindingResult bindingResult, @PathVariable Integer authorId, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        if (bindingResult.hasErrors()) {
            model.addAttribute("authorId", authorId);
            return "vacancies/create";
        }
        try {
            vacancyService.create(dto, authorId);
            return "redirect:/vacancies";
        } catch (WorkExperienceDateException e) {
            model.addAttribute("error", e.getMessage());
            return "vacancies/create";
        }
    }

    @GetMapping("/{id}/edit")
    public String editGet(@PathVariable Integer id, Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("vacancy", vacancyService.getForUpdate(id, email));
        model.addAttribute("categories", categoryService.findAll());
        return "vacancies/edit";
    }

    @PostMapping("/{id}/edit")
    public String editPost(@PathVariable Integer id, @Valid @ModelAttribute("vacancy") VacancyUpdateDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            dto.setId(id);
            model.addAttribute("vacancy", dto);
            model.addAttribute("categories", categoryService.findAll());
            return "vacancies/edit";
        }
        vacancyService.edit(id, dto);
        return "redirect:/vacancies";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Integer id, VacancyDto vacancyDto) {
        Integer userId = vacancyService.update(id, vacancyDto);
        return "redirect:/users/" + userId + "/profile";
    }
}
