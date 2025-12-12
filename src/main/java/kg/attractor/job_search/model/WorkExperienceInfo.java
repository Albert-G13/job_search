package kg.attractor.job_search.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkExperienceInfo {
    private Integer id;
    private Integer resumeId;
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}
