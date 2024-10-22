package dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.dto;

import dev.wilsondaniels.officeflow.infrastructure.departamento.dto.DepartamentoDTO;
import dev.wilsondaniels.officeflow.infrastructure.funcionario.dto.FuncionarioDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public record FuncionarioDepartamentoDTO(
        @JsonProperty("id") String id,
        @JsonProperty("funcionario") FuncionarioDTO funcionario,
        @JsonProperty("departamento") DepartamentoDTO departamento
) {
}