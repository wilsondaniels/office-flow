package dev.wilsondaniels.officeflow.domain.departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoGateway {

    Departamento create(Departamento departamento);

    void deleteById(DepartamentoID id);

    Optional<Departamento> findById(DepartamentoID id);

    Departamento update(Departamento departamento);

    List<Departamento> findAll(String query);
}
