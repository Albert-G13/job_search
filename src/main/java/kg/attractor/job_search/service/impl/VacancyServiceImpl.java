package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.CategoryDao;
import kg.attractor.job_search.dao.UserDao;
import kg.attractor.job_search.dao.VacancyDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.dto.VacancyUpdateDto;
import kg.attractor.job_search.exceptions.VacancyNotFoundException;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.model.Vacancy;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
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
            userDto.setRoleId(user.getRoleId());
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
    public void edit(Integer id, VacancyUpdateDto vacancyDto) {
        Vacancy vacancy = vacancyDao.getById(id)
                .orElseThrow(VacancyNotFoundException::new);

        if (vacancyDto.getName() != null) vacancy.setName(vacancyDto.getName());
        if (vacancyDto.getDescription() != null) vacancy.setDescription(vacancyDto.getDescription());
        if (vacancyDto.getSalary() != null) vacancy.setSalary(vacancyDto.getSalary());
        if (vacancyDto.getExpFrom() != null) vacancy.setExpFrom(vacancyDto.getExpFrom());
        if (vacancyDto.getExpTo() != null) vacancy.setExpTo(vacancyDto.getExpTo());
        if (vacancyDto.getIsActive() != null) vacancy.setActive(vacancyDto.getIsActive());
        if (vacancyDto.getCategoryId() != null) vacancy.setCategoryId(vacancyDto.getCategoryId());

        vacancy.setUpdateTime(LocalDateTime.now());

        vacancyDao.edit(vacancy);
    }

    @Override
    public Integer create(VacancyDto vacancyDto, Integer authorId) {

        User user = userDao.getUserById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Автор не найден"));

        if (!"EMPLOYER".equals(user.getRole())){
            throw new IllegalStateException("Только работодатель может создавать вакансии");
        }

        if (!categoryDao.existsById(vacancyDto.getCategoryId())){
            throw new IllegalArgumentException("Категория не найдена");
        }

        if (vacancyDto.getExpFrom() > vacancyDto.getExpTo()) {
            throw new IllegalArgumentException("Начало работы не может быть больше конца работы");
        }

        vacancyDto.setAuthorId(authorId);
        vacancyDto.setCreatedDate(LocalDateTime.now());
        vacancyDto.setUpdateTime(LocalDateTime.now());
        vacancyDto.setActive(true);
        return vacancyDao.create(vacancyDto);
    }

    public void delete(Integer id) {
        vacancyDao.getById(id)
                .orElseThrow(VacancyNotFoundException::new);
        vacancyDao.deleteById(id);
    }

    @Override
    public void update(Integer id, VacancyDto vacancyDto) {
        vacancyDto.setUpdateTime(LocalDateTime.now());
    }

    private VacancyDto convertToVacancyDto (Vacancy vacancy){
        return VacancyDto.builder()
                .id(vacancy.getId())
                .categoryId(vacancy.getCategoryId())
                .authorId(vacancy.getAuthorId())
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
}
