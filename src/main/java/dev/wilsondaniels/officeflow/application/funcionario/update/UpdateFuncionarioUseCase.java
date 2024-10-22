package dev.wilsondaniels.officeflow.application.funcionario.update;

import dev.wilsondaniels.officeflow.application.UseCase;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateFuncionarioUseCase
        extends UseCase<UpdateFuncionarioCommand, Either<Notification, UpdateFuncionarioOutput>> {
}
