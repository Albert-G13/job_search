package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {
    void deleteAllByResume_Id(Integer resumeId);

}
