package dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.list;

import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamento;
import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoID;

public record FuncionarioDepartamentoOutput(
        FuncionarioDepartamentoID id,
        String funcionario,
        String departamento) {

    public static FuncionarioDepartamentoOutput from(FuncionarioDepartamento funcionarioDepartamento) {
        return new FuncionarioDepartamentoOutput(
                funcionarioDepartamento.getId(),
                funcionarioDepartamento.getFuncionario().getNome(),
                funcionarioDepartamento.getDepartamento().getDepartamento());
    }
}
