package dev.wilsondaniels.officeflow.application.funcionario.update;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateFuncionarioCommand(
        String id,
        String nome,
        String endereco,
        String bairro,
        String cep,
        String telefone,
        BigDecimal salario,
        LocalDate dataContrato,
        String funcao
) {

    public static UpdateFuncionarioCommand with(
            String id,
            String nome,
            String endereco,
            String bairro,
            String cep,
            String telefone,
            BigDecimal salario,
            LocalDate dataContrato,
            String funcao
    ) {
        return new UpdateFuncionarioCommand(id, nome, endereco, bairro, cep, telefone, salario, dataContrato, funcao);
    }
}
