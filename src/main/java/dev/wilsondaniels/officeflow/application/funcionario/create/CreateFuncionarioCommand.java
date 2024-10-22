package dev.wilsondaniels.officeflow.application.funcionario.create;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateFuncionarioCommand(
        String nome,
        String endereco,
        String bairro,
        String cep,
        String telefone,
        BigDecimal salario,
        LocalDate dataContrato,
        String funcao
) {

    public static CreateFuncionarioCommand with(
            String nome,
            String endereco,
            String bairro,
            String cep,
            String telefone,
            BigDecimal salario,
            LocalDate dataContrato,
            String funcao
    ) {
        return new CreateFuncionarioCommand(nome, endereco, bairro, cep, telefone, salario, dataContrato, funcao);
    }
}
