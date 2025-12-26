package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employer")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("/vacancies/{id}")
    public VacancyDto getVacancyById(@PathVariable Integer id){
        return vacancyService.getById(id);
    }
    @GetMapping("/vacancies/applicant/{applicantId}")
    public List<VacancyDto> getVacanciesByRespondedId(@PathVariable Integer applicantId) {
        return vacancyService.getVacanciesByRespondedId(applicantId);
    }
    @GetMapping("/vacancies")
    public List<VacancyDto> getAllVacancies() {
        return vacancyService.getAllVacancies();
    }
    @GetMapping("/vacancies/category/{categoryId}")
    public List<VacancyDto> getVacanciesByCategoryId(@PathVariable Integer categoryId) {
        return vacancyService.getVacanciesByCategoryId(categoryId);
    }
    @GetMapping("/vacancies/{vacancyId}/responded")
    public List<UserDto> getRespondedApplicantsByVacancyId(@PathVariable Integer vacancyId){
        return vacancyService.getRespondedApplicantsByVacancyId(vacancyId);
    }
    @PostMapping("/vacancies")
    public Integer createVacancy(@RequestBody @Valid VacancyDto vacancyDto){
        return vacancyService.create(vacancyDto);
    }
    @PutMapping("/vacancies/{id}")
    public HttpStatus editVacancy(@PathVariable Integer id, @RequestBody @Valid VacancyDto vacancyDto){
        vacancyService.edit(id, vacancyDto);
        return HttpStatus.ACCEPTED;
    }
    @PutMapping("/vacancies/{id}/update")
    public HttpStatus updateVacancy(@PathVariable Integer id, @RequestBody @Valid VacancyDto vacancyDto){
        vacancyService.update(id, vacancyDto);
        return HttpStatus.ACCEPTED;
    }
    @DeleteMapping("/vacancies/{id}")
    public HttpStatus deleteVacancy(@PathVariable Integer id) {
        vacancyService.delete(id); return HttpStatus.OK;
    }
}
