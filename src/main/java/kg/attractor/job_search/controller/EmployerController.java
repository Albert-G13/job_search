package kg.attractor.job_search.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employer")
@RequiredArgsConstructor
public class EmployerController {
    @PostMapping("/vacancy")
    public HttpStatus createVacancy() {
        // TODO: create vacancy
        return HttpStatus.CREATED;
    }
    @PutMapping("/vacancy/{id}")
    public HttpStatus editVacancy(@PathVariable long id) {
        // TODO: edit vacancy
        return HttpStatus.OK;
    }
    @DeleteMapping("/vacancy/{id}")
    public HttpStatus deleteVacancy(@PathVariable long id) {
        // TODO: delete vacancy
        return HttpStatus.NO_CONTENT;
    }
    @GetMapping("/resumes")
    public HttpStatus getAllResumes() {
        // TODO: get all resumes
        return HttpStatus.OK;
    }
    @GetMapping("/resumes/category/{categoryId}")
    public HttpStatus getResumeByCategory(@PathVariable long categoryId) {
        // TODO: get resume by category
        return HttpStatus.OK;
    }
    @GetMapping("/vacancy/{vacancyId}/applicants")
    public HttpStatus getRespondedApplicants(@PathVariable long vacancyId) {
        // TODO: get responded applicants
        return HttpStatus.OK;
    }
    @GetMapping("/applicant/{applicantId}")
    public HttpStatus getApplicant(@PathVariable long applicantId) {
        // TODO: get applicant
        return HttpStatus.OK;
    }
}
