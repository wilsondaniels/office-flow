package dev.wilsondaniels.officeflow.application.departamento.update;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoGateway;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoID;
import dev.wilsondaniels.officeflow.domain.exception.DomainException;
import dev.wilsondaniels.officeflow.domain.exception.NotFoundException;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultUpdateDepartamentoUseCase extends UpdateDepartamentoUseCase {

    private final DepartamentoGateway departamentoGateway;

    public DefaultUpdateDepartamentoUseCase(final DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = Objects.requireNonNull(departamentoGateway);
    }

    @Override
    public Either<Notification, UpdateDepartamentoOutput> execute(final UpdateDepartamentoCommand command) {

        final var anId = DepartamentoID.from(command.id());
        final var departamento = command.departamento();
        final var qtdeFuncionarios = command.qtdeFuncionarios();

        final var aDepartamento = this.departamentoGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();

        aDepartamento.update(departamento, qtdeFuncionarios).validate(notification);

        return notification.hasError() ? Left(notification) : update(aDepartamento);
    }

    private static Supplier<DomainException> notFound(DepartamentoID anId) {
        return () -> NotFoundException.with(Departamento.class, anId);
    }
    private Either<Notification, UpdateDepartamentoOutput> update(final Departamento aDepartamento) {
        return Try(() -> this.departamentoGateway.update(aDepartamento))
                .toEither()
                .bimap(Notification::create, UpdateDepartamentoOutput::from);
    }
}
