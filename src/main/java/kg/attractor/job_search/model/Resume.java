package kg.attractor.job_search.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resume {
    private Integer id;
    private Integer applicantId;
    private Integer categoryId;
    private String name;
    private float salary;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
