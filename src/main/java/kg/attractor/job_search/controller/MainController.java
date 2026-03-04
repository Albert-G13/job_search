package kg.attractor.job_search.controller;

import kg.attractor.job_search.service.CategoryService;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final VacancyService vacancyService;
    private final CategoryService categoryService;

    @GetMapping("/")
    public String roots(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String name,
            Authentication authentication,
            @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC, size = 3)
            Pageable page,
            Model model) {
        if (authentication != null) {
            if (authentication.getAuthorities().stream()
                    .anyMatch(a -> Objects.equals(a.getAuthority(), "EMPLOYER"))) {
                return "redirect:/resumes";
            } else {
                return "redirect:/vacancies";
            }
        }
        model.addAttribute("vacancies", vacancyService.findByFilter(categoryId, name, page));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("selectedCategory", categoryId);
        model.addAttribute("searchName", name);
        if (!page.getSort().isEmpty()) {
            model.addAttribute("currentSort", page.getSort().toString().replace(": ", ","));
        }
        return "vacancies/index";
    }
}
