package kz.project.printedFormsService.service.impl;

import kz.project.printedFormsService.data.dto.DTemplateTypeDto;
import kz.project.printedFormsService.data.entity.dict.DTemplateType;
import kz.project.printedFormsService.data.repository.DTemplateTypeRepository;
import kz.project.printedFormsService.service.DTemplateTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DTemplateTypeServiceImpl implements DTemplateTypeService {
    private final DTemplateTypeRepository repository;

    List<DTemplateTypeDto> getAllTemplateType(){
        List<DTemplateType> all = repository.findAll();
        return DTemplateTypeDto.toDtoList(all);
    }
}
