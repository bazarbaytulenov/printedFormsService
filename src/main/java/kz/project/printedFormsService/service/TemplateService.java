package kz.project.printedFormsService.service;

import kz.project.printedFormsService.data.dto.ResponseDto;
import kz.project.printedFormsService.data.dto.TemplateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.Map;

public interface TemplateService {
    Map<String, byte[]> getTemplate(String code);

    void save(TemplateDto dto) throws IOException;

    ResponseDto edit(TemplateDto dto) throws IOException;

    void delete(String code);

    Page<TemplateDto> getAllTemplate(Boolean isActive, Pageable pageable);
}
