package dev.wilsondaniels.officeflow.application.funcionariodepartamento.create;

import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamento;
import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoGateway;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateFuncionarioDepartamentoUseCase extends CreateFuncionarioDepartamentoUseCase {

    private final FuncionarioDepartamentoGateway funcionarioDepartamentoGateway;

    public DefaultCreateFuncionarioDepartamentoUseCase(final FuncionarioDepartamentoGateway funcionarioDepartamentoGateway) {
        this.funcionarioDepartamentoGateway = Objects.requireNonNull(funcionarioDepartamentoGateway);
    }

    @Override
    public Either<Notification, CreateFuncionarioDepartamentoOutput> execute(final CreateFuncionarioDepartamentoCommand command) {
        final var funcionario = command.funcionario();
        final var departamento = command.departamento();

        final var notification = Notification.create();

        final var aFuncionarioDepartamento = FuncionarioDepartamento.newFuncionarioDepartamento(funcionario, departamento);
        aFuncionarioDepartamento.validate(notification);

        return notification.hasError() ? Left(notification) : create(aFuncionarioDepartamento);
    }

    private Either<Notification, CreateFuncionarioDepartamentoOutput> create(final FuncionarioDepartamento aFuncionarioDepartamento) {
        return Try(() -> this.funcionarioDepartamentoGateway.create(aFuncionarioDepartamento))
                .toEither()
                .bimap(Notification::create, CreateFuncionarioDepartamentoOutput::from);
    }
}
