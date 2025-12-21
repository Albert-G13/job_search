package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    @Override
    public List<ResumeDto> getList(Integer id) {
        List<Resume> resumes = resumeDao.getList(id);
        return convertToDtos(resumes);
    }

    @Override
    public List<ResumeDto> getListByApplicantId(Integer id) {
        List<Resume> resumes = resumeDao.getListByApplicantId(id);
        return convertToDtos(resumes);
    }

    @Override
    public List<ResumeDto> getAllResumes() {
        List<Resume> resumes = resumeDao.getAllResumes();
        return convertToDtos(resumes);
    }

    private List<ResumeDto> convertToDtos(List<Resume> resumes) {
        List<ResumeDto> resumeDtos = new ArrayList<>();
        resumes.forEach(resume -> {
            ResumeDto resumeDto = new ResumeDto();
            resumeDto.setId(resume.getId());
            resumeDto.setApplicantId(resume.getApplicantId());
            resumeDto.setCategoryId(resume.getCategoryId());
            resumeDto.setName(resume.getName());
            resumeDto.setSalary(resume.getSalary());
            resumeDto.setActive(resume.isActive());
            resumeDto.setCreatedDate(resume.getCreatedDate());
            resumeDto.setUpdateTime(resume.getUpdateTime());
            resumeDtos.add(resumeDto);
        });
        return resumeDtos;
    }
}
