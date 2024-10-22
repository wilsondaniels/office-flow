package dev.wilsondaniels.officeflow.application.funcionario.update;

import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;

public record UpdateFuncionarioOutput(
        String id
) {

    public static UpdateFuncionarioOutput from(final String id) {
        return new UpdateFuncionarioOutput(id);
    }

    public static UpdateFuncionarioOutput from(final Funcionario funcionario) {
        return new UpdateFuncionarioOutput(funcionario.getId().getValue());
    }
}
