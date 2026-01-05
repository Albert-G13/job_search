package kg.attractor.job_search.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Min(value = 14, message = "Age must be at least 14")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(
            min = 8, max = 20,
            message = "Length must be greater/equals 5 and less/equals than 20 digits"
    )
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one UPPERCASE letter, one number"
    )
    private String password;
    @NotBlank
    @Size(
            min = 12, max = 12,
            message = "Length must be 12 digits"
    )
    @Pattern(regexp = "^(\\d)", message = "Should contain only digits")
    private String phoneNumber;
    private String avatar;
    private String accountType;

}
