package kg.attractor.job_search.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EducationInfoDto {
    private Integer id;
    private Integer resumeId;
    private String institution;
    private String program;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String degree;
}
