package kg.attractor.job_search.service.impl;

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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
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

        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        if (!userRepository.existsById(userRegisterDto.getRoleId())) {
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

        userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<UserDto> searchUsers(String name, String phoneNumber, String email) {
        List<User> users = userRepository.findByNameAndPhoneNumberAndEmail(name, phoneNumber, email);
        return users.stream().map(this::convertUserToDto).toList();
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertUserToDto).toList();
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

    @Override
    public List<UserDto> getRespondedApplicantsByVacancyId(Integer vacancyId) {
        List<User> users = userRepository.findRespondedApplicantsByVacancyId(vacancyId);
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setSurname(user.getSurname());
            userDto.setAge(user.getAge());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setAvatar(user.getAvatar());
            userDto.setRoleId(user.getRole().getId());
            userDtos.add(userDto);
        });
        return userDtos;
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
