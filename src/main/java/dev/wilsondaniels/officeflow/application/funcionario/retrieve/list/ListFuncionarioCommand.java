package dev.wilsondaniels.officeflow.application.funcionario.retrieve.list;

public record ListFuncionarioCommand(
        String nome,
        String funcao,
        boolean ordenarPorSalario
) {
}
