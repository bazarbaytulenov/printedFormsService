package kz.project.printedFormsService.service.impl;

import kz.project.printedFormsService.data.dto.TemplateDto;
import kz.project.printedFormsService.data.entity.TemplateEntity;
import kz.project.printedFormsService.data.repository.DTemplateTypeRepository;
import kz.project.printedFormsService.data.repository.TemplateRepository;
import kz.project.printedFormsService.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository repository;
    private final DTemplateTypeRepository dTemplateTypeRepository;

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
    public void save(TemplateDto dto, List<MultipartFile> files) throws IOException {
        TemplateEntity templateEntity = new TemplateEntity();
        if (dto.getCode() == null) return;
        templateEntity.setCode(dto.getCode());
        templateEntity.setData(files.get(0).getResource().getContentAsByteArray());
        templateEntity.setHeader(files.size()==2?files.get(1).getResource().getContentAsByteArray():null);
        templateEntity.setNameBody(dto.getDataName());
        templateEntity.setNameHeader(dto.getHeaderName());
        templateEntity.setType(dTemplateTypeRepository.findByCode(dto.getType()).orElseThrow());
        templateEntity.setIsActive(dto.getIsActive());
        repository.save(templateEntity);

    }

    @Override
    public void edit(TemplateDto dto, List<MultipartFile> files) throws IOException {
        TemplateEntity templateEntity = repository.findByCode(dto.getCode()).orElse(null);
        if (templateEntity == null) throw new RuntimeException("edit data is empty");
        templateEntity.setCode(dto.getCode());
        templateEntity.setData(files.get(0).getResource().getContentAsByteArray());
        templateEntity.setHeader(files.size()==2?files.get(1).getResource().getContentAsByteArray():null);
        templateEntity.setNameBody(dto.getDataName());
        templateEntity.setNameHeader(dto.getHeaderName());
        templateEntity.setType(dTemplateTypeRepository.findByCode(dto.getType()).orElseThrow());
        templateEntity.setIsActive(dto.getIsActive());
        repository.save(templateEntity);
        //return new ResponseDto("eddit is succes save", null, null);

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

    @Override
    public TemplateDto getTemplateData(String code) {
        return repository.findFirstByCodeOrderByVersionDesc(code).orElse(null);
    }

    @Override
    public Page<TemplateDto> getAllTemplateByCode(String code, Pageable pageable) {
        if (code != null)
            return TemplateDto.toDtoList(repository.findAllByCode(pageable, code));
        return null;
    }
}
