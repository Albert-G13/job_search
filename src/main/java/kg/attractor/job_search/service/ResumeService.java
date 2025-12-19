package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ResumeDto;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getList(Integer id);

    List<ResumeDto> getListByApplicantId(Integer id);

    List<ResumeDto> getAllResumes();
}
