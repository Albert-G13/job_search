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

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeEditDto {
    private Integer id;
    @NotNull
    private Integer categoryId;
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;
    @NotNull
    @Positive(message = "Salary must be positive")
    private Float salary;
    private Boolean active;
    @Valid
    private List<EducationInfoDto> education = new ArrayList<>();
    @Valid
    private List<WorkExperienceInfoDto> workExperience = new ArrayList<>();
    @Valid
    private List<ContactInfoDto> contacts = new ArrayList<>();
}

