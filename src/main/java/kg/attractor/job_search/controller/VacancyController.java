package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("/{id}")
    public VacancyDto getVacancyById(@PathVariable Integer id){
        return vacancyService.getById(id);
    }
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
    @PostMapping("/create")
    public Integer createVacancy(@RequestBody VacancyDto vacancyDto){
        return vacancyService.create(vacancyDto);
    }
    @PutMapping("/{id}")
    public HttpStatus editVacancy(@PathVariable Integer id, @RequestBody VacancyDto vacancyDto){
        vacancyService.edit(id, vacancyDto);
        return HttpStatus.ACCEPTED;
    }
    @PutMapping("/{id}/update")
    public HttpStatus updateVacancy(@PathVariable Integer id, @RequestBody VacancyDto vacancyDto){
        vacancyService.update(id, vacancyDto);
        return HttpStatus.ACCEPTED;
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteVacancy(@PathVariable Integer id) {
        vacancyService.delete(id); return HttpStatus.OK;
    }
}
