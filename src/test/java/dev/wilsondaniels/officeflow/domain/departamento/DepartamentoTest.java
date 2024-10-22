package dev.wilsondaniels.officeflow.domain.departamento;

import dev.wilsondaniels.officeflow.domain.exception.DomainException;
import dev.wilsondaniels.officeflow.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DepartamentoTest {

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit, a cobertura
    // deveria exercitar diversas outras condições

    @Test
    public void givenAValidParams_whenCallNewDepartamento_thenInstantiateADepartamento() {
        final var expectedDepartamento = "Atendimento ao Consumidor (SAC)";
        final var expectedQtdeFuncionarios = 15;

        final var actualDepartamento =
                Departamento.newDepartamento(expectedDepartamento, expectedQtdeFuncionarios);

        Assertions.assertNotNull(actualDepartamento);
        Assertions.assertNotNull(actualDepartamento.getId());
        Assertions.assertEquals(expectedDepartamento, actualDepartamento.getDepartamento());
        Assertions.assertEquals(expectedQtdeFuncionarios, actualDepartamento.getQtdeFuncionarios());
    }

    @Test
    public void givenAnInvalidNullDepartamento_whenCallNewDepartamentondValidate_thenShouldReceiveError() {
        final String expectedDepartamento = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'departamento' should not be null";
        final var expectedQtdeFuncionarios = 15;

        final var actualDepartamento =
                Departamento.newDepartamento(expectedDepartamento, expectedQtdeFuncionarios);

        final var actualException =
                Assertions.assertThrows(DomainException.class,
                        () -> actualDepartamento.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyDepartamento_whenCallNewDepartamentondValidate_thenShouldReceiveError() {
        final String expectedDepartamento = " ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'departamento' should not be empty";
        final var expectedQtdeFuncionarios = 15;

        final var actualDepartamento =
                Departamento.newDepartamento(expectedDepartamento, expectedQtdeFuncionarios);

        final var actualException =
                Assertions.assertThrows(DomainException.class,
                        () -> actualDepartamento.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
}
