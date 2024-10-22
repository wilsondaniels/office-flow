package dev.wilsondaniels.officeflow.infrastructure.funcionario.persistence;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<FuncionarioJpaEntity, UUID> {
    List<FuncionarioJpaEntity> findAll(Specification<FuncionarioJpaEntity> where);
    long count(Specification<FuncionarioJpaEntity> where);
}
