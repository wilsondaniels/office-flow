package dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.list;

import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoGateway;

import java.util.List;
import java.util.Objects;

public class DefaultListFuncionarioDepartamentoUseCase extends ListFuncionarioDepartamentoUseCase {

    private final FuncionarioDepartamentoGateway funcionarioDepartamentoGateway;

    public DefaultListFuncionarioDepartamentoUseCase(final FuncionarioDepartamentoGateway funcionarioDepartamentoGateway) {
        this.funcionarioDepartamentoGateway = Objects.requireNonNull(funcionarioDepartamentoGateway);
    }

    @Override
    public List<FuncionarioDepartamentoOutput> execute(ListFuncionarioDepartamentoCommand command) {
        return this.funcionarioDepartamentoGateway.findAll(command.nome(),
                command.departamento()).stream().map(FuncionarioDepartamentoOutput::from).toList();
    }
}
