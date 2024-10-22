package dev.wilsondaniels.officeflow.application.departamento.update;

public record UpdateDepartamentoCommand(
        String id,
        String departamento,
        int qtdeFuncionarios

) {

    public static UpdateDepartamentoCommand with(
            String id,
            String departamento,
            int qtdeFuncionarios
    ) {
        return new UpdateDepartamentoCommand(id, departamento, qtdeFuncionarios);
    }
}
