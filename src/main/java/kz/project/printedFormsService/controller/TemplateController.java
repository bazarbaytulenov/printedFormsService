package kz.project.printedFormsService.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.project.printedFormsService.ValidationException;
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
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные получены успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = Map.class))
                    }),


            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка сервиса",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = ValidationException.class))
                    })
    })

    public Map<String, byte[]> getTemplate(@Parameter(name = "code", required = true) @PathVariable String code) {
        return service.getTemplate(code);
    }

    @GetMapping("get/{id}")
    @Operation(description = "Метод для получения шаблона по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные получены успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = TemplateDto.class))
                    }),


            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка сервиса",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = ValidationException.class))
                    })
    })

    public ResponseEntity<TemplateDto> getTemplateData(@Parameter(name = "id", required = true) @PathVariable Long id) throws ValidationException {

        TemplateDto templateData = service.getTemplateData(id);
        return ResponseEntity.ok(templateData);
    }

    @PutMapping(value = "/save")
    @Operation(description = "Метод сохранения шаблона")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные получены успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = TemplateDto.class))
                    }),


            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка сервиса",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = ValidationException.class))
                    })
    })
    public ResponseEntity<TemplateDto> seveTemplate(@Parameter(name = "data") @RequestParam("data") MultipartFile data,
                                                    @Parameter(name = "header") @RequestParam(name = "header", required = false) MultipartFile header,
                                                    @RequestParam TemplateDto dto


    ) throws IOException, ValidationException {
        if (header != null) {
            return ResponseEntity.ok(service.save(dto, List.of(data,header)));
        } else {
            return ResponseEntity.ok(service.save(dto, List.of(data)));

        }
    }

    @PutMapping(value = "/edit")
    @Operation(description = "Метод для сохранение изменения")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные получены успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = TemplateDto.class))
                    }),


            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка сервиса",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = ValidationException.class))
                    })
    })

    public ResponseEntity<TemplateDto> editTemplate(@Parameter(name = "data") @RequestParam("data") MultipartFile data,
                                                    @Parameter(name = "header") @RequestParam(name = "header", required = false) MultipartFile header,
                                                    @RequestParam TemplateDto dto) throws IOException, ValidationException {
        if (header != null) {
            return ResponseEntity.ok(service.edit(dto, List.of(data, header)));
        } else {
            return ResponseEntity.ok(service.edit(dto, List.of(data)));

        }
    }

    @DeleteMapping("/delete/{code}")
    @Operation(description = "Метод для удаления шаблона по идентификатору")
    public ResponseEntity<String> deleteTemplate(@Parameter(name = "code", required = true) @PathVariable("code") String code) {
        service.delete(code);
        return ResponseEntity.ok("delete is success");
    }

    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные получены успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = Page.class))
                    }),


            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка сервиса",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = ValidationException.class))
                    })
    })

    @Operation(description = "Метод для получения всех шаблонов")
    public Page<TemplateDto> getAll(@Parameter(name = "isActive") @RequestParam(value = "isActive", required = false) Boolean isActive,
                                    @Parameter(name = "page") @RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @Parameter(name = "size") @RequestParam(value = "size", defaultValue = "50") Integer size) {
        return service.getAllTemplate(isActive, PageRequest.of(page, size));
    }

    @GetMapping("/allVersion/{code}")
    @Operation(description = "Метод для получения всех шаблонов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные получены успешно",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = Page.class))
                    }),


            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка сервиса",
                    content = {
                            @Content(
                                    mediaType = "application/json",

                                    schema = @Schema(implementation = ValidationException.class))
                    })
    })
    public Page<TemplateDto> getAllByCode(@Parameter(name = "code") @PathVariable(value = "code") String code,
                                          @Parameter(name = "page") @RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @Parameter(name = "size") @RequestParam(value = "size", defaultValue = "50") Integer size) throws ValidationException {
        return service.getAllTemplateByCode(code, PageRequest.of(page, size));
    }

}
