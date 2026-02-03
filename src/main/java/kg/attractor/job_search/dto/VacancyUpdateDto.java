package kg.attractor.job_search.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyUpdateDto {
    private Integer id;
    private Integer categoryId;

    @Size(min = 3, max = 100)
    private String name;

    @Size(min = 3, max = 2000)
    private String description;

    @Positive
    private Float salary;

    @Min(value = 0, message = "опыт работы не должен быть отрицательным или пустым")
    private Integer expFrom;

    @Min(value = 0, message = "опыт работы не должен быть отрицательным или пустым")
    private Integer expTo;

    private Boolean active;
}
