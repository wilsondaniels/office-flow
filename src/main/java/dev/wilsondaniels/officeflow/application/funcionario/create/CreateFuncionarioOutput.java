package dev.wilsondaniels.officeflow.application.funcionario.create;

import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;

public record CreateFuncionarioOutput(
        String id
) {

    public static CreateFuncionarioOutput from(final String id) {
        return new CreateFuncionarioOutput(id);
    }

    public static CreateFuncionarioOutput from(final Funcionario funcionario) {
        return new CreateFuncionarioOutput(funcionario.getId().getValue());
    }
}
