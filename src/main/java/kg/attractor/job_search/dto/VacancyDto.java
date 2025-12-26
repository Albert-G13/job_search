package kg.attractor.job_search.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VacancyDto {
    private Integer id;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer authorId;
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
    @NotBlank
    @Size(min = 3, max = 2000)
    private String description;
    @Positive
    private float salary;
    @Min(0)
    private int expFrom;
    @Min(0)
    private int expTo;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
