package dev.wilsondaniels.officeflow.application.departamento.create;

import dev.wilsondaniels.officeflow.application.UseCase;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateDepartamentoUseCase
        extends UseCase<CreateDepartamentoCommand, Either<Notification, CreateDepartamentoOutput>> {
}
