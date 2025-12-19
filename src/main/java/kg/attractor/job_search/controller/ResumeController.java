package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    @GetMapping
    public List<ResumeDto> getAllResumes() {
        return resumeService.getAllResumes();
    }
    @GetMapping("/category/{id}")
    public List<ResumeDto> getResumeByCategoryId(@PathVariable Integer id) {
        return resumeService.getList(id);
    }
    @GetMapping("/applicant/{id}")
    public List<ResumeDto> getResumeByApplicantId(@PathVariable Integer id) {
        return resumeService.getListByApplicantId(id);
    }
}
