package kg.attractor.job_search.dao;

import kg.attractor.job_search.dto.UserDto;
import kg.attractor.job_search.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public boolean existsByEmail(String email) {
        String sql = "SELECT EXISTS (SELECT * FROM USERS_TABLE WHERE email = ?)";
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, email));
    }

    public List<User> searchUsers(String name, String phoneNumber, String email) {
        String sql = "SELECT * FROM USERS_TABLE WHERE (:name IS NULL OR name LIKE :name) AND (:phoneNumber IS NULL OR phone_number LIKE :phoneNumber) AND (:email IS NULL OR email LIKE :email)";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource()
                .addValue("name", name == null ? null : "%" + name + "%")
                .addValue("phoneNumber", phoneNumber == null ? null : "%" + phoneNumber + "%")
                .addValue("email", email == null ? null : "%" + email + "%"),
                new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM USERS_TABLE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }
    public Optional<User> getUserById(Integer id) {
        String sql = "SELECT * FROM USERS_TABLE WHERE id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                )
        );
    }

    public HttpStatus create(UserDto userDto) {
        String sql = "INSERT INTO USERS_TABLE (" +
                "NAME, " +
                "SURNAME, " +
                "AGE, " +
                "EMAIL, " +
                "PASSWORD, " +
                "PHONE_NUMBER, " +
                "AVATAR, " +
                "ACCOUNT_TYPE)" +
                "VALUES (?,?,?,?,?,?,?,?) ";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userDto.getName());
            ps.setString(2, userDto.getSurname());
            ps.setInt(3, userDto.getAge());
            ps.setString(4, userDto.getEmail());
            ps.setString(5, userDto.getPassword());
            ps.setString(6, userDto.getPhoneNumber());
            ps.setString(6, userDto.getAvatar());
            ps.setString(7, userDto.getAccountType());
            return ps;
        }, keyHolder);
        return HttpStatus.CREATED;
    }

    public void edit(User user) {
        String sql = "UPDATE USERS_TABLE SET " +
                "NAME = ?, " +
                "SURNAME = ?, " +
                "AGE = ?, " +
                "EMAIL = ?, " +
                "PASSWORD = ?, " +
                "PHONE_NUMBER = ?, " +
                "AVATAR = ?" +
                "WHERE ID = ?";
        jdbcTemplate.update(sql,
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAvatar(),
                user.getId()
        );
    }
}
