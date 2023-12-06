package kz.project.printedFormsService.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "file_info", schema = "template_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateFileInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean isHeader;
    private String path;
    private Integer hash;
    private byte[] data;
}
