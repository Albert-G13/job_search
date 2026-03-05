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
    private String surname;
    @NotNull
    private Integer age;
    @NotBlank
    @Size(
            min = 12, max = 12,
            message = "{userDto.phoneSize}"
    )
    @Pattern(regexp = "^\\d+$", message = "{userDto.phonePattern}")
    private String phoneNumber;
    private String avatar;
}

