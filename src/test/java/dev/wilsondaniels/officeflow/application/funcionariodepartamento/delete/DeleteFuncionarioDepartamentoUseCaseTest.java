package dev.wilsondaniels.officeflow.application.funcionariodepartamento.delete;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamento;
import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoGateway;
import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoID;
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
public class DeleteFuncionarioDepartamentoUseCaseTest {

    @InjectMocks
    private DefaultDeleteFuncionarioDepartamentoUseCase useCase;

    @Mock
    private FuncionarioDepartamentoGateway funcionarioDepartamentoGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(funcionarioDepartamentoGateway);
    }

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit e Mockito.
    // A cobertura de testes deveria exercitar diversas outras condições

    @Test
    public void givenAValidId_whenCallsDeleteFuncionario_shouldBeOK() {

        final var expectedFuncionario =
                Funcionario.newFuncionario("John Scofield", "Rua 11", "Vila do Sul", "67890-423",
                        "9876543210", BigDecimal.valueOf(5000.00), LocalDate.of(2024,11, 25), "Gerente de TI");
        final var expectedDepartamento =
                Departamento.newDepartamento("Atendimento ao Consumidor (SAC)", 15);

        final var aFuncionarioDepartamento =
                FuncionarioDepartamento.newFuncionarioDepartamento(expectedFuncionario, expectedDepartamento);

        final var expectedId = aFuncionarioDepartamento.getId();

        Mockito.doNothing().when(funcionarioDepartamentoGateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(funcionarioDepartamentoGateway, Mockito.times(1)).deleteById(expectedId);
    }

    @Test
    public void givenAInvalidId_whenCallsDeleteFuncionario_shouldBeOK() {

        final var expectedId = FuncionarioDepartamentoID.from("123");

        Mockito.doNothing().when(funcionarioDepartamentoGateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(funcionarioDepartamentoGateway, Mockito.times(1)).deleteById(expectedId);
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {

        final var expectedFuncionario =
                Funcionario.newFuncionario("John Scofield", "Rua 11", "Vila do Sul", "67890-423",
                        "9876543210", BigDecimal.valueOf(5000.00), LocalDate.of(2024,11, 25), "Gerente de TI");
        final var expectedDepartamento =
                Departamento.newDepartamento("Atendimento ao Consumidor (SAC)", 15);

        final var aFuncionarioDepartamento =
                FuncionarioDepartamento.newFuncionarioDepartamento(expectedFuncionario, expectedDepartamento);

        final var expectedId = aFuncionarioDepartamento.getId();

        Mockito.doThrow(new IllegalStateException("Gateway error"))
                .when(funcionarioDepartamentoGateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        Mockito.verify(funcionarioDepartamentoGateway, Mockito.times(1)).deleteById(expectedId);
    }
}