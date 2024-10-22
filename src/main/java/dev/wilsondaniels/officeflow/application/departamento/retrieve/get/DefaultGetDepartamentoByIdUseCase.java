package dev.wilsondaniels.officeflow.application.departamento.retrieve.get;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoGateway;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoID;
import dev.wilsondaniels.officeflow.domain.exception.NotFoundException;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetDepartamentoByIdUseCase extends GetDepartamentoByIdUseCase {

    private final DepartamentoGateway departamentoGateway;

    public DefaultGetDepartamentoByIdUseCase(final DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = Objects.requireNonNull(departamentoGateway);
    }

    @Override
    public DepartamentoOutput execute(final String anIn) {
        final var departamentoID = DepartamentoID.from(anIn);

        return this.departamentoGateway.findById(departamentoID)
                .map(DepartamentoOutput::from)
                .orElseThrow(notFound(departamentoID));
    }

    private Supplier<NotFoundException> notFound(final DepartamentoID id) {
        return () -> NotFoundException.with(Departamento.class, id);
    }
}