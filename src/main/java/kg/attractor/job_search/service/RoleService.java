package kg.attractor.job_search.service;

import kg.attractor.job_search.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();
}
