package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.UserEditDto;
import kg.attractor.job_search.dto.UserRegisterDto;

import java.util.List;

public interface UserService {

    void register(UserRegisterDto userRegisterDto);

    boolean existsByEmail(String email);

    List<UserDto> searchUsers(String name, String phoneNumber, String email);

    List<UserDto> getAllUsers();

    UserDto getUserById(Integer id);

    void create(UserDto userDto);

    void edit(Integer id, UserEditDto userEditDto);

}
