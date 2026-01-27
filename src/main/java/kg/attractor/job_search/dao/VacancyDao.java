package kg.attractor.job_search.dao;

import kg.attractor.job_search.dto.VacancyDto;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

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
        String sql = "SELECT * FROM vacancies WHERE CATEGORY_ID = ?";
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

    public Optional<Vacancy> getById(Integer id) {
        String sql = "SELECT * FROM VACANCIES WHERE ID = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(
                                sql,
                                new BeanPropertyRowMapper<>(Vacancy.class),
                                id
                        )
                )
        );
    }

    public Integer create(VacancyDto vacancyDto) {
        String sql = "INSERT INTO VACANCIES (" +
                "NAME, " +
                "DESCRIPTION, " +
                "SALARY, " +
                "EXP_FROM, " +
                "EXP_TO, " +
                "CREATED_DATE, " +
                "UPDATE_TIME, " +
                "CATEGORY_ID, " +
                "AUTHOR_ID)" +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(com -> {
            PreparedStatement ps = com.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, vacancyDto.getName());
            ps.setString(2, vacancyDto.getDescription());
            ps.setFloat(3, vacancyDto.getSalary());
            ps.setInt(4, vacancyDto.getExpFrom());
            ps.setInt(5, vacancyDto.getExpTo());
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(8, vacancyDto.getCategoryId());
            ps.setInt(9, vacancyDto.getAuthorId());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public void edit(Vacancy vacancy) {
        String sql = """
        UPDATE vacancies SET
        name = ?,
        description = ?,
        salary = ?,
        exp_from = ?,
        exp_to = ?,
        is_active = ?,
        category_id = ?,
        update_time = ?
        WHERE id = ?
    """;

        jdbcTemplate.update(sql,
                vacancy.getName(),
                vacancy.getDescription(),
                vacancy.getSalary(),
                vacancy.getExpFrom(),
                vacancy.getExpTo(),
                vacancy.isActive(),
                vacancy.getCategory().getId(),
                Timestamp.valueOf(vacancy.getUpdateTime()),
                vacancy.getId()
        );
    }
    public void deleteById(Integer id) {
        String sql = "DELETE FROM VACANCIES WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }
}
