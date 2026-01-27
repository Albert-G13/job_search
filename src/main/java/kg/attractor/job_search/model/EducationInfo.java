package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "education_info")
public class EducationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String institution;
    private String program;
    private LocalDate startDate;
    private LocalDate endDate;
    private String degree;

    @ManyToOne
    @JoinColumn(name = "RESUME_ID")
    private Resume resume;
}
