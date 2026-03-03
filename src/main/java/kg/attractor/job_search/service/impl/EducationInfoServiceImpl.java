package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.exceptions.EndDateIsAfterNowException;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.EducationInfo;
import kg.attractor.job_search.repository.EducationInfoRepository;
import kg.attractor.job_search.repository.ResumeRepository;
import kg.attractor.job_search.service.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationService {
    private final ResumeRepository resumeRepository;
    private final EducationInfoRepository educationInfoRepository;

    @Override
    public EducationInfoDto create(EducationInfoDto educationInfoDto) {

        if (educationInfoDto.getEndDate().isAfter(LocalDate.now())){
            throw new EndDateIsAfterNowException();
        }

        EducationInfo educationInfo = EducationInfo
                .builder()
                .degree(educationInfoDto.getDegree())
                .program(educationInfoDto.getProgram())
                .endDate(LocalDate.from(educationInfoDto.getEndDate()))
                .institution(educationInfoDto.getInstitution())
                .startDate(LocalDate.from(educationInfoDto.getStartDate()))
                .resume(resumeRepository.findById(educationInfoDto.getResumeId())
                        .orElseThrow(ResumeNotFoundException::new))
                .build();
        educationInfoRepository.save(educationInfo);
        return educationInfoDto;
    }

    @Override
    public List<EducationInfoDto> getByResumeId(Integer resumeId) {
        List<EducationInfo> educationInfos = educationInfoRepository.findByResume_Id(resumeId);
        return educationInfos.stream().map(this::convertToDto).toList();
    }

    @Override
    public void delete(Integer id) {
        educationInfoRepository.deleteByResume_Id(id);
    }

    private EducationInfoDto convertToDto(EducationInfo educationInfo) {
        return EducationInfoDto.builder()
                .institution(educationInfo.getInstitution())
                .program(educationInfo.getProgram())
                .degree(educationInfo.getDegree())
                .endDate(educationInfo.getEndDate())
                .startDate(educationInfo.getStartDate())
                .build();
    }
}
