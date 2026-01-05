package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.EducationInfoDao;
import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationService {
    private final EducationInfoDao educationInfoDao;

    @Override
    public EducationInfoDto create(EducationInfoDto educationInfoDto){
        educationInfoDao.create(educationInfoDto);
        return educationInfoDto;
    }
    @Override
    public List<EducationInfoDto> getByResumeId(Integer resumeId) {
        return educationInfoDao.getListByResumeId(resumeId);
    }

    @Override
    public void delete(Integer id) {
        educationInfoDao.deleteByResumeId(id);
    }
}
