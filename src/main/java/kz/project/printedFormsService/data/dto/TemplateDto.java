package kz.project.printedFormsService.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import kz.project.printedFormsService.data.entity.TemplateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "TemplateDto", description = "Модель")
public class TemplateDto {

    @NotBlank
    @Schema(name ="templateId", description = "Код шаблона", defaultValue = "123")
    private Long templateId;

    @NotBlank
    @Schema(name ="code", description = "Код шаблона", defaultValue = "template_1")
    private String code;
    @NotBlank
    @Schema(name ="name", description = "Наименование шаблона",defaultValue = "Шаблон 1")
    private String name;
    @NotBlank
    @Schema(name = "type", description = "Тип фаила")
    private String type;
    @NotBlank
    @Schema(name ="status", description = "Флаг активности", defaultValue = "1")
    private Boolean status;
    @NotBlank
    @Schema(name ="version", description = "Версия активности")
    private Integer version;
    private TemplateFileDataDto templateFile;
    private TemplateFileDataDto headerFile;
    private List<GroupIndoDto>groups;


    public static TemplateDto toDtoShort(TemplateEntity te) {
       return TemplateDto.builder()
                .code(te.getCode())
                .status(te.getStatus())
                .templateFile(new TemplateFileDataDto(te.getTemplate().getId(),te.getTemplate().getName()))
                .headerFile(te.getTempleateHeader()!=null?new TemplateFileDataDto(te.getTempleateHeader().getId(),te.getTempleateHeader().getName()):null)
                .type(te.getType().getCode())
                .version(te.getVersion())
               .groups(List.of(new GroupIndoDto("1","группа-1"),new GroupIndoDto("2","группа-2")))
                .build();
    }

    public static TemplateDto toDto(TemplateEntity te) {
        return TemplateDto.builder()
                .templateId(te.getId())
                .name(te.getTemplate().getName())
                .code(te.getCode())
                .status(te.getStatus())
                .templateFile(new TemplateFileDataDto(te.getTemplate().getId(),te.getTemplate().getName()))
                .headerFile(te.getTempleateHeader()!=null?new TemplateFileDataDto(te.getTempleateHeader().getId(),te.getTempleateHeader().getName()):null)
                .type(te.getType().getCode())
                .version(te.getVersion())
                .groups(List.of(new GroupIndoDto("1","группа-1"),new GroupIndoDto("2","группа-2")))
                .build();
    }

    public static Page<TemplateDto> toDtoList(Page<TemplateEntity> all){
        if(all.getContent()==null)return null;
        List<TemplateDto> dtos = new ArrayList<>();
        for (TemplateEntity te:all.getContent()) {
            dtos.add(TemplateDto.toDtoShort(te));
        }
        return new PageImpl<>(dtos, PageRequest.of(all.getNumber(),all.getSize()),all.getTotalElements());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TemplateFileDataDto{
        @NotBlank
        @Schema(name ="fileId", description = "Идентификатор файла", defaultValue = "4654654646")
        private Long fileId;
        @NotBlank
        @Schema(name ="fileName", description = "Название файла")
        private String fileName;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GroupIndoDto{
        private String groupId;
        private String name;
    }
}


