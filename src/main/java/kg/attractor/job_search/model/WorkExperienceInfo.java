package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work_experience_info")
public class WorkExperienceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;

    @ManyToOne
    @JoinColumn(name = "RESUME_ID")
    private Resume resume;
}
