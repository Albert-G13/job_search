package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceInfoRepository extends JpaRepository<WorkExperienceInfo, Integer> {

    void deleteByResume_Id(Integer resumeId);

    List<WorkExperienceInfo> findByResume_Id(Integer resumeId);
}
