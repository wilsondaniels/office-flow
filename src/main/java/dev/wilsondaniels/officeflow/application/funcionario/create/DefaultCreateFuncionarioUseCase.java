package dev.wilsondaniels.officeflow.application.funcionario.create;

import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioGateway;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultCreateFuncionarioUseCase extends CreateFuncionarioUseCase {

    private final FuncionarioGateway funcionarioGateway;

    public DefaultCreateFuncionarioUseCase(final FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = Objects.requireNonNull(funcionarioGateway);
    }

    @Override
    public Either<Notification, CreateFuncionarioOutput> execute(final CreateFuncionarioCommand command) {

        final var nome = command.nome();
        final var endereco = command.endereco();
        final var bairro = command.bairro();
        final var cep = command.cep();
        final var telefone = command.telefone();
        final var salario = command.salario();
        final var dataContrato = command.dataContrato();
        final var funcao = command.funcao();

        final var notification = Notification.create();

        final var aFuncionario = Funcionario.newFuncionario(nome, endereco, bairro, cep, telefone,
                salario, dataContrato, funcao);
        aFuncionario.validate(notification);

        return notification.hasError() ? Left(notification) : create(aFuncionario);
    }

    private Either<Notification, CreateFuncionarioOutput> create(final Funcionario aFuncionario) {
        return Try(() -> this.funcionarioGateway.create(aFuncionario))
                .toEither()
                .bimap(Notification::create, CreateFuncionarioOutput::from);
    }
}
