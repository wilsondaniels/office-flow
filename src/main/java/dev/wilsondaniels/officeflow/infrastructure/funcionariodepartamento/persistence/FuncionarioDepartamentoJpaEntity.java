package dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.persistence;

import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamento;
import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoID;
import dev.wilsondaniels.officeflow.infrastructure.departamento.persistence.DepartamentoJpaEntity;
import dev.wilsondaniels.officeflow.infrastructure.funcionario.persistence.FuncionarioJpaEntity;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "funcionario_departamento")
public class FuncionarioDepartamentoJpaEntity {

    @Id
    @Column(name = "uuid")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "uuid_funcionario", nullable = false)
    private FuncionarioJpaEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "uuid_depto", nullable = false)
    private DepartamentoJpaEntity departamento;

    public FuncionarioDepartamentoJpaEntity() { }

    private FuncionarioDepartamentoJpaEntity(UUID id, FuncionarioJpaEntity funcionario, DepartamentoJpaEntity departamento) {
        this.id = id;
        this.funcionario = funcionario;
        this.departamento = departamento;
    }

    public static FuncionarioDepartamentoJpaEntity from(final FuncionarioDepartamento funcionarioDepartamento) {
        return new FuncionarioDepartamentoJpaEntity(
                UUID.fromString(funcionarioDepartamento.getId().getValue()),
                FuncionarioJpaEntity.from(funcionarioDepartamento.getFuncionario()),
                DepartamentoJpaEntity.from(funcionarioDepartamento.getDepartamento())
        );
    }

    public FuncionarioDepartamento toAggregate() {
        return FuncionarioDepartamento.with(
                FuncionarioDepartamentoID.from(id),
                funcionario.toAggregate(),
                departamento.toAggregate()
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public FuncionarioJpaEntity getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioJpaEntity funcionario) {
        this.funcionario = funcionario;
    }

    public DepartamentoJpaEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoJpaEntity departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuncionarioDepartamentoJpaEntity that = (FuncionarioDepartamentoJpaEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}