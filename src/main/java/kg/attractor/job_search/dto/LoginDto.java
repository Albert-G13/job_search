package kg.attractor.job_search.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Email должен быть корректным")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 5, message = "Пароль должен быть минимум 5 символов")
    private String password;

}
