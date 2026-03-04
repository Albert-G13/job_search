package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {

    Page<Vacancy> findAllByActiveIsTrue(Pageable pageable);

    Page<Vacancy> findByUser_Id(Integer userId, Pageable pageable);

    Page<Vacancy> findAllByActive(boolean active, Pageable pageable);

    @Query("SELECT v FROM Vacancy v " +
            "LEFT JOIN RespondedApplicant ra ON ra.vacancy = v " +
            "GROUP BY v " +
            "ORDER BY COUNT(ra) DESC")
    Page<Vacancy> findAllOrderByRespondedApplicantsCount(Pageable pageable);

    @Query("SELECT v FROM Vacancy v " +
            "LEFT JOIN RespondedApplicant ra ON ra.vacancy = v " +
            "WHERE v.active = true " +
            "AND (:categoryId IS NULL OR v.category.id = :categoryId) " +
            "AND (:name IS NULL OR LOWER(v.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "GROUP BY v " +
            "ORDER BY COUNT(ra) DESC")
    Page<Vacancy> findAllOrderByRespondedCount(Integer categoryId, String name, Pageable pageable);

    List<Vacancy> findAll();

    @Query("SELECT v FROM Vacancy v " +
            "LEFT JOIN RespondedApplicant ra ON ra.vacancy = v " +
            "LEFT JOIN Resume r ON ra.resume = r " +
            "WHERE r.user.id = :userId")
    List<Vacancy> findByRespondedApplicantId(Integer userId);

    List<Vacancy> findByCategory_Id(Integer categoryId);

    Optional<Vacancy> findById(Integer id);

    void deleteById(Integer id);

    @Query("SELECT v FROM Vacancy v WHERE v.active = true " +
            "AND (:categoryId IS NULL OR v.category.id = :categoryId) " +
            "AND (:name IS NULL OR LOWER(v.name) LIKE LOWER(CONCAT('%', :name, '%')))")
    Page<Vacancy> findVacanciesByFilter(Integer categoryId, String name, Pageable pageable);
}
