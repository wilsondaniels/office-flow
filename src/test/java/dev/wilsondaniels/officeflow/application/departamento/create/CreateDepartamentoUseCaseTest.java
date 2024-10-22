package dev.wilsondaniels.officeflow.application.departamento.create;

import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateDepartamentoUseCaseTest {

    @InjectMocks
    private DefaultCreateDepartamentoUseCase useCase;

    @Mock
    private DepartamentoGateway departamentoGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(departamentoGateway);
    }

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit e Mockito.
    // A cobertura de testes deveria exercitar diversas outras condições

    @Test
    public void givenAValidCommand_whenCallsCreateDepartamento_shouldReturnDepartamentoId() {

        final var expectedDepartamento = "Atendimento ao Consumidor (SAC)";
        final var expectedQtdeFuncionarios = 15;

        final var aCommand = CreateDepartamentoCommand.with(expectedDepartamento, expectedQtdeFuncionarios);

        when(departamentoGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(departamentoGateway, times(1)).create(argThat(departamento ->
                Objects.equals(expectedDepartamento, departamento.getDepartamento())
                        && Objects.equals(expectedQtdeFuncionarios, departamento.getQtdeFuncionarios())
                        && Objects.nonNull(departamento.getId())
        ));
    }

    @Test
    public void givenAInvalidDepartamento_whenCallsCreateDepartamento_thenShouldReturnDomainException() {

        final String expectedDepartamento = null;
        final var expectedQtdeFuncionarios = 15;
        final var expectedErrorMessage = "'departamento' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = CreateDepartamentoCommand.with(expectedDepartamento, expectedQtdeFuncionarios);

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(departamentoGateway, times(0)).create(any());
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAException() {

        final var expectedDepartamento = "Atendimento ao Consumidor (SAC)";
        final var expectedQtdeFuncionarios = 15;
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var aCommand = CreateDepartamentoCommand.with(expectedDepartamento, expectedQtdeFuncionarios);

        when(departamentoGateway.create(any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(departamentoGateway, times(1)).create(argThat(departamento ->
                Objects.equals(expectedDepartamento, departamento.getDepartamento())
                        && Objects.equals(expectedQtdeFuncionarios, departamento.getQtdeFuncionarios())
                        && Objects.nonNull(departamento.getId())
        ));
    }
}
