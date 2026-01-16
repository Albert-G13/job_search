package kg.attractor.job_search.dao;

import kg.attractor.job_search.dto.WorkExperienceInfoDto;
import kg.attractor.job_search.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkExperienceInfoDao {
    private final JdbcTemplate jdbcTemplate;

    public void create(WorkExperienceInfo workExperienceInfo) {
        String sql = "INSERT INTO WORK_EXPERIENCE_INFO (" +
                "RESUME_ID, " +
                "YEARS, " +
                "COMPANY_NAME, " +
                "POSITION, " +
                "RESPONSIBILITIES) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                workExperienceInfo.getResumeId(),
                workExperienceInfo.getYears(),
                workExperienceInfo.getCompanyName(),
                workExperienceInfo.getPosition(),
                workExperienceInfo.getResponsibilities());
    }
    public void deleteByResumeId(Integer resumeId) {
        String sql = "DELETE FROM WORK_EXPERIENCE_INFO WHERE RESUME_ID = ?";
        jdbcTemplate.update(sql, resumeId);
    }

    public List<WorkExperienceInfo> getListByResumeId(Integer id) {
        String sql = "SELECT * FROM WORK_EXPERIENCE_INFO WHERE RESUME_ID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfo.class), id);
    }
}
