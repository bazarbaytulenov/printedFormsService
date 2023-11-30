package kz.project.printedFormsService.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "MessageDto", description = "Ответ")
public class ResponseDto {
    @NotBlank
    @Schema(name = "Сообшение о успешной формировании отчета")
    private String succesMessage;
    @NotBlank
    @Schema(name = "Сообщение об ошибке")
    private String errorMessage;
    @NotBlank
    @Schema(name = "Отчет")
    private byte[] pdf;


}