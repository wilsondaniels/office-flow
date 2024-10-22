package dev.wilsondaniels.officeflow.infrastructure.departamento.dto;

import dev.wilsondaniels.officeflow.application.departamento.retrieve.list.DepartamentoOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record DepartamentoDTO(
        @JsonProperty("id") String id,
        @JsonProperty("departamento") String departamento,
        @JsonProperty("qtde-funcionarios") int qtdeFuncionarios
) {

    public static DepartamentoDTO from(final DepartamentoOutput departamento) {
        return new DepartamentoDTO(
                departamento.id().getValue(),
                departamento.departamento(),
                departamento.qtdeFuncionarios()
        );
    }
}