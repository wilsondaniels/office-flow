package dev.wilsondaniels.officeflow.infrastructure.funcionario.dto;

import dev.wilsondaniels.officeflow.application.funcionario.retrieve.list.FuncionarioOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FuncionarioDTO(
        @JsonProperty("id") String id,
        @JsonProperty("nome") String nome,
        @JsonProperty("endereco") String endereco,
        @JsonProperty("bairro") String bairro,
        @JsonProperty("cep") String cep,
        @JsonProperty("telefone") String telefone,
        @JsonProperty("salario") BigDecimal salario,
        @JsonProperty("data-contrato") LocalDate dataContrato,
        @JsonProperty("funcao") String funcao
) {

    public static FuncionarioDTO from(final FuncionarioOutput funcionario) {
        return new FuncionarioDTO(
                funcionario.id().getValue(),
                funcionario.nome(),
                funcionario.endereco(),
                funcionario.bairro(),
                funcionario.cep(),
                funcionario.telefone(),
                funcionario.salario(),
                funcionario.dataContrato(),
                funcionario.funcao()
        );
    }
}