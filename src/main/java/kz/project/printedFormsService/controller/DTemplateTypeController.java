package kz.project.printedFormsService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.project.printedFormsService.data.dto.DTemplateTypeDto;
import kz.project.printedFormsService.service.DTemplateTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dict")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Template Controller", description = "API TemplateService")
public class DTemplateTypeController {
  private final DTemplateTypeService service;

    @GetMapping("get/all")
    @Operation(description = "Метод для получения шаблона по идентификатору")
    public List<DTemplateTypeDto> getDict() {
        return service.getAllTemplateType();
    }
}
