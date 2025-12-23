package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getVacanciesByRespondedId(Integer applicantId);

    List<VacancyDto> getVacanciesByCategoryId(Integer categoryId);

    List<UserDto> getRespondedApplicantsByVacancyId(Integer vacancyId);

    VacancyDto getById(Integer id);

    void edit(Integer id, VacancyDto vacancyDto);

    Integer create(VacancyDto vacancyDto);

    void delete(Integer id);

    void update(Integer id, VacancyDto vacancyDto);
}
