package dev.wilsondaniels.officeflow.application.funcionario.retrieve.list;

import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioID;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FuncionarioOutput(
        FuncionarioID id,
        String nome,
        String endereco,
        String bairro,
        String cep,
        String telefone,
        BigDecimal salario,
        LocalDate dataContrato,
        String funcao) {

    public static FuncionarioOutput from(Funcionario funcionario) {
        return new FuncionarioOutput(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getEndereco(),
                funcionario.getBairro(),
                funcionario.getCep(),
                funcionario.getTelefone(),
                funcionario.getSalario(),
                funcionario.getDataContrato(),
                funcionario.getFuncao());
    }
}
