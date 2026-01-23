package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private float salary;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    @ManyToOne
    @JoinColumn(name = "APPLICANT_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
}
