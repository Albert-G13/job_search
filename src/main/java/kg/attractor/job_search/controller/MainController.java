package kg.attractor.job_search.controller;

import kg.attractor.job_search.service.ResumeService;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final VacancyService vacancyService;

    @GetMapping("/")
    public String roots(Authentication authentication, @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC, size = 3) Pageable page, Model model) {
        if (authentication != null) {
            if (authentication.getAuthorities().stream()
                    .anyMatch(a -> Objects.equals(a.getAuthority(), "EMPLOYER"))) {
                return "redirect:/resumes";
            } else {
                return "redirect:/vacancies";
            }
        }
        model.addAttribute("vacancies", vacancyService.findAllVacancies(page));

        return "vacancies/index";
    }
}
