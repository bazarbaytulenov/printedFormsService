package kz.project.printedFormsService.data.repository;

import kz.project.printedFormsService.data.dto.TemplateDto;
import kz.project.printedFormsService.data.entity.TemplateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {

    Optional<TemplateEntity> findByCode(String code);
    Page<TemplateEntity> findAllByIsActiveTrue(Pageable pageable);
    Page<TemplateEntity> findAllByIsActiveFalse(Pageable pageable);

    Optional<TemplateEntity> findFirstByCodeOrderByVersionDesc(String code);

    Page<TemplateEntity> findAllByCode(Pageable pageable, String code);
}
