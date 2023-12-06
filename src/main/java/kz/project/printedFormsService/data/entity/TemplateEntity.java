package kz.project.printedFormsService.data.entity;

import jakarta.persistence.*;
import kz.project.printedFormsService.data.entity.dict.DTemplateType;
import lombok.Data;

@Entity
@Table(name = "template", schema = "template_schema")
@Data
public class TemplateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Boolean status;
    private Integer version;
    @ManyToOne
    private DTemplateType type;
    @OneToOne
    private  TemplateFileInfoEntity template;
    @OneToOne
    private TemplateFileInfoEntity templeateHeader;


}
