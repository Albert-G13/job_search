package kg.attractor.job_search.dao;

import kg.attractor.job_search.dto.EducationInfoDto;
import kg.attractor.job_search.model.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EducationInfoDao {
    private final JdbcTemplate jdbcTemplate;

    public void create(EducationInfo educationInfo) {
        String sql = "INSERT INTO EDUCATION_INFO (" +
                "INSTITUTION, " +
                "PROGRAM, " +
                "START_DATE," +
                "END_DATE," +
                "DEGREE," +
                "RESUME_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                educationInfo.getInstitution(),
                educationInfo.getProgram(),
                educationInfo.getStartDate(),
                educationInfo.getEndDate(),
                educationInfo.getDegree(),
                educationInfo.getResume().getId());
    }
    public void deleteByResumeId(Integer resumeId) {
        String sql = "DELETE FROM EDUCATION_INFO WHERE RESUME_ID = ?";
        jdbcTemplate.update(sql, resumeId);
    }

    public List<EducationInfoDto> getListByResumeId(Integer id) {
        String sql = "SELECT * FROM EDUCATION_INFO WHERE RESUME_ID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EducationInfoDto.class), id);
    }
}
