package dev.wilsondaniels.officeflow.domain.funcionariodepartamento;

import java.util.List;
import java.util.Map;

public interface FuncionarioDepartamentoGateway {

    FuncionarioDepartamento create(FuncionarioDepartamento funcionarioDepartamento);

    void deleteById(FuncionarioDepartamentoID id);

    List<FuncionarioDepartamento> findAll(String nome, String departamento);

    Map<String, Long> countFuncionariosByDepartamento();
}
