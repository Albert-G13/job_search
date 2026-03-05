package kg.attractor.job_search.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import kg.attractor.job_search.common.Utilities;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.UserEditDto;
import kg.attractor.job_search.dto.UserRegisterDto;
import kg.attractor.job_search.exceptions.*;
import kg.attractor.job_search.model.Role;
import kg.attractor.job_search.model.User;
import kg.attractor.job_search.repository.RoleRepository;
import kg.attractor.job_search.repository.UserRepository;
import kg.attractor.job_search.service.EmailService;
import kg.attractor.job_search.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public void updateLanguage(String email, String lang) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        user.setLanguage(lang);
        userRepository.save(user);
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return convertUserToDto(user);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return userRepository.findByEmail(email).map(this::convertUserToDto).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {

        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        if (!userRepository.existsById(userRegisterDto.getRoleId())) {
            throw new RoleNotFoundException();
        }
        if (userRepository.existsByPhoneNumber(userRegisterDto.getPhoneNumber())) {
            throw new NumberAlreadyExistsException();
        }

        Role role = roleRepository.findById(userRegisterDto.getRoleId())
                .orElseThrow(RoleNotFoundException::new);

        User user = User.builder()
                .age(0)
                .name(userRegisterDto.getName())
                .email(userRegisterDto.getEmail())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .phoneNumber(userRegisterDto.getPhoneNumber())
                .role(role)
                .enabled(true)
                .build();

        userRepository.save(user);
    }

    @Override
    public boolean existsByPhoneNumber(String number) {
        return userRepository.existsByPhoneNumber(number);
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
    public UserEditDto getUserEditById(Integer id, String email) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        if (!user.getEmail().equals(email)) {
            throw new InvalidUserException();
        }
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

        if ("APPLICANT".equals(user.getRole().getRole()))  {
            if (userEditDto.getSurname() == null || userEditDto.getSurname().isBlank()){
                throw new UserSurnameValidException();
            }
            if (userEditDto.getAge() < 14) {
                throw new UserAgeValidException();
            }
        }

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

    @Override
    public void makeResetPasswordLink(HttpServletRequest request) throws UsernameNotFoundException, MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        String resetPasswordLink = Utilities.getSiteUrl(request) + "/auth/reset_password?token=" + token;
        emailService.sendEmail(email, resetPasswordLink);
    }

    private void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
    }

    @Override
    public String getLanguageByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new).getLanguage();
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
