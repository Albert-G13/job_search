package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.RoleDao;
import kg.attractor.job_search.dao.UserDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.UserEditDto;
import kg.attractor.job_search.exceptions.InvalidRoleException;
import kg.attractor.job_search.exceptions.UserAlreadyExistsException;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleDao roleDao;

    @Override
    public void register(String email, String password, String phoneNumber, Integer roleId) {

        if (userDao.existsByEmail(email)) {
            throw new UserAlreadyExistsException();
        }

        if (!roleDao.existsById(roleId)) {
            throw new InvalidRoleException("Неверная роль");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .roleId(roleId)
                .enabled(true)
                .build();

        userDao.register(user);
    }


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
    public void create(UserDto userDto) {

        if (userDao.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        if (!roleDao.existsById(userDto.getRoleId())) {
            throw new InvalidRoleException("Неверная роль");
        }

        User user = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phoneNumber(userDto.getPhoneNumber())
                .avatar(userDto.getAvatar())
                .roleId(userDto.getRoleId())
                .enabled(true)
                .build();
        userDao.create(user);
    }

    @Override
    public void edit(Integer id, UserEditDto userEditDto) {
        User user = userDao.getUserById(id)
                .orElseThrow(UserNotFoundException::new);

        if (userEditDto.getName() != null && !userEditDto.getName().isBlank()) {
            user.setName(userEditDto.getName());
        }
        if (userEditDto.getSurname() != null && !userEditDto.getSurname().isBlank()) {
            user.setSurname(userEditDto.getSurname());
        }
        if (userEditDto.getAge() != null && userEditDto.getAge() >= 14 && userEditDto.getAge() <= 100) {
            user.setAge(userEditDto.getAge());
        }
        if (userEditDto.getEmail() != null && !userEditDto.getEmail().isBlank()) {
            user.setEmail(userEditDto.getEmail());
        }
        if (userEditDto.getPassword() != null && !userEditDto.getPassword().isBlank()) {
            String hashed = passwordEncoder.encode(userEditDto.getPassword());
            user.setPassword(hashed);
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
                .role(user.getRole())
                .roleId(user.getRoleId())
                .build();
    }
}
