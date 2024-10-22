package dev.wilsondaniels.officeflow.application.departamento.delete;

import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoGateway;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoID;

import java.util.Objects;

public class DefaultDeleteDepartamentoUseCase extends DeleteDepartamentoUseCase {

    private final DepartamentoGateway departamentoGateway;

    public DefaultDeleteDepartamentoUseCase(final DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = Objects.requireNonNull(departamentoGateway);
    }

    @Override
    public void execute(String anId) {
        this.departamentoGateway.deleteById(DepartamentoID.from(anId));
    }
}
