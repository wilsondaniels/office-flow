package dev.wilsondaniels.officeflow.domain.funcionariodepartamento;

import dev.wilsondaniels.officeflow.domain.AggregateRoot;
import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.validation.ValidationHandler;

public class FuncionarioDepartamento extends AggregateRoot<FuncionarioDepartamentoID> implements Cloneable {

    private Funcionario funcionario;
    private Departamento departamento;

    private FuncionarioDepartamento(
            final FuncionarioDepartamentoID id,
            final Funcionario funcionario,
            final Departamento departamento
    ) {
        super(id);
        this.funcionario = funcionario;
        this.departamento = departamento;
    }

    public static FuncionarioDepartamento newFuncionarioDepartamento(
            final Funcionario funcionario,
            final Departamento departamento) {
        final var id = FuncionarioDepartamentoID.unique();
        return new FuncionarioDepartamento(id, funcionario, departamento);
    }

    public static FuncionarioDepartamento with(
            final FuncionarioDepartamentoID id,
            final Funcionario funcionario,
            final Departamento departamento
    ) {
        return new FuncionarioDepartamento(id, funcionario, departamento);
    }

    public static FuncionarioDepartamento with(final FuncionarioDepartamento funcionarioDepartamento) {
        return with(funcionarioDepartamento.getId(), funcionarioDepartamento.funcionario,
                funcionarioDepartamento.departamento);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new FuncionarioDepartamentoValidator(this, handler).validate();
    }

    public FuncionarioDepartamento update(
            final Funcionario funcionario,
            final Departamento departamento
    ) {
        this.funcionario = funcionario;
        this.departamento = departamento;
        return this;
    }

    public FuncionarioDepartamentoID getId() {
        return id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    @Override
    public FuncionarioDepartamento clone() {
        try {
            return (FuncionarioDepartamento) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}