package kz.project.printedFormsService.data.dto;

import kz.project.printedFormsService.data.entity.dict.DTemplateType;
import kz.project.printedFormsService.service.DTemplateTypeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTemplateTypeDto {
    private String code;
    private String name;

    public static DTemplateTypeDto toDto(DTemplateType et){
       return new DTemplateTypeDto(et.getCode(), et.getName());
    }

    public static List<DTemplateTypeDto>toDtoList(List<DTemplateType> ets){
         List<DTemplateTypeDto> dtos = new ArrayList<>();
         ets.stream().forEach(i->dtos.add(new DTemplateTypeDto(i.getCode(), i.getName())));
        return dtos;
    }

}
