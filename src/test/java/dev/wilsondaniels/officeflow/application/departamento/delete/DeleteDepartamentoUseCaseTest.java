package dev.wilsondaniels.officeflow.application.departamento.delete;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoGateway;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteDepartamentoUseCaseTest {

    @InjectMocks
    private DefaultDeleteDepartamentoUseCase useCase;

    @Mock
    private DepartamentoGateway departamentoGateway;

    @BeforeEach
    void cleanUp() {
        Mockito.reset(departamentoGateway);
    }

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit e Mockito.
    // A cobertura de testes deveria exercitar diversas outras condições

    @Test
    public void givenAValidId_whenCallsDeleteDepartamento_shouldBeOK() {

        final var aDepartamento = Departamento.newDepartamento("SAC", 10);

        final var expectedId = aDepartamento.getId();

        Mockito.doNothing().when(departamentoGateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(departamentoGateway, Mockito.times(1)).deleteById(expectedId);
    }

    @Test
    public void givenAInvalidId_whenCallsDeleteDepartamento_shouldBeOK() {

        final var expectedId = DepartamentoID.from("123");

        Mockito.doNothing().when(departamentoGateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertDoesNotThrow(() -> useCase.execute(expectedId.getValue()));

        Mockito.verify(departamentoGateway, Mockito.times(1)).deleteById(expectedId);
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {

        final var aDepartamento = Departamento.newDepartamento("SAC", 10);

        final var expectedId = aDepartamento.getId();

        Mockito.doThrow(new IllegalStateException("Gateway error"))
                .when(departamentoGateway).deleteById(Mockito.eq(expectedId));

        Assertions.assertThrows(IllegalStateException.class, () -> useCase.execute(expectedId.getValue()));

        Mockito.verify(departamentoGateway, Mockito.times(1)).deleteById(expectedId);
    }
}