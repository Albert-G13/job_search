package kg.attractor.job_search.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VacancyDto {
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
