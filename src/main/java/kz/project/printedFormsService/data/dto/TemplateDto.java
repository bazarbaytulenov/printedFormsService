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
    @Schema(name ="code", description = "Код шаблона")
    private String code;
    @NotBlank
    @Schema(name = "dataName", description = "Наименование шаблона")
    private String dataName;
    @NotBlank
    @Schema(name ="headerName", description = "Наименование заголовки")
    private String headerName;
    @NotBlank
    @Schema(name = "type", description = "Тип фаила")
    private String type;
    @NotBlank
    @Schema(name ="isActive", description = "Флаг активности")
    private Boolean isActive;
    @NotBlank
    @Schema(name ="version", description = "Версия активности")
    private Integer version;
    /*@NotBlank
    @Schema(name ="data", description = "Шаблон")
    @JsonRawValue
    private String data;*/
    /*@NotBlank
    @Schema(name ="header", description = "Шаблон заголовка")
    @JsonRawValue
    private String header;*/


    public static TemplateDto toDtoShort(TemplateEntity te) {
       return TemplateDto.builder()
                .code(te.getCode())
                .isActive(te.getIsActive())
                .dataName(te.getNameBody())
                .type(te.getType().getCode())
               .version(te.getVersion())
                .build();
    }

    public static TemplateDto toDto(TemplateEntity te) {
        return TemplateDto.builder()
                .code(te.getCode())
                .isActive(te.getIsActive())
                .dataName(te.getNameBody())
                .type(te.getType().getCode())
                //.data(new String(te.getData()))
                //.header(new String(te.getHeader()))
                .headerName(te.getNameHeader())
                .version(te.getVersion())
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
}
