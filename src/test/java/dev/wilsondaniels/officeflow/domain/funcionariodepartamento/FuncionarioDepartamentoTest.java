package dev.wilsondaniels.officeflow.domain.funcionariodepartamento;

import dev.wilsondaniels.officeflow.domain.departamento.Departamento;
import dev.wilsondaniels.officeflow.domain.exception.DomainException;
import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FuncionarioDepartamentoTest {

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit, a cobertura
    // deveria exercitar diversas outras condições

    @Test
    public void givenAValidParams_whenCallNewFuncionarioDepartamento_thenInstantiateAFuncionarioDepartamento() {

        final var expectedFuncionario =
                Funcionario.newFuncionario("John Scofield", "Rua 11", "Vila do Sul", "67890-423",
                        "9876543210", BigDecimal.valueOf(5000.00), LocalDate.of(2024,11, 25), "Gerente de TI");

        final var expectedDepartamento =
                Departamento.newDepartamento("Atendimento ao Consumidor (SAC)", 15);

        final var actualFuncionarioDepartamento =
                FuncionarioDepartamento.newFuncionarioDepartamento(expectedFuncionario, expectedDepartamento);

        Assertions.assertNotNull(actualFuncionarioDepartamento);
        Assertions.assertNotNull(actualFuncionarioDepartamento.getId());
        Assertions.assertEquals(expectedFuncionario, actualFuncionarioDepartamento.getFuncionario());
        Assertions.assertEquals(expectedDepartamento, actualFuncionarioDepartamento.getDepartamento());
    }

    @Test
    public void givenAnInvalidNullFuncionario_whenCallNewFuncionarioDepartamentoAndValidate_thenShouldReceiveError() {

        final Funcionario expectedFuncionario = null;

        final var expectedDepartamento =
                Departamento.newDepartamento("Atendimento ao Consumidor (SAC)", 15);

        final var actualFuncionarioDepartamento =
                FuncionarioDepartamento.newFuncionarioDepartamento(expectedFuncionario, expectedDepartamento);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'funcionario' should not be null";

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualFuncionarioDepartamento.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNullDepartamento_whenCallNewFuncionarioDepartamentoAndValidate_thenShouldReceiveError() {

        final var expectedFuncionario =
                Funcionario.newFuncionario("John Scofield", "Rua 11", "Vila do Sul", "67890-423",
                        "9876543210", BigDecimal.valueOf(5000.00), LocalDate.of(2024,11, 25), "Gerente de TI");

        final Departamento expectedDepartamento = null;

        final var actualFuncionarioDepartamento =
                FuncionarioDepartamento.newFuncionarioDepartamento(expectedFuncionario, expectedDepartamento);

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'departamento' should not be null";

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualFuncionarioDepartamento.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
}
