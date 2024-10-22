package dev.wilsondaniels.officeflow.application.funcionario.create;

import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioGateway;
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
public class CreateFuncionarioUseCaseTest {

    @InjectMocks
    private DefaultCreateFuncionarioUseCase useCase;

    @Mock
    private FuncionarioGateway funcionarioGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(funcionarioGateway);
    }

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit e Mockito.
    // A cobertura de testes deveria exercitar diversas outras condições

    @Test
    public void givenAValidCommand_whenCallsCreateFuncionario_shouldReturnFuncionarioId() {

        final var expectedNome = "John Scofield";
        final var expectedEndereco = "Rua 11";
        final var expectedBairro = "Vila do Sul";
        final var expectedCep = "67890-423";
        final var expectedTelefone = "9876543210";
        final var expectedSalario = BigDecimal.valueOf(5000.00);
        final var expectedDataContrato = LocalDate.of(2024,11, 25);
        final var expectedFuncao = "Gerente de TI";

        final var aCommand = CreateFuncionarioCommand.with(expectedNome, expectedEndereco,
                expectedBairro, expectedCep,  expectedTelefone, expectedSalario,
                expectedDataContrato, expectedFuncao);

        when(funcionarioGateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(funcionarioGateway, times(1)).create(argThat(funcionario ->
                Objects.equals(expectedNome, funcionario.getNome())
                        && Objects.equals(expectedEndereco, funcionario.getEndereco())
                        && Objects.equals(expectedBairro, funcionario.getBairro())
                        && Objects.equals(expectedCep, funcionario.getCep())
                        && Objects.equals(expectedTelefone, funcionario.getTelefone())
                        && Objects.equals(expectedSalario, funcionario.getSalario())
                        && Objects.equals(expectedDataContrato, funcionario.getDataContrato())
                        && Objects.equals(expectedFuncao, funcionario.getFuncao())
                        && Objects.nonNull(funcionario.getId())
        ));
    }

    @Test
    public void givenAInvalidNome_whenCallsCreateDepartamento_thenShouldReturnDomainException() {

        final String expectedNome = null;
        final var expectedEndereco = "Rua 11";
        final var expectedBairro = "Vila do Sul";
        final var expectedCep = "67890-423";
        final var expectedTelefone = "9876543210";
        final var expectedSalario = BigDecimal.valueOf(5000.00);
        final var expectedDataContrato = LocalDate.of(2024,11, 25);
        final var expectedFuncao = "Gerente de TI";
        final var expectedErrorMessage = "'nome' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = CreateFuncionarioCommand.with(expectedNome, expectedEndereco,
                expectedBairro, expectedCep,  expectedTelefone, expectedSalario,
                expectedDataContrato, expectedFuncao);

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(funcionarioGateway, times(0)).create(any());
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAException() {

        final var expectedNome = "John Scofield";
        final var expectedEndereco = "Rua 11";
        final var expectedBairro = "Vila do Sul";
        final var expectedCep = "67890-423";
        final var expectedTelefone = "9876543210";
        final var expectedSalario = BigDecimal.valueOf(5000.00);
        final var expectedDataContrato = LocalDate.of(2024,11, 25);
        final var expectedFuncao = "Gerente de TI";
        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var aCommand = CreateFuncionarioCommand.with(expectedNome, expectedEndereco,
                expectedBairro, expectedCep,  expectedTelefone, expectedSalario,
                expectedDataContrato, expectedFuncao);

        when(funcionarioGateway.create(any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(funcionarioGateway, times(1)).create(argThat(funcionario ->
                Objects.equals(expectedNome, funcionario.getNome())
                        && Objects.equals(expectedEndereco, funcionario.getEndereco())
                        && Objects.equals(expectedBairro, funcionario.getBairro())
                        && Objects.equals(expectedCep, funcionario.getCep())
                        && Objects.equals(expectedTelefone, funcionario.getTelefone())
                        && Objects.equals(expectedSalario, funcionario.getSalario())
                        && Objects.equals(expectedDataContrato, funcionario.getDataContrato())
                        && Objects.equals(expectedFuncao, funcionario.getFuncao())
                        && Objects.nonNull(funcionario.getId())
        ));
    }
}
