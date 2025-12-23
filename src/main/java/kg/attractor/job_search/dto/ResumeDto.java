package kg.attractor.job_search.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResumeDto {
    private Integer id;
    private Integer applicantId;
    private Integer categoryId;
    private String name;
    private float salary;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    private List<EducationInfoDto> education;
    private List<WorkExperienceInfoDto> workExperience;
}
