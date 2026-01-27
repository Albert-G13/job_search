package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.dto.VacancyUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VacancyService {

    Page<VacancyDto> findByAuthorId(Integer authorId, Pageable page);

    Page<VacancyDto> findAllVacancies(Pageable page);

    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getVacanciesByRespondedId(Integer applicantId);

    List<VacancyDto> getVacanciesByCategoryId(Integer categoryId);

    List<UserDto> getRespondedApplicantsByVacancyId(Integer vacancyId);

    VacancyDto getById(Integer id);

    void edit(Integer id, VacancyUpdateDto vacancyDto);

    VacancyDto create(VacancyDto vacancyDto, Integer authorId);

    void delete(Integer id);

    Integer update(Integer id, VacancyDto vacancyDto);

    VacancyUpdateDto getForUpdate(Integer id);
}
