package kg.attractor.job_search.repository;

import kg.attractor.job_search.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(Integer userId);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    List<User> findByNameAndPhoneNumberAndEmail(String name, String phoneNumber, String email);

    @Query("SELECT u FROM User u " +
            "JOIN Resume  r ON r.user.id = u.id " +
            "JOIN RespondedApplicant ra ON ra.resume.id = r.id " +
            "WHERE ra.vacancy.id = :vacancyId")
    List<User> findRespondedApplicantsByVacancyId(Integer vacancyId);

    Optional<User> findByResetPasswordToken(String token);

    @Query("select u from User u " +
            "join Resume r on r.user.id = u.id " +
            "where r.id = :resumeId")
    Optional<User> findByResumeId(Integer resumeId);
}
