package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.WorkExperienceInfoDao;
import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import kg.attractor.job_search.model.WorkExperienceInfo;
import kg.attractor.job_search.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceServiceImpl implements WorkExperienceService {
    private final WorkExperienceInfoDao workExperienceInfoDao;

    @Override
    public WorkExperienceInfoDto create(WorkExperienceInfoDto workExperienceInfoDto){
        WorkExperienceInfo workExperienceInfo = WorkExperienceInfo
                .builder()
                .companyName(workExperienceInfoDto.getPosition())
                .years(workExperienceInfoDto.getYears())
                .responsibilities(workExperienceInfoDto.getResponsibilities())
                .position(workExperienceInfoDto.getPosition())
                .build();
        workExperienceInfoDao.create(workExperienceInfo);
        return convertToWorkExperienceInfoDto(workExperienceInfo);
    }
    @Override
    public List<WorkExperienceInfoDto> getByResumeId(Integer resumeId) {
        return workExperienceInfoDao.getListByResumeId(resumeId).stream().map(this::convertToWorkExperienceInfoDto).toList();
    }

    @Override
    public void delete(Integer id) {
        workExperienceInfoDao.deleteByResumeId(id);
    }

    private WorkExperienceInfoDto convertToWorkExperienceInfoDto (WorkExperienceInfo workExperienceInfo){
        return WorkExperienceInfoDto.builder()
                .companyName(workExperienceInfo.getCompanyName())
                .position(workExperienceInfo.getPosition())
                .years(workExperienceInfo.getYears())
                .responsibilities(workExperienceInfo.getResponsibilities())
                .resumeId(workExperienceInfo.getResume().getId())
                .build();
    }
}
