package kg.attractor.job_search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEditDto {
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
}

