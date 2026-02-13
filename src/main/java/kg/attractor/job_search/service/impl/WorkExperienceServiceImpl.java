package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.WorkExperienceInfo;
import kg.attractor.job_search.repository.ResumeRepository;
import kg.attractor.job_search.repository.WorkExperienceInfoRepository;
import kg.attractor.job_search.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceServiceImpl implements WorkExperienceService {
    private final WorkExperienceInfoRepository workExperienceInfoRepository;
    private final ResumeRepository resumeRepository;

    @Override
    public WorkExperienceInfoDto create(WorkExperienceInfoDto workExperienceInfoDto) {
        WorkExperienceInfo workExperienceInfo = WorkExperienceInfo
                .builder()
                .companyName(workExperienceInfoDto.getPosition())
                .years(workExperienceInfoDto.getYears())
                .responsibilities(workExperienceInfoDto.getResponsibilities())
                .position(workExperienceInfoDto.getPosition())
                .resume(resumeRepository.findById(workExperienceInfoDto.getResumeId())
                        .orElseThrow(ResumeNotFoundException::new))
                .build();
        workExperienceInfoRepository.save(workExperienceInfo);
        return convertToWorkExperienceInfoDto(workExperienceInfo);
    }

    @Override
    public List<WorkExperienceInfoDto> getByResumeId(Integer resumeId) {
        return workExperienceInfoRepository.findByResume_Id(resumeId).stream().map(this::convertToWorkExperienceInfoDto).toList();
    }

    @Override
    public void delete(Integer id) {
        workExperienceInfoRepository.deleteByResume_Id(id);
    }

    private WorkExperienceInfoDto convertToWorkExperienceInfoDto(WorkExperienceInfo workExperienceInfo) {
        return WorkExperienceInfoDto.builder()
                .companyName(workExperienceInfo.getCompanyName())
                .position(workExperienceInfo.getPosition())
                .years(workExperienceInfo.getYears())
                .responsibilities(workExperienceInfo.getResponsibilities())
                .resumeId(workExperienceInfo.getResume().getId())
                .build();
    }
}
