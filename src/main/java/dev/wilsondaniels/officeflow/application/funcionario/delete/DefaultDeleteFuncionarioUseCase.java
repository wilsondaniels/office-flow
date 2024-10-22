package dev.wilsondaniels.officeflow.application.funcionario.delete;

import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioGateway;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioID;

import java.util.Objects;

public class DefaultDeleteFuncionarioUseCase extends DeleteFuncionarioUseCase {

    private final FuncionarioGateway funcionarioGateway;

    public DefaultDeleteFuncionarioUseCase(final FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = Objects.requireNonNull(funcionarioGateway);
    }

    @Override
    public void execute(String anId) {
        this.funcionarioGateway.deleteById(FuncionarioID.from(anId));
    }
}
