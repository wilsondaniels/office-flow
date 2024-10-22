package dev.wilsondaniels.officeflow.application.funcionario.retrieve.get;

import dev.wilsondaniels.officeflow.domain.exception.NotFoundException;
import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioGateway;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioID;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetFuncionarioByIdUseCase extends GetFuncionarioByIdUseCase {

    private final FuncionarioGateway funcionarioGateway;

    public DefaultGetFuncionarioByIdUseCase(final FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = Objects.requireNonNull(funcionarioGateway);
    }

    @Override
    public FuncionarioOutput execute(final String anIn) {
        final var funcionarioID = FuncionarioID.from(anIn);

        return this.funcionarioGateway.findById(funcionarioID)
                .map(FuncionarioOutput::from)
                .orElseThrow(notFound(funcionarioID));
    }

    private Supplier<NotFoundException> notFound(final FuncionarioID id) {
        return () -> NotFoundException.with(Funcionario.class, id);
    }
}