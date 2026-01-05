package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.UserDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.UserEditDto;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.exceptions.VacancyNotFoundException;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public List<UserDto> searchUsers(String name, String phoneNumber, String email) {
        List<User> users = userDao.searchUsers(name, phoneNumber, email);
        return users.stream().map(this::convertUserToDto).toList();
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        return users.stream().map(this::convertUserToDto).toList();
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userDao.getUserById(id)
                .orElseThrow(UserNotFoundException::new);
        return convertUserToDto(user);
    }

    @Override
    public HttpStatus create(UserDto userDto) {

        return userDao.create(userDto);
    }

    @Override
    public void edit(Integer id, UserEditDto userEditDto) {
        User user = userDao.getUserById(id)
                .orElseThrow(UserNotFoundException::new);

        if (userEditDto.getName() != null && !userEditDto.getName().isBlank()) {
            user.setName(userEditDto.getName());
        }
        if (user.getSurname() != null && !userEditDto.getSurname().isBlank()) {
            user.setSurname(userEditDto.getSurname());
        }
        if (userEditDto.getAge() != null && userEditDto.getAge() >= 14 && userEditDto.getAge() <= 100) {
            user.setAge(userEditDto.getAge());
        }
        if (userEditDto.getEmail() != null && !userEditDto.getEmail().isBlank()) {
            user.setEmail(userEditDto.getEmail());
        }
        if (userEditDto.getPassword() != null && !userEditDto.getPassword().isBlank()) {
            user.setPassword(userEditDto.getPassword());
        }
        if (userEditDto.getPhoneNumber() != null && !userEditDto.getPhoneNumber().isBlank()) {
            user.setPhoneNumber(userEditDto.getPhoneNumber());
        }
        if (userEditDto.getAvatar() != null) {
            user.setAvatar(userEditDto.getAvatar());
        }

        userDao.edit(user);
    }


    private UserDto convertUserToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build();
    }
}
