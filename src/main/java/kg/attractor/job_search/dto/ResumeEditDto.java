package kg.attractor.job_search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeEditDto {
    private Integer categoryId;
    private String name;
    private Float salary;
    private Boolean isActive;
    private List<EducationInfoDto> education;
    private List<WorkExperienceInfoDto> workExperience;
}

