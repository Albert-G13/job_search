package kg.attractor.job_search.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    private Integer id;
    @NotNull
    private Integer applicantId;
    @NotNull
    private Integer categoryId;
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
    @Positive(message = "Salary must be positive")
    private float salary;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    @Valid
    private List<EducationInfoDto> education;
    @Valid
    private List<WorkExperienceInfoDto> workExperience;
}
