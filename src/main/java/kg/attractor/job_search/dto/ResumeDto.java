package kg.attractor.job_search.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ResumeDto {
    private Integer id;
    private Integer applicantId;
    private Integer categoryId;
    private String name;
    private float salary;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
}
