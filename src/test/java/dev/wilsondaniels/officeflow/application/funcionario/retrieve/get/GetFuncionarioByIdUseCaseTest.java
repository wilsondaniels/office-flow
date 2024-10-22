package dev.wilsondaniels.officeflow.application.funcionario.retrieve.get;

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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetFuncionarioByIdUseCaseTest {

    @InjectMocks
    private DefaultGetFuncionarioByIdUseCase useCase;

    @Mock
    private FuncionarioGateway funcionarioGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(funcionarioGateway);
    }

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit e Mockito.
    // A cobertura de testes deveria exercitar diversas outras condições

    @Test
    public void givenAValidId_whenCallsGetFuncionario_shouldReturnFuncionario() {

        final var expectedNome = "John Scofield";
        final var expectedEndereco = "Rua 11";
        final var expectedBairro = "Vila do Sul";
        final var expectedCep = "67890-423";
        final var expectedTelefone = "9876543210";
        final var expectedSalario = BigDecimal.valueOf(5000.00);
        final var expectedDataContrato = LocalDate.of(2024,11, 25);
        final var expectedFuncao = "Gerente de TI";

        final var aFuncionario = Funcionario.newFuncionario(expectedNome, expectedEndereco, expectedBairro,
                expectedCep, expectedTelefone, expectedSalario, expectedDataContrato, expectedFuncao);
        final var expectedId = aFuncionario.getId();

        when(funcionarioGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(aFuncionario.clone()));

        final var actualDepartamento = useCase.execute(expectedId.getValue());

        Assertions.assertEquals(expectedId, actualDepartamento.id());
        Assertions.assertEquals(expectedNome, actualDepartamento.nome());
        Assertions.assertEquals(expectedEndereco, actualDepartamento.endereco());
        Assertions.assertEquals(expectedBairro, actualDepartamento.bairro());
        Assertions.assertEquals(expectedCep, actualDepartamento.cep());
        Assertions.assertEquals(expectedTelefone, actualDepartamento.telefone());
        Assertions.assertEquals(expectedSalario, actualDepartamento.salario());
        Assertions.assertEquals(expectedDataContrato, actualDepartamento.dataContrato());
        Assertions.assertEquals(expectedFuncao, actualDepartamento.funcao());
    }

    @Test
    public void givenAInvalidId_whenCallsGetFuncionario_shouldReturnNotFound() {
        final var expectedErrorMessage = "Funcionario with ID 123 was not found";
        final var expectedId = FuncionarioID.from("123");

        when(funcionarioGateway.findById(eq(expectedId)))
                .thenReturn(Optional.empty());

        final var actualException = Assertions.assertThrows(
                NotFoundException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
        final var expectedErrorMessage = "Gateway error";
        final var expectedId = FuncionarioID.from("123");

        when(funcionarioGateway.findById(eq(expectedId)))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}