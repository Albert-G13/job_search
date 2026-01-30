package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.ContactTypeDto;

import java.util.List;

public interface ContactTypeService {
    List<ContactTypeDto> findAll();
}
