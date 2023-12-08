package kz.project.printedFormsService.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.project.printedFormsService.data.dto.TemplateDataForReportDto;
import kz.project.printedFormsService.data.dto.TemplateDto;
import kz.project.printedFormsService.data.entity.TemplateEntity;
import kz.project.printedFormsService.data.entity.TemplateFileInfoEntity;
import kz.project.printedFormsService.data.repository.DTemplateTypeRepository;
import kz.project.printedFormsService.data.repository.TemplateFileInfoRepository;
import kz.project.printedFormsService.data.repository.TemplateRepository;
import kz.project.printedFormsService.exception.ValidationException;
import kz.project.printedFormsService.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateServiceImpl implements TemplateService {
    private final TemplateRepository repository;
    private final DTemplateTypeRepository dTemplateTypeRepository;

    private final TemplateFileInfoRepository fileInfoRepository;

    @Override
    public Map<String, byte[]> getTemplate(Long id) throws ValidationException, JsonProcessingException {
        Map<String, byte[]> params = new HashMap<>();
        TemplateEntity templateEntity = repository.findById(id).orElse(null);
        if (templateEntity == null) throw new ValidationException("по данному параметру отсутствуют данные: " + id, 13);
        TemplateDataForReportDto templ= new TemplateDataForReportDto(templateEntity.getType().getCode(), templateEntity.getTemplate().getData(), templateEntity.getTempleateHeader() != null ? templateEntity.getTempleateHeader().getData() : null);
        params.put("type",templ.getType().getBytes(StandardCharsets.UTF_8));
        params.put("body", templ.getBody());
        params.put("header",templ.getHeader());

        return params;

    }

    @Override
    @Transactional
    public TemplateDto save(TemplateDto dto, List<MultipartFile> files) throws IOException, ValidationException {
        if (!validateData(dto)) throw new ValidationException("Не переданы обязательные поля", 13);
        TemplateEntity templateEntity = createTemplateEntity(dto, files);
        TemplateEntity save = repository.save(templateEntity);
        return TemplateDto.toDto(save);

    }

    @Override
    @Transactional
    public TemplateDto edit(TemplateDto dto, List<MultipartFile> files) throws IOException, ValidationException {
        if (!validateData(dto) || dto.getTemplateId() == null || repository.findById(dto.getTemplateId()) == null)
            throw new ValidationException("Не переданы обязательные поля", 13);
        return TemplateDto.toDto(repository.save(createTemplateEntity(dto, files)));

    }

    @Override
    public void delete(String code) {
        repository.delete(repository.findByCode(code).get());

    }

    @Override
    public Page<TemplateDto> getAllTemplate(Boolean isActive, Pageable pageable) {
        if (isActive == null) return TemplateDto.toDtoList(repository.findAll(pageable));
        else if (isActive)
            return TemplateDto.toDtoList(repository.findAllByStatusTrue(pageable));
        else return TemplateDto.toDtoList(repository.findAllByStatusFalse(pageable));

    }

    @Override
    public TemplateDto getTemplateData(Long id) throws ValidationException {
        if (id == null) throw new ValidationException("Не переданы обязательные поля", 13);
        return TemplateDto.toDto(repository.findById(id).orElseThrow(() -> new ValidationException("Не найден шаблон с таким идентификатором или кодом", 13)));
    }

    @Override
    public Page<TemplateDto> getAllTemplateByCode(String code, Pageable pageable) throws ValidationException {
        if (code != null) {
            Page<TemplateEntity> allByCode = repository.findAllByCode(pageable, code);
            if (allByCode != null)
                return TemplateDto.toDtoList(allByCode);
        }
        throw new ValidationException("Не переданы обязательные поля", 13);
    }

    private TemplateEntity createTemplateEntity(TemplateDto dto, List<MultipartFile> files) throws IOException, ValidationException {
        createTempaleFiles(dto, files.get(0).getResource().getContentAsByteArray(), false);
        TemplateEntity templateEntity = new TemplateEntity();
        templateEntity.setCode(dto.getCode());
        templateEntity.setTemplate(createTempaleFiles(dto, files.get(0).getResource().getContentAsByteArray(), false));
        templateEntity.setCode(dto.getCode());
        templateEntity.setStatus(dto.getStatus());
        templateEntity.setType(dTemplateTypeRepository.findByCode(dto.getType()).orElseThrow(() -> new ValidationException("Не найден справочное значение по коду: " + dto.getType(), 13)));
        templateEntity.setVersion(dto.getVersion() + 1);
        if (files.size() == 2 && files.get(1) != null)
            templateEntity.setTempleateHeader(createTempaleFiles(dto, files.get(1).getResource().getContentAsByteArray(), true));
        templateEntity.setStatus(dto.getStatus());
        templateEntity.setVersion(dto.getVersion() != null ? dto.getVersion() + 1 : 1);
        return templateEntity;
    }

    private TemplateFileInfoEntity createTempaleFiles(TemplateDto dto, byte[] files, boolean isHeader) throws IOException {
        if (files.length == 0)
            return null;
        return fileInfoRepository.save(TemplateFileInfoEntity.builder()
                .isHeader(isHeader)
                .name(dto.getTemplateFile().getFileName())
                .data(files)
                .hash(files.hashCode())
                .build());
    }

    private boolean validateData(TemplateDto dto) throws ValidationException {
        if (dto == null
                || dto.getCode() == null
                || dto.getName() == null
                || dto.getStatus() == null
                || dto.getType() == null)
            return false;
        return true;
    }
}
