package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.CategoryDao;
import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.dao.UserDao;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.dto.ResumeEditDto;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final UserDao userDao;
    private final CategoryDao categoryDao;
    @Override
    public List<ResumeDto> getList(Integer id) {
        List<Resume> resumes = resumeDao.getList(id);
        return resumes.stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public List<ResumeDto> getListByApplicantId(Integer id) {
        return resumeDao.getListByApplicantId(id).stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public List<ResumeDto> getAllResumes() {
        List<Resume> resumes = resumeDao.getAllResumes();
        return resumes.stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public ResumeDto getById(Integer id) {
        Resume resume = resumeDao.getById(id)
                .orElseThrow(ResumeNotFoundException::new);
        return convertToResumeDto(resume);
    }

    @Override
    public ResumeDto create(ResumeDto resumeDto, Integer applicantId) {

        User user = userDao.getUserById(applicantId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        if (!"APPLICANT".equals(user.getRole())) {
            throw new IllegalStateException("Только соискатель может создавать резюме");
        }

        if (!categoryDao.existsById(resumeDto.getCategoryId())) {
            throw new IllegalArgumentException("Категория не найдена");
        }

        Resume resume = new Resume();
        resume.setName(resumeDto.getName());
        resume.setSalary(resumeDto.getSalary());
        resume.setActive(true);
        resume.setApplicantId(resumeDto.getApplicantId());
        resume.setCategoryId(resumeDto.getCategoryId());
        resumeDao.create(resume);

        return convertToResumeDto(resume);
    }

    public ResumeDto edit(Integer resumeId, ResumeEditDto resumeDto) {
        Resume resume = resumeDao.getById(resumeId)
                .orElseThrow(ResumeNotFoundException::new);

        if (resumeDto.getName() != null && !resumeDto.getName().isBlank()) {
            resume.setName(resumeDto.getName());
        }

        if (resumeDto.getSalary() != null && resumeDto.getSalary() > 0) {
            resume.setSalary(resumeDto.getSalary());
        }

        if (resumeDto.getCategoryId() != null) {
            if (!categoryDao.existsById(resumeDto.getCategoryId())) {
                throw new IllegalArgumentException("Категория не найдена");
            }
            resume.setCategoryId(resumeDto.getCategoryId());
        }

        if (resumeDto.getIsActive() != null) {
            resume.setActive(resumeDto.getIsActive());
        }

        resume.setUpdateTime(LocalDateTime.now());
        resumeDao.edit(resume);
        return convertToResumeDto(resume);
    }

    @Override
    public void update(Integer id, ResumeDto resumeDto) {
        resumeDto.setUpdateTime(LocalDateTime.now());
    }

    public void delete(Integer resumeId) {
        resumeDao.getById(resumeId)
                .orElseThrow(ResumeNotFoundException::new);
        resumeDao.deleteById(resumeId);
    }

    private ResumeDto convertToResumeDto (Resume resume){
        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getApplicantId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .build();
    }
}
