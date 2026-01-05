package kg.attractor.job_search.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final DataSource dataSource;

    @Autowired
    private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        String userQuery = "select\n" +
                "    email,\n" +
                "    password,\n" +
                "    enabled\n" +
                "from USER_TABLE\n" +
                "where email = ?;";

        String roleQuery = "select\n" +
                "    email,\n" +
                "    role\n" +
                "from ROLES r, USER_TABLE u\n" +
                "where u.EMAIL = ?\n" +
                "and u.ROLE_ID = r.ID;";

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(roleQuery);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers("/employer/vacancies").permitAll()
                        .requestMatchers("/applicant/**").hasAuthority("APPLICANT")
                        .requestMatchers("/employer/**").hasAuthority("EMPLOYER")
                        .requestMatchers("/users/register", "/users/exists").permitAll()
                        .requestMatchers("/users/**").authenticated()
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}
