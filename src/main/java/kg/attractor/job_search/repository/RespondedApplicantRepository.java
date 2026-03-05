package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Integer> {
    List<RespondedApplicant> findByResume_User_Id(Integer resumeUserId);

    boolean existsByVacancy_IdAndResume_Id(Integer vacancyId, Integer resumeId);
}
