package dev.wilsondaniels.officeflow.application.funcionario.update;

import dev.wilsondaniels.officeflow.domain.exception.NotFoundException;
import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioGateway;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioID;
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
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateFuncionarioUseCaseTest {

    @InjectMocks
    private DefaultUpdateFuncionarioUseCase useCase;

    @Mock
    private FuncionarioGateway funcionarioGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(funcionarioGateway);
    }

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit e Mockito.
    // A cobertura de testes deveria exercitar diversas outras condições

    @Test
    public void givenAValidCommand_whenCallsUpdateFuncionario_shouldReturnFuncionarioId() {

        final var aFuncionario =
                Funcionario.newFuncionario("John II", "Rua 1", "Vila do Sul",
                        "12345-123", "999994444", BigDecimal.valueOf(1000.00),
                        LocalDate.of(2022,11, 25), "Gerente de TI");

        final var expectedNome = "John Scofield";
        final var expectedEndereco = "Rua 11";
        final var expectedBairro = "Vila do Sul";
        final var expectedCep = "67890-423";
        final var expectedTelefone = "9876543210";
        final var expectedSalario = BigDecimal.valueOf(5000.00);
        final var expectedDataContrato = LocalDate.of(2024,11, 25);
        final var expectedFuncao = "Gerente de TI";
        final var expectedId = aFuncionario.getId();

        final var aCommand = UpdateFuncionarioCommand.with(expectedId.getValue(), expectedNome,
                expectedEndereco, expectedBairro, expectedCep,  expectedTelefone, expectedSalario,
                expectedDataContrato, expectedFuncao);

        when(funcionarioGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(Funcionario.with(aFuncionario)));

        when(funcionarioGateway.update(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Mockito.verify(funcionarioGateway, times(1)).findById(eq(expectedId));

        Mockito.verify(funcionarioGateway, times(1)).update(argThat(
                aUpdatedDepartamento ->
                        Objects.equals(expectedNome, aUpdatedDepartamento.getNome())
                                && Objects.equals(expectedEndereco, aUpdatedDepartamento.getEndereco())
                                && Objects.equals(expectedBairro, aUpdatedDepartamento.getBairro())
                                && Objects.equals(expectedCep, aUpdatedDepartamento.getCep())
                                && Objects.equals(expectedTelefone, aUpdatedDepartamento.getTelefone())
                                && Objects.equals(expectedSalario, aUpdatedDepartamento.getSalario())
                                && Objects.equals(expectedDataContrato, aUpdatedDepartamento.getDataContrato())
                                && Objects.equals(expectedFuncao, aUpdatedDepartamento.getFuncao())
                                && Objects.equals(expectedId, aUpdatedDepartamento.getId())
        ));
    }

    @Test
    public void givenAInvalidFuncionario_whenCallsUpdateFuncionario_thenShouldReturnDomainException() {

        final var aFuncionario =
                Funcionario.newFuncionario("John II", "Rua 1", "Vila do Sul",
                        "12345-123", "999994444", BigDecimal.valueOf(1000.00),
                        LocalDate.of(2022,11, 25), "Gerente de TI");

        final String expectedNome = null;
        final var expectedEndereco = "Rua 11";
        final var expectedBairro = "Vila do Sul";
        final var expectedCep = "67890-423";
        final var expectedTelefone = "9876543210";
        final var expectedSalario = BigDecimal.valueOf(5000.00);
        final var expectedDataContrato = LocalDate.of(2024,11, 25);
        final var expectedFuncao = "Gerente de TI";
        final var expectedId = aFuncionario.getId();

        final var expectedErrorMessage = "'nome' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand = UpdateFuncionarioCommand.with(expectedId.getValue(), expectedNome,
                expectedEndereco, expectedBairro, expectedCep,  expectedTelefone, expectedSalario,
                expectedDataContrato, expectedFuncao);

        when(funcionarioGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(Funcionario.with(aFuncionario)));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(funcionarioGateway, times(0)).update(any());
    }

    @Test
    public void givenAValidCommand_whenGatewayThrowsRandomException_shouldReturnAException() {

        final var aFuncionario =
                Funcionario.newFuncionario("John II", "Rua 1", "Vila do Sul",
                        "12345-123", "999994444", BigDecimal.valueOf(1000.00),
                        LocalDate.of(2022,11, 25), "Gerente de TI");

        final var expectedNome = "John Scofield";
        final var expectedEndereco = "Rua 11";
        final var expectedBairro = "Vila do Sul";
        final var expectedCep = "67890-423";
        final var expectedTelefone = "9876543210";
        final var expectedSalario = BigDecimal.valueOf(5000.00);
        final var expectedDataContrato = LocalDate.of(2024,11, 25);
        final var expectedFuncao = "Gerente de TI";
        final var expectedId = aFuncionario.getId();

        final var expectedErrorMessage = "Gateway error";
        final var expectedErrorCount = 1;

        final var aCommand = UpdateFuncionarioCommand.with(expectedId.getValue(), expectedNome,
                expectedEndereco, expectedBairro, expectedCep,  expectedTelefone, expectedSalario,
                expectedDataContrato, expectedFuncao);

        when(funcionarioGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(Funcionario.with(aFuncionario)));

        when(funcionarioGateway.update(any()))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        Mockito.verify(funcionarioGateway, times(1)).update(argThat(
                aUpdatedDepartamento ->
                        Objects.equals(expectedNome, aUpdatedDepartamento.getNome())
                                && Objects.equals(expectedEndereco, aUpdatedDepartamento.getEndereco())
                                && Objects.equals(expectedBairro, aUpdatedDepartamento.getBairro())
                                && Objects.equals(expectedCep, aUpdatedDepartamento.getCep())
                                && Objects.equals(expectedTelefone, aUpdatedDepartamento.getTelefone())
                                && Objects.equals(expectedSalario, aUpdatedDepartamento.getSalario())
                                && Objects.equals(expectedDataContrato, aUpdatedDepartamento.getDataContrato())
                                && Objects.equals(expectedFuncao, aUpdatedDepartamento.getFuncao())
                                && Objects.equals(expectedId, aUpdatedDepartamento.getId())
        ));
    }

    @Test
    public void givenACommandWithInvalidID_whenCallsUpdateDepartamento_shouldReturnNotFoundException() {


        final var expectedNome = "John Scofield";
        final var expectedEndereco = "Rua 11";
        final var expectedBairro = "Vila do Sul";
        final var expectedCep = "67890-423";
        final var expectedTelefone = "9876543210";
        final var expectedSalario = BigDecimal.valueOf(5000.00);
        final var expectedDataContrato = LocalDate.of(2024,11, 25);
        final var expectedFuncao = "Gerente de TI";
        final var expectedId = "123";

        final var expectedErrorMessage = "Funcionario with ID 123 was not found";
        final var expectedErrorCount = 1;

        final var aCommand = UpdateFuncionarioCommand.with(expectedId, expectedNome,
                expectedEndereco, expectedBairro, expectedCep,  expectedTelefone, expectedSalario,
                expectedDataContrato, expectedFuncao);

        when(funcionarioGateway.findById(eq(FuncionarioID.from(expectedId))))
                .thenReturn(Optional.empty());

        final var actualException =
                Assertions.assertThrows(NotFoundException.class, () -> useCase.execute(aCommand));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());

        Mockito.verify(funcionarioGateway, times(1)).findById(eq(FuncionarioID.from(expectedId)));

        Mockito.verify(funcionarioGateway, times(0)).update(any());
    }
}