package dev.wilsondaniels.officeflow.infrastructure.funcionario.persistence;

import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioGateway;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioID;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuncionarioPostgreSQLGateway implements FuncionarioGateway {

    private final FuncionarioRepository repository;

    public FuncionarioPostgreSQLGateway(final FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Funcionario create(Funcionario funcionario) {
        return save(funcionario);
    }

    private Funcionario save(Funcionario funcionario) {
        final FuncionarioJpaEntity entity = FuncionarioJpaEntity.from(funcionario);
        return this.repository.save(entity).toAggregate();
    }

    @Override
    public void deleteById(FuncionarioID id) {
        final var uuid = UUID.fromString(id.getValue());
        if (this.repository.existsById(uuid)) {
            this.repository.deleteById(uuid);
        }
    }

    @Override
    public Optional<Funcionario> findById(FuncionarioID id) {
        return this.repository.findById(UUID.fromString(id.getValue()))
                .map(FuncionarioJpaEntity::toAggregate);
    }

    @Override
    public Funcionario update(Funcionario funcionario) {
        return save(funcionario);
    }

    @Override
    public List<Funcionario> findAll(String nome, String funcao, boolean ordenarPorSalario) {

        final Specification<FuncionarioJpaEntity> spec = Specification
                .where(filterByNome(nome))
                .and(filterByFuncao(funcao));

        List<Funcionario> listaFuncionarios = repository.findAll(spec)
                .stream().map(FuncionarioJpaEntity::toAggregate).toList();

        if (ordenarPorSalario) {
            listaFuncionarios = listaFuncionarios.stream()
                    .sorted(
                    (s1, s2) -> s1.getSalario().compareTo(s2.getSalario()))
                    .collect(Collectors.toList());
        }
        return listaFuncionarios;
    }

    @Override
    public long getTotalFuncionariosPorSalario(Double salario) {
        return repository.count(salarioMaiorQue(salario));
    }

    private static Specification<FuncionarioJpaEntity> filterByNome(String nome) {
        return (root, query, cb) -> {
            if (nome == null || nome.isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    private static Specification<FuncionarioJpaEntity> filterByFuncao(String funcao) {
        return (root, query, cb) -> {
            if (funcao == null || funcao.isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("funcao")), "%" + funcao.toLowerCase() + "%");
        };
    }

    private static Specification<FuncionarioJpaEntity> salarioMaiorQue(double salarioMinimo) {
        return (root, query, cb) -> cb.greaterThan(root.get("salario"), salarioMinimo);
    }
}