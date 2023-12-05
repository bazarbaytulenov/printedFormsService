package kz.project.printedFormsService.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.project.printedFormsService.data.dto.TemplateDto;
import kz.project.printedFormsService.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Template Controller", description = "API TemplateService")
public class TemplateController {

    private final TemplateService service;

    @Hidden
    @GetMapping("/{code}")
    @Operation(description = "Метод для получения шаблона по идентификатору")
    public Map<String, byte[]> getTemplate(@Parameter(name = "Идентификатор шаблона", required = true) @PathVariable String code) {
        return service.getTemplate(code);
    }

    @GetMapping("get/{code}")
    @Operation(description = "Метод для получения шаблона по идентификатору")
    public ResponseEntity<TemplateDto> getTemplateData(@Parameter(name = "Идентификатор шаблона", required = true) @PathVariable String code) {
        return ResponseEntity.ok(service.getTemplateData(code));
    }

    @PutMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Метод сохранения шаблона")
    public ResponseEntity<String> seveTemplate(@RequestParam("data") MultipartFile data,
                                                @RequestParam(name = "header", required = false) MultipartFile header,
                                               TemplateDto dto) throws IOException {
        if (header != null)
            service.save(dto, List.of(data, header));
        else service.save(dto, List.of(data));
        return ResponseEntity.ok("succes");
    }

    @PutMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "Метод для сохранение изменения")
    public ResponseEntity<String> editTemplate(@Parameter(name = "Шаблон", required = true) @RequestParam("data") MultipartFile data,
                                               @Parameter(name = "Шаблон", required = true) @RequestParam(name = "header", required = false) MultipartFile header,
                                               TemplateDto dto) throws IOException {
        service.edit(dto, List.of(data, header));
        return ResponseEntity.ok("edit is succes");
    }

    @DeleteMapping("/delete/{code}")
    @Operation(description = "Метод для удаления шаблона по идентификатору")
    public ResponseEntity<String> deleteTemplate(@Parameter(name = "Идентификатор шаблона", required = true) @PathVariable("code") String code) {
        service.delete(code);
        return ResponseEntity.ok("delete is succes");
    }

    @GetMapping("/all")
    @Operation(description = "Метод для получения всех шаблонов")
    public Page<TemplateDto> getAll(@Parameter(name = "Признак активности если значение null возвращяется все") @RequestParam(value = "isActive", required = false) Boolean isActive,
                                    @Parameter(name = "Номер страницы") @RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @Parameter(name = "Количество записейв странице") @RequestParam(value = "size", defaultValue = "50") Integer size) {
        return service.getAllTemplate(isActive, PageRequest.of(page, size));
    }

    @GetMapping("/all/{code}")
    @Operation(description = "Метод для получения всех шаблонов")
    public Page<TemplateDto> getAllByCode(@Parameter(name = "Признак активности если значение null возвращяется все") @PathVariable(value = "code") String code,
                                    @Parameter(name = "Номер страницы") @RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @Parameter(name = "Количество записейв странице") @RequestParam(value = "size", defaultValue = "50") Integer size) {
        return service.getAllTemplateByCode(code, PageRequest.of(page, size));
    }

}
