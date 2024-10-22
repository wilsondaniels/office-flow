package dev.wilsondaniels.officeflow.application.departamento.retrieve.get;

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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetDepartamentoByIdUseCaseTest {

    @InjectMocks
    private DefaultGetDepartamentoByIdUseCase useCase;

    @Mock
    private DepartamentoGateway departamentoGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(departamentoGateway);
    }

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit e Mockito.
    // A cobertura de testes deveria exercitar diversas outras condições

    @Test
    public void givenAValidId_whenCallsGetDepartamento_shouldReturnDepartamento() {

        final var expectedDepartamento = "Atendimento ao Consumidor (SAC)";
        final var expectedQtdeFuncionarios = 15;
        final var aDepartamento = Departamento.newDepartamento(expectedDepartamento, expectedQtdeFuncionarios);
        final var expectedId = aDepartamento.getId();

        when(departamentoGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(aDepartamento.clone()));

        final var actualDepartamento = useCase.execute(expectedId.getValue());

        Assertions.assertEquals(expectedId, actualDepartamento.id());
        Assertions.assertEquals(expectedDepartamento, actualDepartamento.departamento());
        Assertions.assertEquals(expectedQtdeFuncionarios, actualDepartamento.qtdeFuncionarios());
    }

    @Test
    public void givenAInvalidId_whenCallsGetDepartamento_shouldReturnNotFound() {
        final var expectedErrorMessage = "Departamento with ID 123 was not found";
        final var expectedId = DepartamentoID.from("123");

        when(departamentoGateway.findById(eq(expectedId)))
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
        final var expectedId = DepartamentoID.from("123");

        when(departamentoGateway.findById(eq(expectedId)))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = Assertions.assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}