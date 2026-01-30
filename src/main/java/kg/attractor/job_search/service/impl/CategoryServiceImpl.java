package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.CategoryDto;
import kg.attractor.job_search.model.Category;
import kg.attractor.job_search.repository.CategoryRepository;
import kg.attractor.job_search.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> {
            CategoryDto dto = new CategoryDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            dto.setParentId(category.getParentId());
            categoryDtos.add(dto);
        });
        return categoryDtos;
    }
}
