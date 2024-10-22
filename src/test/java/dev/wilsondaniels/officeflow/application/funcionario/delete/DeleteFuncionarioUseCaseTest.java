package dev.wilsondaniels.officeflow.application.funcionario.delete;

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

@ExtendWith(MockitoExtension.class)
public class DeleteFuncionarioUseCaseTest {

    @InjectMocks
    private DefaultDeleteFuncionarioUseCase useCase;

    @Mock
    private FuncionarioGateway funcionarioGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(funcionarioGateway);
    }

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit e Mockito.
    // A cobertura de testes deveria exercitar diversas outras condições

    @Test
    public void givenAValidId_whenCallsDeleteFuncionario_shouldBeOK() {

        final var aFuncionario =
                Funcionario.newFuncionario("John II", "Rua 1", "Vila do Sul",
                        "12345-123", "999994444", BigDecimal.valueOf(1000.00),
                        LocalDate.of(2022,11, 25), "Gerente de TI");

        final var expectedId = aFuncionario.getId();

        Mockito.doNothing().when(funcionarioGateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(funcionarioGateway, Mockito.times(1)).deleteById(expectedId);
    }

    @Test
    public void givenAInvalidId_whenCallsDeleteFuncionario_shouldBeOK() {

        final var expectedId = FuncionarioID.from("123");

        Mockito.doNothing().when(funcionarioGateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(funcionarioGateway, Mockito.times(1)).deleteById(expectedId);
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {

        final var aFuncionario =
                Funcionario.newFuncionario("John II", "Rua 1", "Vila do Sul",
                        "12345-123", "999994444", BigDecimal.valueOf(1000.00),
                        LocalDate.of(2022,11, 25), "Gerente de TI");

        final var expectedId = aFuncionario.getId();

        Mockito.doThrow(new IllegalStateException("Gateway error"))
                .when(funcionarioGateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        Mockito.verify(funcionarioGateway, Mockito.times(1)).deleteById(expectedId);
    }
}