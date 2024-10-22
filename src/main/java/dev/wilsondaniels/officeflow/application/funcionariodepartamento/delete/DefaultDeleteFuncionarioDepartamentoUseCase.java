package dev.wilsondaniels.officeflow.application.funcionariodepartamento.delete;

import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoGateway;
import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoID;

import java.util.Objects;

public class DefaultDeleteFuncionarioDepartamentoUseCase extends DeleteFuncionarioDepartamentoUseCase {

    private final FuncionarioDepartamentoGateway funcionarioDepartamentoGateway;

    public DefaultDeleteFuncionarioDepartamentoUseCase(final FuncionarioDepartamentoGateway funcionarioDepartamentoGateway) {
        this.funcionarioDepartamentoGateway = Objects.requireNonNull(funcionarioDepartamentoGateway);
    }

    @Override
    public void execute(String anId) {
        this.funcionarioDepartamentoGateway.deleteById(FuncionarioDepartamentoID.from(anId));
    }
}
