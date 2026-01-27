package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {

    Page<Vacancy> findByUser_Id(Integer userId, Pageable pageable);

    @Query("SELECT v FROM Vacancy v " +
            "LEFT JOIN RespondedApplicant ra ON ra.vacancy = v " +
            "GROUP BY v " +
            "ORDER BY COUNT(ra) DESC")
    Page<Vacancy> findAllOrderByRespondedApplicantsCount(Pageable pageable);
}
