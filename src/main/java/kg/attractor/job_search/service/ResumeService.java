package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.dto.ResumeEditDto;
import kg.attractor.job_search.model.Resume;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResumeService {
    Page<ResumeDto> findAllResumes(Pageable page);

    Page<ResumeDto> findByApplicantId(Integer applicantId, Pageable page);

    List<ResumeDto> getList(Integer id);


    List<ResumeDto> getAllResumes();

    ResumeDto getById(Integer id, String email);

    ResumeDto findById(Integer id);

    ResumeDto create(ResumeDto resumeDto, Integer applicantId);

    ResumeEditDto getForUpdate(Integer id);

    void delete(Integer id);

    void edit(Integer resumeId, ResumeEditDto resumeDto);

    Integer update(Integer id, ResumeDto resumeDto);

    List<ResumeDto> findAllByApplicantId(Integer id);

    Page<Resume> findResumesByFilters(String name, Long categoryId, Pageable page);
}
