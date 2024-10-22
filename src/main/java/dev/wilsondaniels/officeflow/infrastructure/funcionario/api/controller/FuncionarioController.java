package dev.wilsondaniels.officeflow.infrastructure.funcionario.api.controller;

import dev.wilsondaniels.officeflow.application.funcionario.create.CreateFuncionarioCommand;
import dev.wilsondaniels.officeflow.application.funcionario.create.CreateFuncionarioOutput;
import dev.wilsondaniels.officeflow.application.funcionario.create.CreateFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.delete.DeleteFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.retrieve.get.FuncionarioOutput;
import dev.wilsondaniels.officeflow.application.funcionario.retrieve.get.GetFuncionarioByIdUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.retrieve.list.ListFuncionarioCommand;
import dev.wilsondaniels.officeflow.application.funcionario.retrieve.list.ListFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.retrieve.total.GetTotalFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.update.UpdateFuncionarioCommand;
import dev.wilsondaniels.officeflow.application.funcionario.update.UpdateFuncionarioOutput;
import dev.wilsondaniels.officeflow.application.funcionario.update.UpdateFuncionarioUseCase;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import dev.wilsondaniels.officeflow.infrastructure.funcionario.api.FuncionarioAPI;
import dev.wilsondaniels.officeflow.infrastructure.funcionario.dto.FuncionarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static dev.wilsondaniels.officeflow.infrastructure.util.Util.isUUIDValid;

@RestController
public class FuncionarioController implements FuncionarioAPI {

    private final CreateFuncionarioUseCase createFuncionarioUseCase;
    private final GetFuncionarioByIdUseCase getFuncionarioByIdUseCase;
    private final UpdateFuncionarioUseCase updateFuncionarioUseCase;
    private final DeleteFuncionarioUseCase deleteFuncionarioUseCase;
    private final ListFuncionarioUseCase listFuncionarioUseCase;
    private final GetTotalFuncionarioUseCase getTotalFuncionarioUseCase;

    public FuncionarioController(
            final CreateFuncionarioUseCase createFuncionarioUseCase,
            final GetFuncionarioByIdUseCase getFuncionarioByIdUseCase,
            final UpdateFuncionarioUseCase updateFuncionarioUseCase,
            final DeleteFuncionarioUseCase deleteFuncionarioUseCase,
            final ListFuncionarioUseCase listFuncionarioUseCase,
            final GetTotalFuncionarioUseCase getTotalFuncionarioUseCase
    ) {
        this.createFuncionarioUseCase = Objects.requireNonNull(createFuncionarioUseCase);
        this.getFuncionarioByIdUseCase = Objects.requireNonNull(getFuncionarioByIdUseCase);
        this.updateFuncionarioUseCase = Objects.requireNonNull(updateFuncionarioUseCase);
        this.deleteFuncionarioUseCase = Objects.requireNonNull(deleteFuncionarioUseCase);
        this.listFuncionarioUseCase = Objects.requireNonNull(listFuncionarioUseCase);
        this.getTotalFuncionarioUseCase = Objects.requireNonNull(getTotalFuncionarioUseCase);
    }

    @Override
    public ResponseEntity<?> create(FuncionarioDTO input) {

        final var aCommand = CreateFuncionarioCommand.with(
                input.nome(),
                input.endereco(),
                input.bairro(),
                input.cep(),
                input.telefone(),
                input.salario(),
                input.dataContrato(),
                input.funcao()
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateFuncionarioOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/funcionarios/" + output.id())).body(output);

        return this.createFuncionarioUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> getById(String id) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }

        final FuncionarioOutput funcionarioOutput = this.getFuncionarioByIdUseCase.execute(id);
        return new ResponseEntity<>(new FuncionarioDTO(
                funcionarioOutput.id().getValue(),
                funcionarioOutput.nome(),
                funcionarioOutput.endereco(),
                funcionarioOutput.bairro(),
                funcionarioOutput.cep(),
                funcionarioOutput.telefone(),
                funcionarioOutput.salario(),
                funcionarioOutput.dataContrato(),
                funcionarioOutput.funcao()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateById(String id, FuncionarioDTO input) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }

        final var aCommand = UpdateFuncionarioCommand.with(
                input.id(),
                input.nome(),
                input.endereco(),
                input.bairro(),
                input.cep(),
                input.telefone(),
                input.salario(),
                input.dataContrato(),
                input.funcao()
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateFuncionarioOutput, ResponseEntity<?>> onSuccess =
                ResponseEntity::ok;

        return this.updateFuncionarioUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }
        this.deleteFuncionarioUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<FuncionarioDTO> listByNomeFuncaoDepartamento(String nome, String funcao) {
         return this.listFuncionarioUseCase.execute(new ListFuncionarioCommand(nome, funcao, false))
                 .stream().map(FuncionarioDTO::from).toList();
    }

    @Override
    public List<FuncionarioDTO> listFuncionariosRankedBySalarioAsc() {
        return this.listFuncionarioUseCase.execute(new ListFuncionarioCommand("", "", true))
                .stream().map(FuncionarioDTO::from).toList();
    }

    @Override
    public Long countTotalNumberOfFuncionario(double salarioMinimo) {
        return this.getTotalFuncionarioUseCase.execute(salarioMinimo);
    }
}
