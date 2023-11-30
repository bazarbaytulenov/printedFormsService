package kz.project.printedFormsService.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "group", schema = "template_schema")
@Datatemplate_schema
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isAll;
    private Boolean isCreate;
    private Boolean isUpdate;
    private Boolean isDelete;
    private Boolean isEdit;
}
