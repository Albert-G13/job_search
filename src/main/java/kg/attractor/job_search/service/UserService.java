package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.UserDto;

import java.util.List;

public interface UserService {

    boolean existsByEmail(String email);

    List<UserDto> searchUsers(String name, String phoneNumber, String email);

    List<UserDto> getAllUsers();

    UserDto getUserById(Integer id);
}
