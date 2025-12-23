package kg.attractor.job_search.dto;

import lombok.Data;

@Data
public class WorkExperienceInfoDto {
    private Integer id;
    private Integer resumeId;
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}
