package dev.wilsondaniels.officeflow.application.departamento.create;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoGateway;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateDepartamentoUseCase extends CreateDepartamentoUseCase {

    private final DepartamentoGateway departamentoGateway;

    public DefaultCreateDepartamentoUseCase(final DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = Objects.requireNonNull(departamentoGateway);
    }

    @Override
    public Either<Notification, CreateDepartamentoOutput> execute(final CreateDepartamentoCommand command) {
        final var departamento = command.departamento();
        final var qtdeFuncionarios = command.qtdeFuncionarios();

        final var notification = Notification.create();

        final var aDepartamento = Departamento.newDepartamento(departamento, qtdeFuncionarios);
        aDepartamento.validate(notification);

        return notification.hasError() ? Left(notification) : create(aDepartamento);
    }

    private Either<Notification, CreateDepartamentoOutput> create(final Departamento aDepartamento) {
        return Try(() -> this.departamentoGateway.create(aDepartamento))
                .toEither()
                .bimap(Notification::create, CreateDepartamentoOutput::from);
    }
}
