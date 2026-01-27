package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.ResumeDao;
import kg.attractor.job_search.dto.ResumeDto;
import kg.attractor.job_search.dto.ResumeEditDto;
import kg.attractor.job_search.exceptions.CategoryNotFoundException;
import kg.attractor.job_search.exceptions.ResumeNotFoundException;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.model.Category;
import kg.attractor.job_search.model.Resume;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.repository.CategoryRepository;
import kg.attractor.job_search.repository.ResumeRepository;
import kg.attractor.job_search.repository.UserRepository;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ResumeDto> findAllResumes(Pageable page){
        Page<Resume> resumes = resumeRepository.findAll(page);
        return resumes.map(this::convertToResumeDto);
    }

    @Override
    public Page<ResumeDto> findByApplicantId(Integer applicantId, Pageable page){
        Page<Resume> resumes = resumeRepository.findByUser_Id(applicantId, page);
        return resumes.map(this::convertToResumeDto);
    }

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

        User applicant = userRepository.findById(applicantId)
                .orElseThrow(UserNotFoundException::new);

        if (!"APPLICANT".equals(applicant.getRole().getRole())) {
            throw new IllegalStateException("Только соискатель может создавать резюме");
        }

        Category category = categoryRepository.findById(resumeDto.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Resume resume = Resume.builder()
                .name(resumeDto.getName())
                .salary(resumeDto.getSalary())
                .isActive(true)
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .user(applicant)
                .category(category)
                .build();

        resume = resumeRepository.save(resume);

        return convertToResumeDto(resume);
    }

    public void edit(Integer resumeId, ResumeEditDto dto) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Резюме не найдено"));

        if (dto.getName() != null) resume.setName(dto.getName());
        if (dto.getSalary() != null) resume.setSalary(dto.getSalary());
        if (dto.getIsActive() != null) resume.setActive(dto.getIsActive());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Категория не найдена"));
            resume.setCategory(category);
        }

        resume.setUpdateTime(LocalDateTime.now());

        resumeRepository.save(resume);
    }

    @Override
    public Integer update(Integer id, ResumeDto resumeDto) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        resume.setUpdateTime(LocalDateTime.now());

        resumeRepository.save(resume);

        return resume.getUser().getId();
    }

    @Override
    public ResumeEditDto getForUpdate(Integer id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Резюме не найдено"));

        return ResumeEditDto.builder()
                .id(resume.getId())
                .name(resume.getName())
                .categoryId(resume.getCategory() != null ? resume.getCategory().getId() : null)
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .build();
    }

    public void delete(Integer resumeId) {
        resumeDao.getById(resumeId)
                .orElseThrow(ResumeNotFoundException::new);
        resumeDao.deleteById(resumeId);
    }

    private ResumeDto convertToResumeDto (Resume resume){

        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getUser().getId())
                .name(resume.getName())
                .categoryId(resume.getCategory().getId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .build();
    }
}
