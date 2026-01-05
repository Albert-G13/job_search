package kg.attractor.job_search.controller;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import kg.attractor.job_search.service.impl.WorkExperienceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicant/resumes/work-experience")
@RequiredArgsConstructor
public class WorkExperienceInfoController {

    private final WorkExperienceServiceImpl workExperienceService;

    @PostMapping
    public WorkExperienceInfoDto create(@RequestBody @Valid WorkExperienceInfoDto dto) {
        return workExperienceService.create(dto);
    }

    @GetMapping("/resume/{resumeId}")
    public List<WorkExperienceInfoDto> getByResumeId(@PathVariable Integer resumeId) {
        return workExperienceService.getByResumeId(resumeId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        workExperienceService.delete(id);
    }
}