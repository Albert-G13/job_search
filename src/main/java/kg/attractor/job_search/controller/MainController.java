package kg.attractor.job_search.controller;

import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final VacancyService vacancyService;

    @GetMapping
    public String getVacancies(
            Pageable page,
            Model model){
        model.addAttribute("vacancies", vacancyService.findAllVacancies(page));
        return "vacancies/index";
    }
}
