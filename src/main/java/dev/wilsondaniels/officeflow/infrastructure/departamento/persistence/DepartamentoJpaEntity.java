package dev.wilsondaniels.officeflow.infrastructure.departamento.persistence;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoID;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Departamento")
public class DepartamentoJpaEntity {

    @Id
    @Column(name = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String departamento;

    @Column(name = "qtdefuncionarios", nullable = false)
    private int qtdeFuncionarios;

    public DepartamentoJpaEntity() { }

    private DepartamentoJpaEntity(UUID id, String departamento, int qtdeFuncionarios) {
        this.id = id;
        this.departamento = departamento;
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    public static DepartamentoJpaEntity from(final Departamento departamento) {
        return new DepartamentoJpaEntity(
                UUID.fromString(departamento.getId().getValue()),
                departamento.getDepartamento(),
                departamento.getQtdeFuncionarios()
        );
    }

    public Departamento toAggregate() {
        return Departamento.with(
                DepartamentoID.from(id),
                departamento,
                qtdeFuncionarios
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartamentoJpaEntity that = (DepartamentoJpaEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}