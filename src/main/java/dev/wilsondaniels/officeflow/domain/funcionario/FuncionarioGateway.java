package dev.wilsondaniels.officeflow.domain.funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioGateway {

    Funcionario create(Funcionario funcionario);

    void deleteById(FuncionarioID id);

    Optional<Funcionario> findById(FuncionarioID id);

    Funcionario update(Funcionario funcionario);

    List<Funcionario> findAll(String nome, String funcao, boolean ordenarPorSalario);

    long getTotalFuncionariosPorSalario(Double salario);
}
