package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.EducationInfoDao;
import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.dao.WorkExperienceInfoDao;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final EducationInfoDao educationInfoDao;
    private final WorkExperienceInfoDao workExperienceInfoDao;
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

    @Override
    public ResumeDto getById(Integer id) {
        Resume resume = resumeDao.getById(id)
                .orElseThrow(ResumeNotFoundException::new);
        return convertToDto(resume);
    }

    @Override
    public ResumeDto create(ResumeDto resumeDto) {
        Resume resume = new Resume();
        resume.setName(resumeDto.getName());
        resume.setSalary(resumeDto.getSalary());
        resume.setActive(true);
        resume.setApplicantId(resumeDto.getApplicantId());
        resume.setCategoryId(resumeDto.getCategoryId());
        resumeDao.create(resume);

        Integer resumeId = resume.getId();

        if (resumeDto.getEducation() != null){
            resumeDto.getEducation().forEach(e -> {
                e.setResumeId(resumeId);
                educationInfoDao.create(e);
            });
        }
        if (resumeDto.getWorkExperience() != null){
            resumeDto.getWorkExperience().forEach(w -> {
                w.setResumeId(resumeId);
                workExperienceInfoDao.create(w);
            });
        }
        return resumeDao.getById(resumeId)
                .map(this::convertToDto)
                .orElseThrow(ResumeNotFoundException::new);
    }

    public ResumeDto edit(Integer resumeId, ResumeDto resumeDto) {
        Resume resume = resumeDao.getById(resumeId)
                .orElseThrow(ResumeNotFoundException::new);

        if(resumeDto.getName() != null) resume.setName(resumeDto.getName());
        if(resumeDto.getSalary() != 0) resume.setSalary(resumeDto.getSalary());
        if(resumeDto.getCategoryId() != null) resume.setCategoryId(resumeDto.getCategoryId());

        resumeDao.edit(resume);

        educationInfoDao.deleteByResumeId(resumeId);
        if(resumeDto.getEducation() != null){
            resumeDto.getEducation().forEach(e -> {
                e.setResumeId(resumeId);
                educationInfoDao.create(e);
            });
        }

        workExperienceInfoDao.deleteByResumeId(resumeId);
        if(resumeDto.getWorkExperience() != null){
            resumeDto.getWorkExperience().forEach(w -> {
                w.setResumeId(resumeId);
                workExperienceInfoDao.create(w);
            });
        }

        return convertToDto(resume);
    }

    @Override
    public void update(Integer id, ResumeDto resumeDto) {
        resumeDto.setUpdateTime(LocalDateTime.now());
    }

    public void delete(Integer resumeId) {
        Resume resume = resumeDao.getById(resumeId)
                .orElseThrow(ResumeNotFoundException::new);
        resumeDao.deleteById(resumeId);
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
            resumeDto.setEducation(educationInfoDao.getListByResumeId(resume.getId()));
            resumeDto.setWorkExperience(workExperienceInfoDao.getListByResumeId(resume.getId()));
            resumeDtos.add(resumeDto);
        });
        return resumeDtos;
    }
    private ResumeDto convertToDto(Resume resume) {
            ResumeDto resumeDto = new ResumeDto();
            resumeDto.setId(resume.getId());
            resumeDto.setApplicantId(resume.getApplicantId());
            resumeDto.setCategoryId(resume.getCategoryId());
            resumeDto.setName(resume.getName());
            resumeDto.setSalary(resume.getSalary());
            resumeDto.setActive(resume.isActive());
            resumeDto.setCreatedDate(resume.getCreatedDate());
            resumeDto.setUpdateTime(resume.getUpdateTime());
            resumeDto.setEducation(educationInfoDao.getListByResumeId(resume.getId()));
            resumeDto.setWorkExperience(workExperienceInfoDao.getListByResumeId(resume.getId()));
        return resumeDto;
    }

}
