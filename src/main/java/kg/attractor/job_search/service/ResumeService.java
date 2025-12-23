package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ResumeDto;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getList(Integer id);

    List<ResumeDto> getListByApplicantId(Integer id);

    List<ResumeDto> getAllResumes();

    ResumeDto getById(Integer id);

    ResumeDto create(ResumeDto resumeDto);

    void delete(Integer id);

    ResumeDto edit(Integer id, ResumeDto resumeDto);

    void update(Integer id, ResumeDto resumeDto);
}
