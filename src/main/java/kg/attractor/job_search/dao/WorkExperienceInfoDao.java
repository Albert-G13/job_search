package kg.attractor.job_search.dao;

import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkExperienceInfoDao {
    private final JdbcTemplate jdbcTemplate;

    public void create(WorkExperienceInfoDto workExperienceInfoDto) {
        String sql = "INSERT INTO WORK_EXPERIENCE_INFO (" +
                "RESUME_ID, " +
                "YEARS, " +
                "COMPANY_NAME, " +
                "POSITION, " +
                "RESPONSIBILITIES) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                workExperienceInfoDto.getResumeId(),
                workExperienceInfoDto.getYears(),
                workExperienceInfoDto.getCompanyName(),
                workExperienceInfoDto.getPosition(),
                workExperienceInfoDto.getResponsibilities());
    }
    public void deleteByResumeId(Integer resumeId) {
        String sql = "DELETE FROM WORK_EXPERIENCE_INFO WHERE RESUME_ID = ?";
        jdbcTemplate.update(sql, resumeId);
    }

    public List<WorkExperienceInfoDto> getListByResumeId(Integer id) {
        String sql = "SELECT * FROM WORK_EXPERIENCE_INFO WHERE RESUME_ID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfoDto.class), id);
    }
}
