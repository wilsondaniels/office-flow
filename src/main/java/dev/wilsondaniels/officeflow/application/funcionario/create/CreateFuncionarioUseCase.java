package dev.wilsondaniels.officeflow.application.funcionario.create;

import dev.wilsondaniels.officeflow.application.UseCase;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateFuncionarioUseCase
        extends UseCase<CreateFuncionarioCommand, Either<Notification, CreateFuncionarioOutput>> {
}
