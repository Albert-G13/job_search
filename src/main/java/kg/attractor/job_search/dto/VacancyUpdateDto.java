package kg.attractor.job_search.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VacancyUpdateDto {
    private Integer id;
    private Integer categoryId;

    @Size(min = 3, max = 100)
    private String name;

    @Size(min = 3, max = 2000)
    private String description;

    @Positive
    private Float salary;

    @Min(0)
    private Integer expFrom;

    @Min(0)
    private Integer expTo;

    private Boolean isActive;
}
