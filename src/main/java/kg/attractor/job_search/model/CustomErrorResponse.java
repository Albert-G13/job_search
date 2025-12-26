package kg.attractor.job_search.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CustomErrorResponse {
    private String error;
    private Map<String, List<String>> reasons;
}
