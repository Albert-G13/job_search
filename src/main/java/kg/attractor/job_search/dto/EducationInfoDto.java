package kg.attractor.job_search.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationInfoDto {
    private Integer id;
    private Integer resumeId;
    @NotBlank(message = "Institution is required")
    @Size(min = 3, max = 100)
    private String institution;
    @NotBlank(message = "Program is required")
    @Size(min = 3, max = 100)
    private String program;
    @NotNull(message = "Start date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "End date is required")
    private LocalDate endDate;
    @Size(min = 3, max = 100)
    @NotBlank(message = "Degree is required")
    private String degree;
}
