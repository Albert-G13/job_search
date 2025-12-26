package kg.attractor.job_search.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EducationInfoDto {
    private Integer id;
    @NotNull(message = "Resume id is required")
    private Integer resumeId;
    @NotBlank(message = "Institution is required")
    @Size(max = 100)
    private String institution;
    @NotBlank(message = "Program is required")
    @Size(max = 100)
    private String program;
    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;
    @NotNull(message = "End date is required")
    private LocalDateTime endDate;
    @NotBlank(message = "Degree is required")
    private String degree;
}
