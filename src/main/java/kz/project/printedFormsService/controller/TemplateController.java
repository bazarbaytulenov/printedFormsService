package kz.project.printedFormsService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.project.printedFormsService.data.dto.ResponseDto;
import kz.project.printedFormsService.data.dto.TemplateDto;
import kz.project.printedFormsService.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/template")
@RequiredArgsConstructor@Slf4j
@CrossOrigin
@Tag(name = "Template Controller" ,description = "API TemplateService")
public class TemplateController {

    private final TemplateService service;

    @GetMapping("/{code}")
    @Operation(description = "Метод для получения шаблона по идентификатору")
    @SecurityRequirement(name = "jsessionid")
    public Map<String, byte[]> getTemplate(@Parameter(name = "Идентификатор шаблона", required = true)@PathVariable("code") String code){
        return service.getTemplate(code);
    }

    @PostMapping("/save")
    @Operation(description = "Метод сохранения шаблона")
    @SecurityRequirement(name = "jsessionid")
    public ResponseEntity seveTemplate(@Parameter(name = "Модель шаблона", required = true)@RequestBody TemplateDto dto){
        service.save(dto);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/edit")
    @Operation(description = "Метод для сохранение изменения")
    @SecurityRequirement(name = "jsessionid")
    public ResponseEntity<ResponseDto> editTemplate(@Parameter(name = "Модель шаблона", required = true)@RequestBody TemplateDto dto){
        ResponseDto edit = service.edit(dto);
        return  ResponseEntity.ok(edit);
    }

    @DeleteMapping("/delete/{code}")
    @Operation(description = "Метод для удаления шаблона по идентификатору")
    @SecurityRequirement(name = "jsessionid")
    public ResponseEntity<ResponseDto> deleteTemplate(@Parameter(name = "Идентификатор шаблона", required = true)@PathVariable("code") String code){
        service.delete(code);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(description = "Метод для получения всех шаблонов")
    @SecurityRequirement(name = "jsessionid")
    public Page<TemplateDto> getAll(@Parameter(name = "Признак активности")@RequestParam(value = "isActive",required = false) Boolean isActive,
                                   @Parameter(name = "Номер страницы")@RequestParam(value = "page",defaultValue = "0") Integer page,
                                   @Parameter(name = "Количество записейв странице")@RequestParam(value = "size",defaultValue = "50") Integer size){
        return service.getAllTemplate(isActive, PageRequest.of(page, size));
    }

}
