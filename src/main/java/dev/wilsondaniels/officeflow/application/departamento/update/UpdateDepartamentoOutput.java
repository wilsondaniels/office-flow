package dev.wilsondaniels.officeflow.application.departamento.update;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;

public record UpdateDepartamentoOutput(
        String id
) {

    public static UpdateDepartamentoOutput from(final String id) {
        return new UpdateDepartamentoOutput(id);
    }

    public static UpdateDepartamentoOutput from(final Departamento departamento) {
        return new UpdateDepartamentoOutput(departamento.getId().getValue());
    }
}
