package kg.attractor.job_search.service.impl;

import jakarta.transaction.Transactional;
import kg.attractor.job_search.dto.*;
import kg.attractor.job_search.exceptions.*;
import kg.attractor.job_search.model.*;
import kg.attractor.job_search.repository.*;
import kg.attractor.job_search.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final EducationInfoRepository educationInfoRepository;
    private final WorkExperienceInfoRepository workExperienceInfoRepository;
    private final ContactInfoRepository contactInfoRepository;
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public Page<ResumeDto> findAllResumes(Pageable page) {
        Page<Resume> resumes = resumeRepository.findAllByActiveIsTrue(page);
        return resumes.map(this::convertToResumeDto);
    }

    @Override
    public Page<ResumeDto> findByApplicantId(Integer applicantId, Pageable page) {
        Page<Resume> resumes = resumeRepository.findByUser_Id(applicantId, page);
        return resumes.map(this::convertToResumeDto);
    }

    @Override
    public List<ResumeDto> getList(Integer categoryId) {
        List<Resume> resumes = resumeRepository.findByCategory_Id(categoryId);
        return resumes.stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public List<ResumeDto> getAllResumes() {
        List<Resume> resumes = resumeRepository.findAll();
        return resumes.stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public ResumeDto getById(Integer id, String email) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);
        if (!resume.getUser().getEmail().equals(email)) {
            throw new InvalidAuthorOfResumeEditException();
        }
        return convertToResumeDto(resume);
    }

    @Override
    public ResumeDto findById(Integer id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);
        return convertToResumeDto(resume);
    }

    @Override
    public ResumeDto create(ResumeDto resumeDto, Integer applicantId) {

        User applicant = userRepository.findById(applicantId)
                .orElseThrow(UserNotFoundException::new);

        if (!"APPLICANT".equals(applicant.getRole().getRole())) {
            throw new InvalidRoleApplicantException();
        }

        Category category = categoryRepository.findById(resumeDto.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Resume resume = Resume.builder()
                .name(resumeDto.getName())
                .salary(resumeDto.getSalary())
                .active(true)
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .user(applicant)
                .category(category)
                .build();

        final Resume createdResume = resumeRepository.save(resume);

        if (resumeDto.getEducation() != null) {
            resumeDto.getEducation().forEach(eduDto -> {
                EducationInfo edu = EducationInfo.builder()
                        .resume(createdResume)
                        .institution(eduDto.getInstitution())
                        .program(eduDto.getProgram())
                        .startDate(eduDto.getStartDate())
                        .endDate(eduDto.getEndDate())
                        .degree(eduDto.getDegree())
                        .build();
                educationInfoRepository.save(edu);
            });
        }
        if (resumeDto.getWorkExperience() != null) {
            resumeDto.getWorkExperience().forEach(expDto -> {
                WorkExperienceInfo exp = WorkExperienceInfo.builder()
                        .resume(createdResume)
                        .years(expDto.getYears())
                        .companyName(expDto.getCompanyName())
                        .position(expDto.getPosition())
                        .responsibilities(expDto.getResponsibilities())
                        .build();
                workExperienceInfoRepository.save(exp);
            });
        }

        if (resumeDto.getContacts() != null) {
            resumeDto.getContacts().forEach(cDto -> {

                ContactType type = contactTypeRepository.findById(cDto.getTypeId())
                        .orElseThrow(() -> new RuntimeException("Тип контакта не найден"));

                ContactInfo contact = ContactInfo.builder()
                        .resume(createdResume)
                        .contactType(type)
                        .contactValue(cDto.getContactValue())
                        .build();

                contactInfoRepository.save(contact);
            });
        }

        return convertToResumeDto(resume);
    }

    @Override
    @Transactional
    public void edit(Integer resumeId, ResumeEditDto dto) {

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(ResumeNotFoundException::new);

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        resume.setName(dto.getName());
        resume.setSalary(dto.getSalary());
        resume.setCategory(category);
        resume.setUpdateTime(LocalDateTime.now());
        resume.setActive(dto.getActive() != null && dto.getActive());

        educationInfoRepository.deleteAllByResume_Id(resumeId);

        if (dto.getEducation() != null) {
            dto.getEducation().forEach(eduDto -> {
                EducationInfo edu = EducationInfo.builder()
                        .resume(resume)
                        .institution(eduDto.getInstitution())
                        .program(eduDto.getProgram())
                        .startDate(eduDto.getStartDate())
                        .endDate(eduDto.getEndDate())
                        .degree(eduDto.getDegree())
                        .build();
                educationInfoRepository.save(edu);
            });
        }

        workExperienceInfoRepository.deleteAllByResume_Id((resumeId));

        if (dto.getWorkExperience() != null) {
            dto.getWorkExperience().forEach(expDto -> {
                WorkExperienceInfo exp = WorkExperienceInfo.builder()
                        .resume(resume)
                        .years(expDto.getYears())
                        .companyName(expDto.getCompanyName())
                        .position(expDto.getPosition())
                        .responsibilities(expDto.getResponsibilities())
                        .build();
                workExperienceInfoRepository.save(exp);
            });
        }

        contactInfoRepository.deleteAllByResume_Id(resumeId);

        if (dto.getContacts() != null) {
            dto.getContacts().forEach(cDto -> {
                ContactType type = contactTypeRepository.findById(cDto.getTypeId())
                        .orElseThrow(TypeOfContactNotFoundException::new);

                ContactInfo contact = ContactInfo.builder()
                        .resume(resume)
                        .contactType(type)
                        .contactValue(cDto.getContactValue())
                        .build();

                contactInfoRepository.save(contact);
            });
        }

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
    public List<ResumeDto> findAllByApplicantId(Integer id) {
        List<Resume> resumes = resumeRepository.findAllByUser_Id(id);

        return resumes.stream().map(this::convertToResumeDto).toList();
    }

    @Override
    public Page<Resume> findResumesByFilters(String name, Long categoryId, Pageable page) {
        if ((name == null || name.isEmpty()) && categoryId == null) {
            return resumeRepository.findAll(page);
        }
        return resumeRepository.searchResumes(name, categoryId, page);
    }

    @Override
    public ResumeEditDto getForUpdate(Integer id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        return ResumeEditDto.builder()
                .id(resume.getId())
                .name(resume.getName())
                .categoryId(resume.getCategory().getId())
                .salary(resume.getSalary())
                .active(resume.isActive())
                .education(resume.getEducations().stream()
                        .map(this::convertEducationToDto)
                        .collect(Collectors.toList()))
                .workExperience(resume.getExperiences().stream()
                        .map(this::convertWorkExperienceToDto)
                        .collect(Collectors.toList()))
                .contacts(resume.getContacts().stream()
                        .map(c -> new ContactInfoDto(c.getId(), c.getContactType().getId(), c.getContactValue()))
                        .collect(Collectors.toList()))
                .build();
    }

    public void delete(Integer resumeId) {
        resumeRepository.deleteById(resumeId);
    }

    private ResumeDto convertToResumeDto(Resume resume) {

        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getUser().getId())
                .name(resume.getName())
                .categoryId(resume.getCategory().getId())
                .salary(resume.getSalary())
                .active(resume.isActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .education(
                        resume.getEducations() != null
                                ? resume.getEducations().stream()
                                .map(this::convertEducationToDto)
                                .collect(Collectors.toCollection(ArrayList::new))
                                : new ArrayList<>()
                )
                .workExperience(
                        resume.getExperiences() != null
                                ? resume.getExperiences().stream()
                                .map(this::convertWorkExperienceToDto)
                                .collect(Collectors.toCollection(ArrayList::new))
                                : new ArrayList<>()
                )
                .contacts(
                        resume.getContacts() != null
                                ? resume.getContacts().stream()
                                .map(this::convertContactToDto)
                                .collect(Collectors.toCollection(ArrayList::new))
                                : new ArrayList<>()
                )
                .build();
    }

    private EducationInfoDto convertEducationToDto(EducationInfo edu) {
        return EducationInfoDto.builder()
                .id(edu.getId())
                .institution(edu.getInstitution())
                .program(edu.getProgram())
                .startDate(edu.getStartDate())
                .endDate(edu.getEndDate())
                .degree(edu.getDegree())
                .resumeId(edu.getResume().getId())
                .build();
    }

    private WorkExperienceInfoDto convertWorkExperienceToDto(WorkExperienceInfo exp) {
        return WorkExperienceInfoDto.builder()
                .id(exp.getId())
                .companyName(exp.getCompanyName())
                .position(exp.getPosition())
                .years(exp.getYears())
                .responsibilities(exp.getResponsibilities())
                .resumeId(exp.getResume().getId())
                .build();
    }

    private ContactInfoDto convertContactToDto(ContactInfo contact) {
        return ContactInfoDto.builder()
                .id(contact.getId())
                .typeId(contact.getContactType().getId())
                .contactValue(contact.getContactValue())
                .build();
    }
}
