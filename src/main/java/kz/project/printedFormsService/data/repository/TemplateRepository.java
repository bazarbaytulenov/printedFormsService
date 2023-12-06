package kz.project.printedFormsService.data.repository;

import kz.project.printedFormsService.data.entity.TemplateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {

    Optional<TemplateEntity> findByCode(String code);
    Page<TemplateEntity> findAllByStatusTrue(Pageable pageable);
    Page<TemplateEntity> findAllByStatusFalse(Pageable pageable);

    Optional<TemplateEntity> findFirstByCodeOrderByVersionDesc(String code);

    Page<TemplateEntity> findAllByCode(Pageable pageable, String code);
}
