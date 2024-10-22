package dev.wilsondaniels.officeflow.application.departamento.create;

public record CreateDepartamentoCommand(
        String departamento,
        int qtdeFuncionarios

) {

    public static CreateDepartamentoCommand with(
            String departamento,
            int qtdeFuncionarios
    ) {
        return new CreateDepartamentoCommand(departamento, qtdeFuncionarios);
    }
}
