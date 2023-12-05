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
    private String nameBody;
    private String nameHeader;
    private Boolean isActive;
    private String path;
    private byte[] data;
    private byte[] header;
    private Integer version;
    private String hsshData;
    private String hashHeader;
    @ManyToOne
    private DTemplateType type;


}
