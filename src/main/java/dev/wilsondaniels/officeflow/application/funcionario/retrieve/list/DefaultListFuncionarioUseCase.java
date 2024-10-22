package dev.wilsondaniels.officeflow.application.funcionario.retrieve.list;

import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioGateway;

import java.util.List;
import java.util.Objects;

public class DefaultListFuncionarioUseCase extends ListFuncionarioUseCase {

    private final FuncionarioGateway funcionarioGateway;

    public DefaultListFuncionarioUseCase(final FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = Objects.requireNonNull(funcionarioGateway);
    }

    @Override
    public List<FuncionarioOutput> execute(ListFuncionarioCommand command) {
        return this.funcionarioGateway.findAll(command.nome(),
                command.funcao(), command.ordenarPorSalario())
                .stream().map(FuncionarioOutput::from).toList();
    }
}
