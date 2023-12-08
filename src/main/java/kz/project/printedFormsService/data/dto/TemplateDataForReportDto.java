package kz.project.printedFormsService.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDataForReportDto {
    private String type;
    private byte[] body;
    private byte[] header;

}
