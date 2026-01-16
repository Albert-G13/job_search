package kg.attractor.job_search.dao;

import kg.attractor.job_search.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleDao {

    private final JdbcTemplate jdbcTemplate;

    public Optional<Role> getById(Integer id) {
        String sql = "SELECT * FROM roles WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Role.class), id)
                .stream().findFirst();
    }

    public boolean existsById(Integer id) {
        String sql = "SELECT COUNT(*) FROM roles WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
