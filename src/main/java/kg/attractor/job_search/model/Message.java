package kg.attractor.job_search.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {
    private Integer id;
    private Integer respondedApplicantsId;
    private String content;
    private LocalDateTime timestamp;
}
