package dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.persistence;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FuncionarioDepartamentoRepository extends JpaRepository<FuncionarioDepartamentoJpaEntity, UUID> {
    List<FuncionarioDepartamentoJpaEntity> findAll(Specification<FuncionarioDepartamentoJpaEntity> where);

    @Query("SELECT f.departamento.departamento, COUNT(f) FROM FuncionarioDepartamentoJpaEntity f GROUP BY f.departamento.departamento")
    List<Object[]> contarFuncionariosAgrupadosPorDepartamento();
}
