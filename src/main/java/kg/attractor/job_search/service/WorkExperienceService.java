package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.WorkExperienceInfoDto;

import java.util.List;

public interface WorkExperienceService {
    WorkExperienceInfoDto create(WorkExperienceInfoDto workExperienceInfoDto);

    List<WorkExperienceInfoDto> getByResumeId(Integer resumeId);

    void delete(Integer id);
}
