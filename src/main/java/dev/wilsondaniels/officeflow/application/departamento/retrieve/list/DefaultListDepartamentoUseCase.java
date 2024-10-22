package dev.wilsondaniels.officeflow.application.departamento.retrieve.list;

import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoGateway;

import java.util.List;
import java.util.Objects;

public class DefaultListDepartamentoUseCase extends ListDepartamentoUseCase {

    private final DepartamentoGateway departamentoGateway;

    public DefaultListDepartamentoUseCase(final DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = Objects.requireNonNull(departamentoGateway);
    }

    @Override
    public List<DepartamentoOutput> execute(String query) {
        return this.departamentoGateway.findAll(query).stream().map(DepartamentoOutput::from).toList();
    }
}
