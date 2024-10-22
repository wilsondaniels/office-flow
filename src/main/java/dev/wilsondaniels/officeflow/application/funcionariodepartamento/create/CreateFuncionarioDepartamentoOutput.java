package dev.wilsondaniels.officeflow.application.funcionariodepartamento.create;

import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamento;

public record CreateFuncionarioDepartamentoOutput(
        String id
) {

    public static CreateFuncionarioDepartamentoOutput from(final String id) {
        return new CreateFuncionarioDepartamentoOutput(id);
    }

    public static CreateFuncionarioDepartamentoOutput from(final FuncionarioDepartamento funcionarioDepartamento) {
        return new CreateFuncionarioDepartamentoOutput(funcionarioDepartamento.getId().getValue());
    }
}
