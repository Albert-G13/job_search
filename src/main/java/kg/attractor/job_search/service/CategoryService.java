package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
}
