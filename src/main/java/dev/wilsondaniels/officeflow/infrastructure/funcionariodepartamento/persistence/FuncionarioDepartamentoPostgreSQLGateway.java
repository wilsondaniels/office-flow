package dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.persistence;

import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamento;
import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoGateway;
import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoID;
import dev.wilsondaniels.officeflow.infrastructure.departamento.persistence.DepartamentoJpaEntity;
import dev.wilsondaniels.officeflow.infrastructure.funcionario.persistence.FuncionarioJpaEntity;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FuncionarioDepartamentoPostgreSQLGateway implements FuncionarioDepartamentoGateway {

    private final FuncionarioDepartamentoRepository repository;

    public FuncionarioDepartamentoPostgreSQLGateway(final FuncionarioDepartamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public FuncionarioDepartamento create(FuncionarioDepartamento funcionarioDepartamento) {
        return save(funcionarioDepartamento);
    }

    private FuncionarioDepartamento save(FuncionarioDepartamento funcionarioDepartamento) {
        final FuncionarioDepartamentoJpaEntity entity = FuncionarioDepartamentoJpaEntity.from(funcionarioDepartamento);
        return this.repository.save(entity).toAggregate();
    }

    @Override
    public void deleteById(FuncionarioDepartamentoID id) {
        final var uuid = UUID.fromString(id.getValue());
        if (this.repository.existsById(uuid)) {
            this.repository.deleteById(uuid);
        }
    }

    @Override
    public List<FuncionarioDepartamento> findAll(String nome, String departamento) {

        final Specification<FuncionarioDepartamentoJpaEntity> spec = Specification
                .where(filterByNomeFuncionario(nome))
                .and(filterByNomeDepartamento(departamento));

        return repository.findAll(spec)
                .stream().map(FuncionarioDepartamentoJpaEntity::toAggregate).toList();
    }

    @Override
    public Map<String, Long> countFuncionariosByDepartamento() {
        final Map<String, Long> sortedMap = new TreeMap<>();
        final List<Object[]> objects = repository.contarFuncionariosAgrupadosPorDepartamento();
        for (final Object[] obj : objects) {
            sortedMap.put((String) obj[0], (Long) obj[1]);
        }
        return sortedMap;
    }

    private static Specification<FuncionarioDepartamentoJpaEntity> filterByNomeFuncionario(String nome) {
        return (root, query, cb) -> {
            if (nome == null || nome.isEmpty()) {
                return cb.conjunction();
            }
            final Join<FuncionarioDepartamento, FuncionarioJpaEntity> join = root.join("funcionario");
            return cb.like(cb.lower(join.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    private static Specification<FuncionarioDepartamentoJpaEntity> filterByNomeDepartamento(String departamento) {
        return (root, query, cb) -> {
            if (departamento == null || departamento.isEmpty()) {
                return cb.conjunction();
            }
            final Join<FuncionarioDepartamento, DepartamentoJpaEntity> join = root.join("departamento");
            return cb.like(cb.lower(join.get("departamento")), "%" + departamento.toLowerCase() + "%");
        };
    }
}