package dev.wilsondaniels.officeflow.application.departamento.update;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoGateway;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoID;
import dev.wilsondaniels.officeflow.domain.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateDepartamentoUseCaseTest {

    @InjectMocks
    private DefaultUpdateDepartamentoUseCase useCase;

    @Mock
    private DepartamentoGateway departamentoGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(departamentoGateway);
    }

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit e Mockito.
    // A cobertura de testes deveria exercitar diversas outras condições

    @Test
    public void givenAValidCommand_whenCallsUpdateDepartamento_shouldReturnDepartamentoId() {

        final var aDepartamento = Departamento.newDepartamento("SAC", 10);

        final var expectedDepartamento = "Atendimento ao Consumidor (SAC)";
        final var expectedQtdeFuncionarios = 15;
        final var expectedId = aDepartamento.getId();

        final var aCommand = UpdateDepartamentoCommand.with(
                expectedId.getValue(),
                expectedDepartamento,
                expectedQtdeFuncionarios
        );

        when(departamentoGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(Departamento.with(aDepartamento)));

        when(departamentoGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(departamentoGateway, times(1)).findById(eq(expectedId));

        Mockito.verify(departamentoGateway, times(1)).update(argThat(
                aUpdatedDepartamento ->
                        Objects.equals(expectedDepartamento, aUpdatedDepartamento.getDepartamento())
                                && Objects.equals(expectedQtdeFuncionarios, aUpdatedDepartamento.getQtdeFuncionarios())
                                && Objects.equals(expectedId, aUpdatedDepartamento.getId())
        ));
    }

    @Test
    public void givenAInvalidDepartamento_whenCallsUpdateDepartamento_thenShouldReturnDomainException() {

        final var aDepartamento = Departamento.newDepartamento("SAC", 10);

        final String expectedDepartamento = null;
        final var expectedQtdeFuncionarios = 15;
        final var expectedId = aDepartamento.getId();

        final var expectedErrorMessage = "'departamento' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = UpdateDepartamentoCommand.with(
                expectedId.getValue(),
                expectedDepartamento,
                expectedQtdeFuncionarios
        );

        when(departamentoGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(Departamento.with(aDepartamento)));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(departamentoGateway, times(0)).update(any());
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAException() {

        final var aDepartamento = Departamento.newDepartamento("SAC", 10);

        final var expectedDepartamento = "Atendimento ao Consumidor (SAC)";
        final var expectedQtdeFuncionarios = 15;
        final var expectedId = aDepartamento.getId();
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var aCommand = UpdateDepartamentoCommand.with(
                expectedId.getValue(),
                expectedDepartamento,
                expectedQtdeFuncionarios
        );

        when(departamentoGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(Departamento.with(aDepartamento)));

        when(departamentoGateway.update(any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(departamentoGateway, times(1)).update(argThat(
                aUpdatedDepartamento ->
                        Objects.equals(expectedDepartamento, aUpdatedDepartamento.getDepartamento())
                                && Objects.equals(expectedQtdeFuncionarios, aUpdatedDepartamento.getQtdeFuncionarios())
                                && Objects.equals(expectedId, aUpdatedDepartamento.getId())
        ));
    }

    @Test
    public void givenACommandWithInvalidID_whenCallsUpdateDepartamento_shouldReturnNotFoundException() {

        final var expectedDepartamento = "Atendimento ao Consumidor (SAC)";
        final var expectedQtdeFuncionarios = 15;
        final var expectedId = "123";
        final var expectedErrorMessage = "Departamento with ID 123 was not found";
        final var expectedErrorCount = 1;

        final var aCommand = UpdateDepartamentoCommand.with(
                expectedId,
                expectedDepartamento,
                expectedQtdeFuncionarios
        );

        when(departamentoGateway.findById(eq(DepartamentoID.from(expectedId))))
                .thenReturn(Optional.empty());

        final var actualException =
                Assertions.assertThrows(NotFoundException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(departamentoGateway, times(1)).findById(eq(DepartamentoID.from(expectedId)));

        Mockito.verify(departamentoGateway, times(0)).update(any());
    }
}