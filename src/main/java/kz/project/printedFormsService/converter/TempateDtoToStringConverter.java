package kz.project.printedFormsService.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import kz.project.printedFormsService.data.dto.TemplateDto;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TempateDtoToStringConverter implements Converter<String, TemplateDto> {

        @Autowired
        private ObjectMapper objectMapper;

        @Override
        @SneakyThrows
        public TemplateDto convert(String source) {
            return objectMapper.readValue(source, TemplateDto.class);
        }

}
