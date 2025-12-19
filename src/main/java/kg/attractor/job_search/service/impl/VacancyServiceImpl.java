package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.VacancyDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.model.Vacancy;
import kg.attractor.job_search.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacancies = vacancyDao.getAllVacancies();
        List<VacancyDto> vacancyDtos = new ArrayList<>();
        vacancies.forEach(vacancy -> {
            VacancyDto vacancyDto = new VacancyDto();
            vacancyDto.setId(vacancy.getId());
            vacancyDto.setCategoryId(vacancy.getCategoryId());
            vacancyDto.setAuthorId(vacancy.getAuthorId());
            vacancyDto.setName(vacancy.getName());
            vacancyDto.setDescription(vacancy.getDescription());
            vacancyDto.setSalary(vacancy.getSalary());
            vacancyDto.setExpFrom(vacancy.getExpFrom());
            vacancyDto.setExpTo(vacancy.getExpTo());
            vacancyDto.setActive(vacancy.isActive());
            vacancyDto.setCreatedDate(vacancy.getCreatedDate());
            vacancyDto.setUpdateTime(vacancy.getUpdateTime());
            vacancyDtos.add(vacancyDto);
        });
        return vacancyDtos;
    }

    @Override
    public List<VacancyDto> getVacanciesByRespondedId(Integer applicantId) {
        List<Vacancy> vacancies = vacancyDao.getVacanciesByRespondedId(applicantId);
        List<VacancyDto> vacancyDtos = new ArrayList<>();
        vacancies.forEach(vacancy -> {
            VacancyDto vacancyDto = new VacancyDto();
            vacancyDto.setId(vacancy.getId());
            vacancyDto.setCategoryId(vacancy.getCategoryId());
            vacancyDto.setAuthorId(vacancy.getAuthorId());
            vacancyDto.setName(vacancy.getName());
            vacancyDto.setDescription(vacancy.getDescription());
            vacancyDto.setSalary(vacancy.getSalary());
            vacancyDto.setExpFrom(vacancy.getExpFrom());
            vacancyDto.setExpTo(vacancy.getExpTo());
            vacancyDto.setActive(vacancy.isActive());
            vacancyDto.setCreatedDate(vacancy.getCreatedDate());
            vacancyDto.setUpdateTime(vacancy.getUpdateTime());
            vacancyDtos.add(vacancyDto);
        });
        return vacancyDtos;
    }

    @Override
    public List<VacancyDto> getVacanciesByCategoryId(Integer categoryId) {
        List<Vacancy> vacancies = vacancyDao.getVacanciesByCategoryId(categoryId);
        List<VacancyDto> vacancyDtos = new ArrayList<>();
        vacancies.forEach(vacancy -> {
            VacancyDto vacancyDto = new VacancyDto();
            vacancyDto.setId(vacancy.getId());
            vacancyDto.setCategoryId(vacancy.getCategoryId());
            vacancyDto.setAuthorId(vacancy.getAuthorId());
            vacancyDto.setName(vacancy.getName());
            vacancyDto.setDescription(vacancy.getDescription());
            vacancyDto.setSalary(vacancy.getSalary());
            vacancyDto.setExpFrom(vacancy.getExpFrom());
            vacancyDto.setExpTo(vacancy.getExpTo());
            vacancyDto.setActive(vacancy.isActive());
            vacancyDto.setCreatedDate(vacancy.getCreatedDate());
            vacancyDto.setUpdateTime(vacancy.getUpdateTime());
            vacancyDtos.add(vacancyDto);
    });
        return vacancyDtos;
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
            userDto.setAccountType(user.getAccountType());
            userDtos.add(userDto);
        });
        return userDtos;
    }
}
