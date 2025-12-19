package kg.attractor.job_search.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String accountType;
}
