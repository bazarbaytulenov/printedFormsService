package kz.project.printedFormsService.data.entity.dict;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "d_template_type", schema = "template_schema")
@Data
public class DTemplateType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
}
