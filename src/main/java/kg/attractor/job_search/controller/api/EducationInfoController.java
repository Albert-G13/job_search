package kg.attractor.job_search.controller.api;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.service.impl.EducationInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicant/resumes/education")
@RequiredArgsConstructor
public class EducationInfoController {

    private final EducationInfoServiceImpl educationInfoServiceImpl;

    @PostMapping
    public EducationInfoDto create(@RequestBody @Valid EducationInfoDto dto) {
        return educationInfoServiceImpl.create(dto);
    }

    @GetMapping("resume/{resumeId}")
    public List<EducationInfoDto> getByResumeId(@PathVariable Integer resumeId) {
        return educationInfoServiceImpl.getByResumeId(resumeId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        educationInfoServiceImpl.delete(id);
    }
}