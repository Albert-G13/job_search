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
public class UserRegisterDto {

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
    @NotNull
    private Integer roleId;
}

