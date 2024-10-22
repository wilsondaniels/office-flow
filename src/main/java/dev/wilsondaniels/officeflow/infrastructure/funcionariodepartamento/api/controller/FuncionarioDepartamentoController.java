package dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.api.controller;

import dev.wilsondaniels.officeflow.application.funcionariodepartamento.create.CreateFuncionarioDepartamentoCommand;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.create.CreateFuncionarioDepartamentoOutput;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.create.CreateFuncionarioDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.delete.DeleteFuncionarioDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.list.ListFuncionarioDepartamentoCommand;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.list.ListFuncionarioDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.total.GetTotalFuncionarioPorDepartamentoUseCase;
import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoID;
import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioID;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.api.FuncionarioDepartamentoAPI;
import dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.dto.FuncionarioDepartamentoDTO;
import dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.dto.FuncionarioDepartamentoSimplesDTO;
import dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.dto.TotalFuncionariosPorDepartamentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static dev.wilsondaniels.officeflow.infrastructure.util.Util.isUUIDValid;

@RestController
public class FuncionarioDepartamentoController implements FuncionarioDepartamentoAPI {

    private final CreateFuncionarioDepartamentoUseCase createFuncionarioDepartamentoUseCase;
    private final DeleteFuncionarioDepartamentoUseCase deleteFuncionarioDepartamentoUseCase;
    private final ListFuncionarioDepartamentoUseCase listFuncionarioDepartamentoUseCase;
    private final GetTotalFuncionarioPorDepartamentoUseCase getTotalFuncionarioPorDepartamentoUseCase;

    public FuncionarioDepartamentoController(
            final CreateFuncionarioDepartamentoUseCase createFuncionarioDepartamentoUseCase,
            final DeleteFuncionarioDepartamentoUseCase deleteFuncionarioDepartamentoUseCase,
            final ListFuncionarioDepartamentoUseCase listFuncionarioDepartamentoUseCase,
            final GetTotalFuncionarioPorDepartamentoUseCase getTotalFuncionarioPorDepartamentoUseCase
    ) {
        this.createFuncionarioDepartamentoUseCase = Objects.requireNonNull(createFuncionarioDepartamentoUseCase);
        this.deleteFuncionarioDepartamentoUseCase = Objects.requireNonNull(deleteFuncionarioDepartamentoUseCase);
        this.listFuncionarioDepartamentoUseCase = Objects.requireNonNull(listFuncionarioDepartamentoUseCase);
        this.getTotalFuncionarioPorDepartamentoUseCase = Objects.requireNonNull(getTotalFuncionarioPorDepartamentoUseCase);
    }

    @Override
    public ResponseEntity<?> create(FuncionarioDepartamentoDTO input) {

        final var aCommand = CreateFuncionarioDepartamentoCommand.with(
                Departamento.with(
                        DepartamentoID.from(input.departamento().id()),
                        input.departamento().departamento(),
                        input.departamento().qtdeFuncionarios()),
                Funcionario.with(
                        FuncionarioID.from(input.funcionario().id()),
                        input.funcionario().nome(),
                        input.funcionario().endereco(),
                        input.funcionario().bairro(),
                        input.funcionario().cep(),
                        input.funcionario().telefone(),
                        input.funcionario().salario(),
                        input.funcionario().dataContrato(),
                        input.funcionario().funcao())
                );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateFuncionarioDepartamentoOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/funcionario-departamentos/" + output.id())).body(output);

        return this.createFuncionarioDepartamentoUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }
        this.deleteFuncionarioDepartamentoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<FuncionarioDepartamentoSimplesDTO> listByNomeFuncaoDepartamento(String nome, String departamento) {
        return this.listFuncionarioDepartamentoUseCase.execute(new ListFuncionarioDepartamentoCommand(nome, departamento))
                .stream().map(FuncionarioDepartamentoSimplesDTO::from).toList();
    }

    @Override
    public List<TotalFuncionariosPorDepartamentoDTO> listTotalFuncionariosByDepartamentoByNomeFuncaoDepartamento() {
        return this.getTotalFuncionarioPorDepartamentoUseCase.execute()
                .stream().map(TotalFuncionariosPorDepartamentoDTO::from).toList();
    }
}
