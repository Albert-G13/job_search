package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.CategoryDao;
import kg.attractor.job_search.dao.UserDao;
import kg.attractor.job_search.dao.VacancyDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.dto.VacancyUpdateDto;
import kg.attractor.job_search.exceptions.CategoryNotFoundException;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.exceptions.VacancyNotFoundException;
import kg.attractor.job_search.model.Category;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.model.Vacancy;
import kg.attractor.job_search.repository.CategoryRepository;
import kg.attractor.job_search.repository.UserRepository;
import kg.attractor.job_search.repository.VacancyRepository;
import kg.attractor.job_search.security.CustomUserDetails;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    private final UserDao userDao;
    private final CategoryDao categoryDao;
    private final VacancyRepository vacancyRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public Page<VacancyDto> findByAuthorId(Integer authorId, Pageable page){
        Page<Vacancy> vacancies = vacancyRepository.findByUser_Id(authorId, page);
        return vacancies.map(this::convertToVacancyDto);
    }

    @Override
    public Page<VacancyDto> findAllVacancies(Pageable page){
        Sort sort = page.getSort();
        Page<Vacancy> vacancies;

        if (sort.getOrderFor("responds") !=null){
            Pageable respondPage = PageRequest.of(page.getPageNumber(), page.getPageSize());
            vacancies = vacancyRepository.findAllOrderByRespondedApplicantsCount(respondPage);
        } else {
            vacancies = vacancyRepository.findAll(page);
        }
        return vacancies.map(this::convertToVacancyDto);
    }

    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancies = vacancyDao.getAllVacancies();
        return vacancies.stream().map(this::convertToVacancyDto).toList();
    }

    @Override
    public List<VacancyDto> getVacanciesByRespondedId(Integer applicantId) {
        List<Vacancy> vacancies = vacancyDao.getVacanciesByRespondedId(applicantId);
        return vacancies.stream().map(this::convertToVacancyDto).toList();
    }

    @Override
    public List<VacancyDto> getVacanciesByCategoryId(Integer categoryId) {
        List<Vacancy> vacancies = vacancyDao.getVacanciesByCategoryId(categoryId);
        return vacancies.stream().map(this::convertToVacancyDto).toList();
    }
    @Override
    public List<UserDto> getRespondedApplicantsByVacancyId(Integer vacancyId) {
        List<User> users = vacancyDao.getRespondedApplicantsByVacancyId(vacancyId);
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setSurname(user.getSurname());
            userDto.setAge(user.getAge());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setAvatar(user.getAvatar());
            userDto.setRoleId(user.getRole().getId());
            userDtos.add(userDto);
        });
        return userDtos;
    }

    @Override
    public VacancyDto getById(Integer id) {
        Vacancy vacancy = vacancyDao.getById(id)
                .orElseThrow(VacancyNotFoundException::new);
        return convertToVacancyDto(vacancy);
    }

    @Override
    public void edit(Integer id, VacancyUpdateDto dto) {

        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(VacancyNotFoundException::new);

        if (dto.getName() != null) vacancy.setName(dto.getName());
        if (dto.getDescription() != null) vacancy.setDescription(dto.getDescription());
        if (dto.getSalary() != null) vacancy.setSalary(dto.getSalary());
        if (dto.getExpFrom() != null) vacancy.setExpFrom(dto.getExpFrom());
        if (dto.getExpTo() != null) vacancy.setExpTo(dto.getExpTo());
        if (dto.getIsActive() != null) vacancy.setActive(dto.getIsActive());

        vacancy.setUpdateTime(LocalDateTime.now());

        vacancyRepository.save(vacancy);
    }

    @Override
    public VacancyDto create(VacancyDto vacancyDto, Integer authorId) {

        User author = userRepository.findById(authorId)
                .orElseThrow(UserNotFoundException::new);

        if (!"EMPLOYER".equals(author.getRole().getRole())){
            throw new IllegalStateException("Только работодатель может создавать вакансии");
        }

        if (vacancyDto.getExpFrom() > vacancyDto.getExpTo()) {
            throw new IllegalArgumentException("Начало работы не может быть больше конца работы");
        }
        Category category = categoryRepository.findById(vacancyDto.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Vacancy vacancy = Vacancy.builder()
                .name(vacancyDto.getName())
                .salary(vacancyDto.getSalary())
                .isActive(true)
                .description(vacancyDto.getDescription())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .user(author)
                .category(category)
                .build();
        vacancy = vacancyRepository.save(vacancy);
        return convertToVacancyDto(vacancy);
    }

    public void delete(Integer id) {
        vacancyDao.getById(id)
                .orElseThrow(VacancyNotFoundException::new);
        vacancyDao.deleteById(id);
    }

    @Override
    public Integer update(Integer id, VacancyDto vacancyDto) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(VacancyNotFoundException::new);

        vacancy.setUpdateTime(LocalDateTime.now());

        vacancyRepository.save(vacancy);
        return vacancy.getUser().getId();
    }

    @Override
    public VacancyUpdateDto getForUpdate(Integer id) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacancy not found"));

        return VacancyUpdateDto.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .categoryId(vacancy.getCategory() != null ? vacancy.getCategory().getId() : null)
                .isActive(vacancy.isActive())
                .build();
    }

    private VacancyDto convertToVacancyDto (Vacancy vacancy){
        return VacancyDto.builder()
                .id(vacancy.getId())
                .categoryId(vacancy.getCategory().getId())
                .authorId(vacancy.getUser().getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .isActive(vacancy.isActive())
                .createdDate(vacancy.getCreatedDate())
                .updateTime(vacancy.getUpdateTime())
                .build();
    }
    private Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getUser().getId();
        }
        throw new RuntimeException("Пользователь не авторизован!");
    }
}
