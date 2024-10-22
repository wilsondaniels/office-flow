package dev.wilsondaniels.officeflow.infrastructure.departamento.persistence;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DepartamentoRepository extends JpaRepository<DepartamentoJpaEntity, UUID> {

    List<DepartamentoJpaEntity>findAll(Specification<DepartamentoJpaEntity> where);
}
