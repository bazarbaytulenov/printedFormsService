package kz.project.printedFormsService.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "template")
@Data
public class TemplateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nameBody;
    private String nameHeader;
    private  String type;
    private Boolean isActive;
    private String path;
    private byte[] data;
    private byte[] header;

}
