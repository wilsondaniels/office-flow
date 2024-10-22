package dev.wilsondaniels.officeflow.infrastructure.departamento.api.controller;

import dev.wilsondaniels.officeflow.application.departamento.create.CreateDepartamentoCommand;
import dev.wilsondaniels.officeflow.application.departamento.create.CreateDepartamentoOutput;
import dev.wilsondaniels.officeflow.application.departamento.create.CreateDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.delete.DeleteDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.retrieve.get.DepartamentoOutput;
import dev.wilsondaniels.officeflow.application.departamento.retrieve.get.GetDepartamentoByIdUseCase;
import dev.wilsondaniels.officeflow.application.departamento.retrieve.list.ListDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.update.UpdateDepartamentoCommand;
import dev.wilsondaniels.officeflow.application.departamento.update.UpdateDepartamentoOutput;
import dev.wilsondaniels.officeflow.application.departamento.update.UpdateDepartamentoUseCase;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import dev.wilsondaniels.officeflow.infrastructure.departamento.api.DepartamentoAPI;
import dev.wilsondaniels.officeflow.infrastructure.departamento.dto.DepartamentoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static dev.wilsondaniels.officeflow.infrastructure.util.Util.*;

@RestController
public class DepartamentoController implements DepartamentoAPI {

    private final CreateDepartamentoUseCase createDepartamentoUseCase;
    private final GetDepartamentoByIdUseCase getDepartamentoByIdUseCase;
    private final UpdateDepartamentoUseCase updateDepartamentoUseCase;
    private final DeleteDepartamentoUseCase deleteDepartamentoUseCase;
    private final ListDepartamentoUseCase listDepartamentoUseCase;

    public DepartamentoController(
            final CreateDepartamentoUseCase createDepartamentoUseCase,
            final GetDepartamentoByIdUseCase getDepartamentoByIdUseCase,
            final UpdateDepartamentoUseCase updateDepartamentoUseCase,
            final DeleteDepartamentoUseCase deleteDepartamentoUseCase,
            final ListDepartamentoUseCase listDepartamentoUseCase
    ) {
        this.createDepartamentoUseCase = Objects.requireNonNull(createDepartamentoUseCase);
        this.getDepartamentoByIdUseCase = Objects.requireNonNull(getDepartamentoByIdUseCase);
        this.updateDepartamentoUseCase = Objects.requireNonNull(updateDepartamentoUseCase);
        this.deleteDepartamentoUseCase = Objects.requireNonNull(deleteDepartamentoUseCase);
        this.listDepartamentoUseCase = Objects.requireNonNull(listDepartamentoUseCase);
    }

    @Override
    public ResponseEntity<?> create(DepartamentoDTO input) {

        final var aCommand = CreateDepartamentoCommand.with(
                input.departamento(),
                input.qtdeFuncionarios()
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateDepartamentoOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/departamentos/" + output.id())).body(output);

        return this.createDepartamentoUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> getById(String id) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }

        final DepartamentoOutput departamentoOutput = this.getDepartamentoByIdUseCase.execute(id);
        return new ResponseEntity<>(new DepartamentoDTO(departamentoOutput.id().getValue(),
                departamentoOutput.departamento(), departamentoOutput.qtdeFuncionarios()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateById(String id, DepartamentoDTO input) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }

        final var aCommand = UpdateDepartamentoCommand.with(
                id,
                input.departamento(),
                input.qtdeFuncionarios()
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateDepartamentoOutput, ResponseEntity<?>> onSuccess =
                ResponseEntity::ok;

        return this.updateDepartamentoUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
        if (!isUUIDValid(id)) {
            return ResponseEntity.notFound().build();
        }
        this.deleteDepartamentoUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<DepartamentoDTO> listByDepto(String departamento) {
        return listDepartamentoUseCase.execute(departamento).stream().map(DepartamentoDTO::from).toList();
    }
}
