package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Resume> getAllResumes() {
        String sql = "SELECT * FROM resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }
    public List<Resume> getList(Integer id) {
        String sql = "SELECT * FROM resumes WHERE category_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }
    public List<Resume> getListByApplicantId(Integer id) {
        String sql = "SELECT * FROM resumes WHERE applicant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id);
    }
}
