package dev.wilsondaniels.officeflow.application.departamento.retrieve.list;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoID;

public record DepartamentoOutput(
        DepartamentoID id,
        String departamento,
        int qtdeFuncionarios) {

    public static DepartamentoOutput from(Departamento departamento) {
        return new DepartamentoOutput(
                departamento.getId(),
                departamento.getDepartamento(),
                departamento.getQtdeFuncionarios());
    }
}
