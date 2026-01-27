package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.EducationInfoDao;
import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.EducationInfo;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.repository.ResumeRepository;
import kg.attractor.job_search.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationService {
    private final EducationInfoDao educationInfoDao;
    private final ResumeRepository resumeRepository;

    @Override
    public EducationInfoDto create(EducationInfoDto educationInfoDto){
        Resume resume = resumeRepository.findById(educationInfoDto.getResumeId())
                .orElseThrow(ResumeNotFoundException::new);
        EducationInfo educationInfo = EducationInfo
                .builder()
                .degree(educationInfoDto.getDegree())
                .program(educationInfoDto.getProgram())
                .endDate(LocalDate.from(educationInfoDto.getEndDate()))
                .institution(educationInfoDto.getInstitution())
                .startDate(LocalDate.from(educationInfoDto.getStartDate()))
                .resume(resume)
                .build();
        educationInfoDao.create(educationInfo);
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
