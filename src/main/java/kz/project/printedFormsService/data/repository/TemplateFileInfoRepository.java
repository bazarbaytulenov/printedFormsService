package kz.project.printedFormsService.data.repository;

import kz.project.printedFormsService.data.entity.TemplateFileInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateFileInfoRepository extends JpaRepository<TemplateFileInfoEntity, Long> {
}
