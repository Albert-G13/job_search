package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users_table", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    private String avatar;
    private String role;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role roleId;

    private boolean enabled;
}
