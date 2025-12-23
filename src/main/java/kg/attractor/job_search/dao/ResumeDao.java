package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.Resume;
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
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;
    KeyHolder keyHolder = new GeneratedKeyHolder();

    public List<Resume> getAllResumes() {
        String sql = "SELECT * FROM RESUMES";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }
    public List<Resume> getList(Integer id) {
        String sql = "SELECT * FROM RESUMES WHERE CATEGORY_ID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }
    public List<Resume> getListByApplicantId(Integer id) {
        String sql = "SELECT * FROM RESUMES WHERE APPLICANT_ID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }

    public Optional<Resume> getById(Integer id) {
        String sql = "SELECT * FROM RESUMES WHERE ID = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(
                                sql,
                                new BeanPropertyRowMapper<>(Resume.class),
                                id
                        )
                )
        );
    }

    public void create(Resume resume){
        String sql = "INSERT INTO RESUMES (NAME, SALARY, IS_ACTIVE, CREATED_DATE, UPDATE_TIME, APPLICANT_ID, CATEGORY_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);;
            ps.setString(1, resume.getName());
            ps.setFloat(2, resume.getSalary());
            ps.setBoolean(3, resume.isActive());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(6, resume.getApplicantId());
            ps.setInt(7, resume.getCategoryId());
            return ps;
        }, keyHolder);

        resume.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
    }

    public void edit(Resume resume) {
        String sql = "UPDATE RESUMES SET NAME = ?, SALARY = ?, IS_ACTIVE = ?, UPDATE_TIME = ?, CATEGORY_ID = ? WHERE ID = ?";
        jdbcTemplate.update(sql,
                resume.getName(),
                resume.getSalary(),
                resume.isActive(),
                Timestamp.valueOf(LocalDateTime.now()),
                resume.getCategoryId(),
                resume.getId()
        );
    }

    public void deleteById(Integer resumeId) {
        String sql = "DELETE FROM RESUMES WHERE ID = ?";
        jdbcTemplate.update(sql, resumeId);
    }
}
