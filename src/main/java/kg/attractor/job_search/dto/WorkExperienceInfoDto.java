package kg.attractor.job_search.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WorkExperienceInfoDto {
    private Integer id;
    @NotNull
    private Integer resumeId;
    @Min(value = 0, message = "Years cannot be negative")
    private Integer years;
    @NotBlank
    private String companyName;
    @NotBlank
    private String position;
    @Size(max = 100)
    private String responsibilities;
}
