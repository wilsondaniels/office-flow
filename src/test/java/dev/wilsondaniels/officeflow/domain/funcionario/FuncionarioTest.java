package dev.wilsondaniels.officeflow.domain.funcionario;

import dev.wilsondaniels.officeflow.domain.exception.DomainException;
import dev.wilsondaniels.officeflow.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FuncionarioTest {

    // Testes abaixo incluídos apenas para demostrar o uso básico do JUnit, a cobertura
    // deveria exercitar diversas outras condições

    @Test
    public void givenAValidParams_whenCallNewFuncionario_thenInstantiateAFuncionario() {

        final var expectedNome = "John Scofield";
        final var expectedEndereco = "Rua 11";
        final var expectedBairro = "Vila do Sul";
        final var expectedCep = "67890-423";
        final var expectedTelefone = "9876543210";
        final var expectedSalario = BigDecimal.valueOf(5000.00);
        final var expectedDataContrato = LocalDate.of(2024,11, 25);
        final var expectedFuncao = "Gerente de TI";

        final var actualFuncionario =
                Funcionario.newFuncionario(expectedNome, expectedEndereco, expectedBairro, expectedCep,
                        expectedTelefone, expectedSalario, expectedDataContrato, expectedFuncao);

        Assertions.assertNotNull(actualFuncionario);
        Assertions.assertNotNull(actualFuncionario.getId());
        Assertions.assertEquals(expectedNome, actualFuncionario.getNome());
        Assertions.assertEquals(expectedEndereco, actualFuncionario.getEndereco());
        Assertions.assertEquals(expectedBairro, actualFuncionario.getBairro());
        Assertions.assertEquals(expectedCep, actualFuncionario.getCep());
        Assertions.assertEquals(expectedTelefone, actualFuncionario.getTelefone());
        Assertions.assertEquals(expectedSalario, actualFuncionario.getSalario());
        Assertions.assertEquals(expectedDataContrato, actualFuncionario.getDataContrato());
        Assertions.assertEquals(expectedFuncao, actualFuncionario.getFuncao());
    }

    @Test
    public void givenAnInvalidNullNome_whenCallNewFuncionarioAndValidate_thenShouldReceiveError() {

        final String expectedNome = null;
        final var expectedEndereco = "Rua 11";
        final var expectedBairro = "Vila do Sul";
        final var expectedCep = "67890-423";
        final var expectedTelefone = "9876543210";
        final var expectedSalario = BigDecimal.valueOf(5000.00);
        final var expectedDataContrato = LocalDate.of(2024,11, 25);
        final var expectedFuncao = "Gerente de TI";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'nome' should not be null";

        final var actualFuncionario =
                Funcionario.newFuncionario(expectedNome, expectedEndereco, expectedBairro, expectedCep,
                        expectedTelefone, expectedSalario, expectedDataContrato, expectedFuncao);

        final var actualException =
                Assertions.assertThrows(DomainException.class,
                        () -> actualFuncionario.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyNome_whenCallNewFuncionarioAndValidate_thenShouldReceiveError() {

        final String expectedNome = " ";
        final var expectedEndereco = "Rua 11";
        final var expectedBairro = "Vila do Sul";
        final var expectedCep = "67890-423";
        final var expectedTelefone = "9876543210";
        final var expectedSalario = BigDecimal.valueOf(5000.00);
        final var expectedDataContrato = LocalDate.of(2024,11, 25);
        final var expectedFuncao = "Gerente de TI";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'nome' should not be empty";

        final var actualFuncionario =
                Funcionario.newFuncionario(expectedNome, expectedEndereco, expectedBairro, expectedCep,
                        expectedTelefone, expectedSalario, expectedDataContrato, expectedFuncao);

        final var actualException =
                Assertions.assertThrows(DomainException.class,
                        () -> actualFuncionario.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
}
