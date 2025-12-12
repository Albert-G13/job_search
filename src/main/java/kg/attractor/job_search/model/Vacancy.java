package kg.attractor.job_search.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Vacancy {
    private Integer id;
    private Integer categoryId;
    private Integer authorId;
    private String name;
    private String description;
    private float salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
