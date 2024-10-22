package dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.total;

import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoGateway;

import java.util.*;

public class DefaultGetTotalFuncionarioPorDepartamentoUseCase extends GetTotalFuncionarioPorDepartamentoUseCase {

    private final FuncionarioDepartamentoGateway funcionarioDepartamentoGateway;

    public DefaultGetTotalFuncionarioPorDepartamentoUseCase(final FuncionarioDepartamentoGateway funcionarioDepartamentoGateway) {
        this.funcionarioDepartamentoGateway = Objects.requireNonNull(funcionarioDepartamentoGateway);
    }

    @Override
    public List<TotalFuncionariosPorDepartamentoOutput> execute() {
        final Map<String, Long> stringLongMap = funcionarioDepartamentoGateway.countFuncionariosByDepartamento();
        final List<TotalFuncionariosPorDepartamentoOutput> lista = new ArrayList<>();
        for (final String key : stringLongMap.keySet()) {
            lista.add(new TotalFuncionariosPorDepartamentoOutput(key, stringLongMap.get(key)));
        }
        return lista;
    }

}