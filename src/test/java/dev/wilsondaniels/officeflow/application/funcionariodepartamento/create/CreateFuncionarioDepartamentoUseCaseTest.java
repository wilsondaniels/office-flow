package dev.wilsondaniels.officeflow.application.funcionariodepartamento.create;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateFuncionarioDepartamentoUseCaseTest {

    @InjectMocks
    private DefaultCreateFuncionarioDepartamentoUseCase useCase;

    @Mock
    private FuncionarioDepartamentoGateway funcionarioDepartamentoGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(funcionarioDepartamentoGateway);
    }

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit e Mockito.
    // A cobertura de testes deveria exercitar diversas outras condições

    @Test
    public void givenAValidCommand_whenCallsCreateFuncionarioDepartamento_shouldReturnFuncionarioDepartamentoId() {

        final var expectedFuncionario =
                Funcionario.newFuncionario("John Scofield", "Rua 11", "Vila do Sul", "67890-423",
                        "9876543210", BigDecimal.valueOf(5000.00), LocalDate.of(2024,11, 25), "Gerente de TI");
        final var expectedDepartamento =
                Departamento.newDepartamento("Atendimento ao Consumidor (SAC)", 15);

        final var aCommand =  CreateFuncionarioDepartamentoCommand.with(expectedDepartamento, expectedFuncionario);

        when(funcionarioDepartamentoGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(funcionarioDepartamentoGateway, times(1)).create(argThat(funcionario ->
                Objects.equals(expectedFuncionario, funcionario.getFuncionario())
                        && Objects.equals(expectedDepartamento, funcionario.getDepartamento())
                        && Objects.nonNull(funcionario.getId())
        ));
    }

    @Test
    public void givenAInvalidFuncionario_whenCallsCreatefuncionarioDepartamento_thenShouldReturnDomainException() {

        final Funcionario expectedFuncionario = null;

        final var expectedDepartamento = Departamento.newDepartamento(
                "Atendimento ao Consumidor (SAC)", 15);
        final var expectedErrorMessage = "'funcionario' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand =  CreateFuncionarioDepartamentoCommand.with(expectedDepartamento, expectedFuncionario);

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(funcionarioDepartamentoGateway, times(0)).create(any());
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAException() {

        final var expectedFuncionario =
                Funcionario.newFuncionario("John Scofield", "Rua 11", "Vila do Sul", "67890-423",
                        "9876543210", BigDecimal.valueOf(5000.00), LocalDate.of(2024,11, 25), "Gerente de TI");
        final var expectedDepartamento =
                Departamento.newDepartamento("Atendimento ao Consumidor (SAC)", 15);

        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var aCommand =  CreateFuncionarioDepartamentoCommand.with(expectedDepartamento, expectedFuncionario);

        when(funcionarioDepartamentoGateway.create(any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(funcionarioDepartamentoGateway, times(1)).create(argThat(funcionario ->
                Objects.equals(expectedFuncionario, funcionario.getFuncionario())
                        && Objects.equals(expectedDepartamento, funcionario.getDepartamento())
                        && Objects.nonNull(funcionario.getId())
        ));
    }
}
