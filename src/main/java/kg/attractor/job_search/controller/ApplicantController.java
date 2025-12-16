package kg.attractor.job_search.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicant")
@RequiredArgsConstructor
public class ApplicantController {

    @PostMapping("/resume")
    public HttpStatus createResume() {
        // TODO: create resume
        return HttpStatus.CREATED;
    }
    @PutMapping("/resume/{id}")
    public HttpStatus editResume(@PathVariable long id) {
        // TODO: edit resume
        return HttpStatus.OK;
    }
    @DeleteMapping("/resume/{id}")
    public HttpStatus deleteResume(@PathVariable long id) {
        // TODO: delete resume
        return HttpStatus.NO_CONTENT;
    }
    @GetMapping("/vacancies")
    public HttpStatus getAllActiveVacancies() {
        // TODO: get all active vacancies
        return HttpStatus.OK;
    }
    @GetMapping("/vacancy/category/{categoryId}")
    public HttpStatus getVacanciesByCategory(@PathVariable long categoryId) {
        // TODO: get vacancies by category
        return HttpStatus.OK;
    }
    @PostMapping("vacancy/{vacancyId}/respond")
    public HttpStatus respondToVacancy(@PathVariable long vacancyId) {
        // TODO: respond to vacancy
        return HttpStatus.OK;
    }
    @GetMapping("/employer/{employerId}")
    public HttpStatus getEmployer(@PathVariable long employerId) {
        // TODO: get employer
        return HttpStatus.OK;
    }

}
