package dev.wilsondaniels.officeflow.application.funcionariodepartamento.create;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;

public record CreateFuncionarioDepartamentoCommand(
        Departamento departamento,
        Funcionario funcionario

) {

    public static CreateFuncionarioDepartamentoCommand with(
            Departamento departamento,
            Funcionario funcionario
    ) {
        return new CreateFuncionarioDepartamentoCommand(departamento, funcionario);
    }
}
