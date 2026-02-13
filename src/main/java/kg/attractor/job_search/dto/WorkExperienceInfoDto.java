package kg.attractor.job_search.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceInfoDto {
    private Integer id;
    private Integer resumeId;
    @NotNull
    @Min(value = 0, message = "Years cannot be negative")
    private Integer years;
    @NotBlank
    @Size(min = 3, max = 100)
    private String companyName;
    @NotBlank
    @Size(min = 3, max = 100)
    private String position;
    @NotBlank
    @Size(min = 3, max = 100)
    private String responsibilities;
}
