package dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.dto;

import dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.total.TotalFuncionariosPorDepartamentoOutput;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TotalFuncionariosPorDepartamentoDTO(
        @JsonProperty("departamento") String departamento,
        @JsonProperty("total-funcionarios") Long totalFuncionarios
) {

    public static TotalFuncionariosPorDepartamentoDTO from(final TotalFuncionariosPorDepartamentoOutput out) {
        return new TotalFuncionariosPorDepartamentoDTO(
                out.departamento(),
                out.totalFuncionarios()
        );
    }
}