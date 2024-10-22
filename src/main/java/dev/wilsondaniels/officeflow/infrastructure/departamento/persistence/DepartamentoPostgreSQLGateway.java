package dev.wilsondaniels.officeflow.infrastructure.departamento.persistence;


import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoGateway;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoID;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartamentoPostgreSQLGateway implements DepartamentoGateway {

    private final DepartamentoRepository repository;

    public DepartamentoPostgreSQLGateway(final DepartamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Departamento create(Departamento departamento) {
        return save(departamento);
    }

    private Departamento save(Departamento departamento) {
        final DepartamentoJpaEntity entity = DepartamentoJpaEntity.from(departamento);
        return this.repository.save(entity).toAggregate();
    }

    @Override
    public void deleteById(DepartamentoID id) {
        final var uuid = UUID.fromString(id.getValue());
        if (this.repository.existsById(uuid)) {
            this.repository.deleteById(uuid);
        }
    }

    @Override
    public Optional<Departamento> findById(DepartamentoID id) {
        return this.repository.findById(UUID.fromString(id.getValue()))
                .map(DepartamentoJpaEntity::toAggregate);
    }

    @Override
    public Departamento update(Departamento departamento) {
        return save(departamento);
    }

    @Override
    public List<Departamento> findAll(String query) {
        return repository.findAll(filterByDepartamento(query))
                .stream().map(DepartamentoJpaEntity::toAggregate).toList();
    }

    private static Specification<DepartamentoJpaEntity> filterByDepartamento(String departamento) {
        return (root, query, cb) -> {
            if (departamento == null || departamento.isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("departamento")), "%" + departamento.toLowerCase() + "%");
        };
    }
}