package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationInfoRepository extends JpaRepository<EducationInfo, Integer> {

    void deleteByResume_Id(Integer resumeId);
    void deleteAllByResume_Id(Integer resumeId);
    List<EducationInfo> findByResume_Id(Integer resumeId);
}
