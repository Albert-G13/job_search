package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dao.RoleDao;
import kg.attractor.job_search.dao.UserDao;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.UserEditDto;
import kg.attractor.job_search.dto.UserRegisterDto;
import kg.attractor.job_search.exceptions.InvalidRoleException;
import kg.attractor.job_search.exceptions.RoleNotFoundException;
import kg.attractor.job_search.exceptions.UserAlreadyExistsException;
import kg.attractor.job_search.exceptions.UserNotFoundException;
import kg.attractor.job_search.model.Role;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.repository.RoleRepository;
import kg.attractor.job_search.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDto findUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return convertUserToDto(user);
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {

        if (userDao.existsByEmail(userRegisterDto.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        if (!roleDao.existsById(userRegisterDto.getRoleId())) {
            throw new InvalidRoleException("Неверная роль");
        }

        Role role = roleRepository.findById(userRegisterDto.getRoleId())
                .orElseThrow(RoleNotFoundException::new);

        User user = User.builder()
                .email(userRegisterDto.getEmail())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .phoneNumber(userRegisterDto.getPhoneNumber())
                .role(role)
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
    public UserEditDto getUserEditById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return UserEditDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public void create(UserDto userDto) {

        if (userDao.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        if (!roleDao.existsById(userDto.getRoleId())) {
            throw new InvalidRoleException("Неверная роль");
        }

        Role role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(RoleNotFoundException::new);

        User user = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phoneNumber(userDto.getPhoneNumber())
                .avatar(userDto.getAvatar())
                .role(role)
                .enabled(true)
                .build();
        userDao.create(user);
    }

    @Override
    public void edit(Integer id, UserEditDto userEditDto) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if (userEditDto.getName() != null) {
            user.setName(userEditDto.getName());
        }

        if (userEditDto.getSurname() != null) {
            user.setSurname(userEditDto.getSurname());
        }

        if (userEditDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userEditDto.getPhoneNumber());
        }

        if (userEditDto.getAge() != null) {
                    user.setAge(userEditDto.getAge());
        }


        if (userEditDto.getAvatar() != null) {
                    user.setAvatar(userEditDto.getAvatar());
        }

        userRepository.saveAndFlush(user);
    }

    public UserDto convertUserToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .role(user.getRole().getRole())
                .roleId(user.getRole().getId())
                .build();
    }
}
