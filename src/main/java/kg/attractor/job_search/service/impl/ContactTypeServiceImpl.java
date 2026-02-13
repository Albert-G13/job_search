package kg.attractor.job_search.service.impl;

import kg.attractor.job_search.dto.ContactTypeDto;
import kg.attractor.job_search.model.ContactType;
import kg.attractor.job_search.repository.ContactTypeRepository;
import kg.attractor.job_search.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl implements ContactTypeService {
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public List<ContactTypeDto> findAll() {
        List<ContactType> contactTypes = contactTypeRepository.findAll();
        List<ContactTypeDto> contactTypeDtos = new ArrayList<>();
        contactTypes.forEach(contactType -> {
            ContactTypeDto dto = new ContactTypeDto();
            dto.setId(contactType.getId());
            dto.setType(contactType.getType());
            contactTypeDtos.add(dto);
        });
        return contactTypeDtos;
    }

}
