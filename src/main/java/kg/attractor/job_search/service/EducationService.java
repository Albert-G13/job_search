package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.EducationInfoDto;

import java.util.List;

public interface EducationService {
    EducationInfoDto create(EducationInfoDto educationInfoDto);

    List<EducationInfoDto> getByResumeId(Integer resumeId);

    void delete(Integer id);
}
