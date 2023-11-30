package kz.project.printedFormsService.service;

import kz.project.printedFormsService.data.dto.ResponseDto;
import kz.project.printedFormsService.data.dto.TemplateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TemplateService {
    Map<String, byte[]> getTemplate(String code);

    void save(TemplateDto dto);

    ResponseDto edit(TemplateDto dto);

    void delete(String code);

    Page<TemplateDto> getAllTemplate(Boolean isActive, Pageable pageable);
}
