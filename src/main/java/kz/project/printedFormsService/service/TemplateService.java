package kz.project.printedFormsService.service;

import kz.project.printedFormsService.ValidationException;
import kz.project.printedFormsService.data.dto.TemplateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TemplateService {
    Map<String, byte[]> getTemplate(String code);

    TemplateDto save(TemplateDto dto, List<MultipartFile> files) throws IOException, ValidationException;

    TemplateDto edit(TemplateDto dto, List<MultipartFile> files) throws IOException, ValidationException;

    void delete(String code);

    Page<TemplateDto> getAllTemplate(Boolean isActive, Pageable pageable);

    TemplateDto getTemplateData(Long id) throws ValidationException;

    Page<TemplateDto> getAllTemplateByCode(String code, Pageable pageable) throws ValidationException;
}
