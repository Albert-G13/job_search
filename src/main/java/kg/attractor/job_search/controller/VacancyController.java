package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;
    @GetMapping("/applicant/{applicantId}")
    public List<VacancyDto> getVacanciesByRespondedId(@PathVariable Integer applicantId) {
        return vacancyService.getVacanciesByRespondedId(applicantId);
    }
    @GetMapping
    public List<VacancyDto> getAllVacancies() {
        return vacancyService.getAllVacancies();
    }
    @GetMapping("/category/{categoryId}")
    public List<VacancyDto> getVacanciesByCategoryId(@PathVariable Integer categoryId) {
        return vacancyService.getVacanciesByCategoryId(categoryId);
    }
    @GetMapping("/{vacancyId}/responded")
    public List<UserDto> getRespondedApplicantsByVacancyId(@PathVariable Integer vacancyId){
        return vacancyService.getRespondedApplicantsByVacancyId(vacancyId);
    }
}
