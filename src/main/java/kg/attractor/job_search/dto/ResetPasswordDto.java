package kg.attractor.job_search.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordDto {

    @NotBlank(message = "Token is required")
    private String token;
    @NotBlank
    @Size(
            min = 5, max = 20,
            message = "Length must be greater/equals 5 and less/equals than 20 digits"
    )
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$",
            message = "Should contain at least one UPPERCASE letter, one number"
    )
    private String password;
}
