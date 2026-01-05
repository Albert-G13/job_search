package kg.attractor.job_search.service;

import jakarta.validation.Valid;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.UserEditDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserService {

    boolean existsByEmail(String email);

    List<UserDto> searchUsers(String name, String phoneNumber, String email);

    List<UserDto> getAllUsers();

    UserDto getUserById(Integer id);

    HttpStatus create(UserDto userDto);

    void edit(Integer id, UserEditDto userEditDto);
}
