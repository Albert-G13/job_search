package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.User;
import kg.attractor.job_search.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> getVacanciesByRespondedId(Integer applicantId) {
        String sql = "SELECT v.* " +
                "FROM vacancies v " +
                "JOIN responded_applicants ra ON ra.vacancy_id = v.id " +
                "JOIN resumes r ON ra.resume_id = r.id " +
                "WHERE r.applicant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), applicantId);
    }
    public List<Vacancy> getAllVacancies() {
        String sql = "SELECT * FROM vacancies";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }
    public List<Vacancy> getVacanciesByCategoryId(Integer categoryId) {
        String sql = "SELECT * FROM vacancies WHERE category_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }
    public List<User> getRespondedApplicantsByVacancyId(Integer vacancyId) {
        String sql = "SELECT u.* " +
                "FROM USERS_TABLE u " +
                "JOIN resumes r ON r.applicant_id = u.id " +
                "JOIN responded_applicants ra ON ra.resume_id = r.id " +
                "WHERE ra.vacancy_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), vacancyId);
    }

}
