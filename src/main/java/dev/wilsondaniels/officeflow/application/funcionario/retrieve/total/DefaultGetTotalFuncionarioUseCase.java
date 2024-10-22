package dev.wilsondaniels.officeflow.application.funcionario.retrieve.total;

import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioGateway;

import java.util.Objects;

public class DefaultGetTotalFuncionarioUseCase extends GetTotalFuncionarioUseCase {

    private final FuncionarioGateway funcionarioGateway;

    public DefaultGetTotalFuncionarioUseCase(final FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = Objects.requireNonNull(funcionarioGateway);
    }

    @Override
    public Long execute(final Double salarioMinimo) {
        return funcionarioGateway.getTotalFuncionariosPorSalario(salarioMinimo);
    }
}