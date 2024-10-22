package dev.wilsondaniels.officeflow.application.departamento.create;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;

public record CreateDepartamentoOutput(
        String id
) {

    public static CreateDepartamentoOutput from(final String id) {
        return new CreateDepartamentoOutput(id);
    }

    public static CreateDepartamentoOutput from(final Departamento departamento) {
        return new CreateDepartamentoOutput(departamento.getId().getValue());
    }
}
