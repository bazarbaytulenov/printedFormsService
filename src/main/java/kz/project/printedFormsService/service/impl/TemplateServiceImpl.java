package kz.project.printedFormsService.service.impl;

import kz.project.printedFormsService.data.dto.ResponseDto;
import kz.project.printedFormsService.data.dto.TemplateDto;
import kz.project.printedFormsService.data.entity.TemplateEntity;
import kz.project.printedFormsService.data.repository.TemplateRepository;
import kz.project.printedFormsService.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository repository;

    @Override
    public Map<String, byte[]> getTemplate(String code) {
        Map<String,byte[]> params = new HashMap<>();
        TemplateEntity templateEntity = repository.findByCode(code).orElse(null);
        if (templateEntity == null) return null;
        params.put("body", templateEntity.getData());
        params.put("header", templateEntity.getHeader());
        return params;
    }

    @Override
    public void save(TemplateDto dto) {
        TemplateEntity templateEntity = new TemplateEntity();
        if (dto == null) return;
        templateEntity.setCode(dto.getCode());
        templateEntity.setData(dto.getData());
        templateEntity.setHeader(dto.getHeader());
        templateEntity.setNameBody(dto.getNameBody());
        templateEntity.setNameHeader(dto.getNameHeader());
        templateEntity.setType(dto.getType());
        templateEntity.setIsActive(dto.getIsActive());
        repository.save(templateEntity);

    }

    @Override
    public ResponseDto edit(TemplateDto dto) {
        TemplateEntity templateEntity = repository.findByCode(dto.getCode()).orElse(null);
        if (templateEntity == null) return new ResponseDto(null, "TemplateEntity is empty", null);
        templateEntity.setCode(dto.getCode());
        templateEntity.setData(dto.getData());
        templateEntity.setHeader(dto.getHeader());
        templateEntity.setNameBody(dto.getNameBody());
        templateEntity.setNameHeader(dto.getNameHeader());
        templateEntity.setType(dto.getType());
        templateEntity.setIsActive(dto.getIsActive());
        repository.save(templateEntity);
        return new ResponseDto("eddit is succes save", null, null);

    }

    @Override
    public void delete(String code) {
        repository.delete(repository.findByCode(code).get());

    }

    @Override
    public Page<TemplateDto> getAllTemplate(Boolean isActive, Pageable pageable) {
        if (isActive == null) return TemplateDto.toDtoList(repository.findAll(pageable));
        else if (isActive)
            return TemplateDto.toDtoList(repository.findAllByIsActiveTrue(pageable));
        else return TemplateDto.toDtoList(repository.findAllByIsActiveFalse(pageable));

    }
}
