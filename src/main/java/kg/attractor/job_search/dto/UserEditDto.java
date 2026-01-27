package kg.attractor.job_search.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEditDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Min(value = 14, message = "Age must be at least 14")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;
    @NotBlank
    @Size(
            min = 12, max = 12,
            message = "Length must be 12 digits"
    )
    @Pattern(regexp = "^\\d+$", message = "Should contain only digits")
    private String phoneNumber;
    private String avatar;
}

