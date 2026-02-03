package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.RoleDto;
import kg.attractor.job_search.model.Role;
import kg.attractor.job_search.repository.RoleRepository;
import kg.attractor.job_search.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public List<RoleDto> findAll(){
        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        roles.forEach(role -> {
            RoleDto dto = new RoleDto();
            dto.setId(role.getId());
            dto.setRole(role.getRole());
            roleDtos.add(dto);
        });
        return roleDtos;
    }
}
