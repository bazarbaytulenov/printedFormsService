/*
package kz.project.printedFormsService.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "template", schema = "template_header")
@Data
public class TemplateHeaderEndity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String type;
    private Boolean isActive;
    private String path;
    private byte[] header;

    @ManyToMany
    @JoinTable(name = "header_body", joinColumns = @JoinColumn(name = "body_id"),
            inverseJoinColumns = @JoinColumn(name = "header_id"))
    Set<TemplateEntity> body;
}
*/
