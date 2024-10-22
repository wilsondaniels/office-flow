package dev.wilsondaniels.officeflow.application.funcionariodepartamento.create;

import dev.wilsondaniels.officeflow.application.UseCase;
import dev.wilsondaniels.officeflow.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateFuncionarioDepartamentoUseCase
        extends UseCase<CreateFuncionarioDepartamentoCommand, Either<Notification, CreateFuncionarioDepartamentoOutput>> {
}
