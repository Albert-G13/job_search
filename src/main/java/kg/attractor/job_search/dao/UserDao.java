package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public Optional<User> getUserById(Long id) {
        String sql = "SELECT * FROM USERS_TABLE WHERE id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                )
        );
    }
}
