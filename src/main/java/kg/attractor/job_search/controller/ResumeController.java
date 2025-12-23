package kg.attractor.job_search.controller;

import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResumeDto getResumeById(@PathVariable Integer id){
        return resumeService.getById(id);
    }
    @PostMapping()
    public ResumeDto createResume(@RequestBody ResumeDto resumeDto){
        return resumeService.create(resumeDto);
    }
    @PutMapping("/{id}")
    public ResumeDto editResume(@PathVariable Integer id, @RequestBody ResumeDto resumeDto){
        return resumeService.edit(id, resumeDto);
    }
    @PutMapping("/{id}/update")
    public HttpStatus updateResume(@PathVariable Integer id, @RequestBody ResumeDto resumeDto){
        resumeService.update(id, resumeDto);
        return HttpStatus.ACCEPTED;
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteResume(@PathVariable Integer id) {
        resumeService.delete(id);
        return HttpStatus.OK;
    }
}
