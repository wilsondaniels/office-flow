package dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.dto;

import dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.list.FuncionarioDepartamentoOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record FuncionarioDepartamentoSimplesDTO(
        @JsonProperty("id") String id,
        @JsonProperty("funcionario") String funcionario,
        @JsonProperty("departamento") String departamento
) {

    public static FuncionarioDepartamentoSimplesDTO from(final FuncionarioDepartamentoOutput funcionarioDepartamento) {
        return new FuncionarioDepartamentoSimplesDTO(
                funcionarioDepartamento.id().getValue(),
                funcionarioDepartamento.funcionario(),
                funcionarioDepartamento.departamento()
        );
    }
}