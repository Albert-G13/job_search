package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "responded_applicants")
public class RespondedApplicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean confirmation;

    @ManyToOne
    @JoinColumn(name = "RESUME_ID")
    private Resume resume;

    @ManyToOne
    @JoinColumn(name = "VACANCY_ID")
    private Vacancy vacancy;
}
