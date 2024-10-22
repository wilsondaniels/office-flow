package dev.wilsondaniels.officeflow.application.funcionario.update;

import dev.wilsondaniels.officeflow.domain.exception.DomainException;
import dev.wilsondaniels.officeflow.domain.exception.NotFoundException;
import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioGateway;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioID;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultUpdateFuncionarioUseCase extends UpdateFuncionarioUseCase {

    private final FuncionarioGateway funcionarioGateway;

    public DefaultUpdateFuncionarioUseCase(final FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = Objects.requireNonNull(funcionarioGateway);
    }

    @Override
    public Either<Notification, UpdateFuncionarioOutput> execute(final UpdateFuncionarioCommand command) {

        final var id = FuncionarioID.from(command.id());
        final var nome = command.nome();
        final var endereco = command.endereco();
        final var bairro = command.bairro();
        final var cep = command.cep();
        final var telefone = command.telefone();
        final var salario = command.salario();
        final var dataContrato = command.dataContrato();
        final var funcao = command.funcao();

        final var aFuncionario = this.funcionarioGateway.findById(id)
                .orElseThrow(notFound(id));

        final var notification = Notification.create();

        aFuncionario.update(nome, endereco, bairro, cep, telefone, salario,
                dataContrato, funcao).validate(notification);

        return notification.hasError() ? Left(notification) : update(aFuncionario);
    }

    private static Supplier<DomainException> notFound(FuncionarioID anId) {
        return () -> NotFoundException.with(Funcionario.class, anId);
    }
    private Either<Notification, UpdateFuncionarioOutput> update(final Funcionario aFuncionario) {
        return Try(() -> this.funcionarioGateway.update(aFuncionario))
                .toEither()
                .bimap(Notification::create, UpdateFuncionarioOutput::from);
    }
}
