package dev.wilsondaniels.officeflow.application.departamento.update;

import dev.wilsondaniels.officeflow.application.UseCase;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateDepartamentoUseCase
        extends UseCase<UpdateDepartamentoCommand, Either<Notification, UpdateDepartamentoOutput>> {
}
