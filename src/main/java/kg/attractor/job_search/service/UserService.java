package kg.attractor.job_search.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.dto.UserEditDto;
import kg.attractor.job_search.dto.UserRegisterDto;
import kg.attractor.job_search.model.User;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    UserDto findUserById(Integer id);

    void register(UserRegisterDto userRegisterDto);

    boolean existsByEmail(String email);

    List<UserDto> searchUsers(String name, String phoneNumber, String email);

    List<UserDto> getAllUsers();

    UserEditDto getUserEditById(Integer id);

    void edit(Integer id, UserEditDto userEditDto);

    List<UserDto> getRespondedApplicantsByVacancyId(Integer vacancyId);

    void makeResetPasswordLink(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException;

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);
}
