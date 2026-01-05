package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.WorkExperienceInfoDao;
import kg.attractor.job_search.dto.WorkExperienceInfoDto;
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
        workExperienceInfoDao.create(workExperienceInfoDto);
        return workExperienceInfoDto;
    }
    @Override
    public List<WorkExperienceInfoDto> getByResumeId(Integer resumeId) {
        return workExperienceInfoDao.getListByResumeId(resumeId);
    }

    @Override
    public void delete(Integer id) {
        workExperienceInfoDao.deleteByResumeId(id);
    }
}
