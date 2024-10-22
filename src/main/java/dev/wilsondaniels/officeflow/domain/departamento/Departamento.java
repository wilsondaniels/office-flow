package dev.wilsondaniels.officeflow.domain.departamento;

import dev.wilsondaniels.officeflow.domain.AggregateRoot;
import dev.wilsondaniels.officeflow.domain.validation.ValidationHandler;

public class Departamento extends AggregateRoot<DepartamentoID> implements Cloneable {

    private String departamento;
    private Integer qtdeFuncionarios;

    private Departamento(
            final DepartamentoID id,
            final String departamento,
            final int qtdeFuncionarios
    ) {
        super(id);
        this.departamento = departamento;
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    public static Departamento newDepartamento(final String departamento, final int qtdeFuncionarios) {
        final var id = DepartamentoID.unique();
        return new Departamento(id, departamento, qtdeFuncionarios);
    }

    public static Departamento with(
            final DepartamentoID id,
            final String departamento,
            final int qtdeFuncionarios
    ) {
        return new Departamento(
                id,
                departamento,
                qtdeFuncionarios
        );
    }

    public static Departamento with(final Departamento departamento) {
        return with(
                departamento.getId(),
                departamento.departamento,
                departamento.qtdeFuncionarios
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new DepartamentoValidator(this, handler).validate();
    }

    public Departamento update(
            final String departamento,
            final int qtdeFuncionarios
    ) {
        this.departamento = departamento;
        this.qtdeFuncionarios = qtdeFuncionarios;
        return this;
    }

    public DepartamentoID getId() {
        return id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public Integer getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    @Override
    public Departamento clone() {
        try {
            return (Departamento) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}