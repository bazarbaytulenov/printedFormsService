package kz.project.printedFormsService.data.repository;

import kz.project.printedFormsService.data.entity.dict.DTemplateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DTemplateTypeRepository extends JpaRepository<DTemplateType, Long> {
    Optional<DTemplateType>findByCode(String code);
}
