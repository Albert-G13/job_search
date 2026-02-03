package kg.attractor.job_search.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacancyDto {
    private Integer id;
    @NotNull
    private Integer categoryId;
    private Integer authorId;
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
    @NotBlank
    @Size(min = 3, max = 2000)
    private String description;
    @Positive(message = "зарплата должна быть положительным числом")
    private float salary;
    @Min(value = 0, message = "опыт работы не должен быть отрицательным или пустым")
    private int expFrom;
    @Min(value = 0, message = "опыт работы не должен быть отрицательным или пустым")
    private int expTo;
    private boolean active;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
