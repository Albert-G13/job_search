package kg.attractor.job_search.controller.api;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.dto.ResumeEditDto;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicant")
@RequiredArgsConstructor
public class ResumeRestController {
    private final ResumeService resumeService;
    @GetMapping("/resumes")
    public List<ResumeDto> getAllResumes() {
        return resumeService.getAllResumes();
    }
    @GetMapping("/resumes/category/{id}")
    public List<ResumeDto> getResumeByCategoryId(@PathVariable Integer id) {
        return resumeService.getList(id);
    }
    @GetMapping("/{id}/resumes")
    public List<ResumeDto> getResumeByApplicantId(@PathVariable Integer id) {
        return resumeService.getListByApplicantId(id);
    }
    @GetMapping("/resumes/{id}")
    public ResumeDto getResumeById(@PathVariable Integer id){
        return resumeService.getById(id);
    }
    @PostMapping("/{applicantId}/resumes")
    public ResumeDto createResume(@PathVariable Integer applicantId, @RequestBody @Valid ResumeDto resumeDto){
        return resumeService.create(resumeDto, applicantId);
    }
    @PutMapping("/resumes/{id}")
    public void editResume(@PathVariable Integer id, @RequestBody @Valid ResumeEditDto resumeDto){
        resumeService.edit(id, resumeDto);
    }
    @PutMapping("/resumes/{id}/update")
    public HttpStatus updateResume(@PathVariable Integer id, @RequestBody @Valid ResumeDto resumeDto){
        resumeService.update(id, resumeDto);
        return HttpStatus.ACCEPTED;
    }
    @DeleteMapping("/resumes/{id}")
    public HttpStatus deleteResume(@PathVariable Integer id) {
        resumeService.delete(id);
        return HttpStatus.OK;
    }
}
