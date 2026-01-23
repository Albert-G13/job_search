package kg.attractor.job_search.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "RESPONDED_APPLICANTS_ID")
    private RespondedApplicant respondedApplicant;
}
