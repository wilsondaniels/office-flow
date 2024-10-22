package dev.wilsondaniels.officeflow.domain.funcionario;

import dev.wilsondaniels.officeflow.domain.validation.Error;
import dev.wilsondaniels.officeflow.domain.validation.ValidationHandler;
import dev.wilsondaniels.officeflow.domain.validation.Validator;

import java.math.BigDecimal;

public class FuncionarioValidator extends Validator {

    public static final int NOME_MAX_LENGTH = 255;
    public static final int NOME_MIN_LENGTH = 2;
    public static final int ENDERECO_MAX_LENGTH = 255;
    public static final int ENDERECO_MIN_LENGTH = 2;
    public static final int FUNCAO_MAX_LENGTH = 255;
    public static final int FUNCAO_MIN_LENGTH = 2;
    public static final int BAIRRO_MAX_LENGTH = 255;
    public static final int CEP_MAX_LENGTH = 255;
    public static final int TELEFONE_MAX_LENGTH = 10;
    private final Funcionario funcionario;

    public FuncionarioValidator(final Funcionario funcionario, final ValidationHandler aHandler) {
        super(aHandler);
        this.funcionario = funcionario;
    }

    @Override
    public void validate() {
        checkNomeConstraints();
        checkEnderecoConstraints();
        checkFuncaoConstraints();
        checkSalarioConstraints();
        checkDataContratoConstraints();
        checkBairroConstraints();
        checkCepConstraints();
        checkTelefoneConstraints();
    }

    private void checkNomeConstraints() {
        final var nome = this.funcionario.getNome();
        if (nome == null) {
            this.validationHandler().append(new Error("'nome' should not be null"));
            return;
        }

        if (nome.isBlank()) {
            this.validationHandler().append(new Error("'nome' should not be empty"));
            return;
        }

        final int length = nome.trim().length();
        if (length > NOME_MAX_LENGTH || length < NOME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'nome' must be between 2 and 255 characters"));
        }
    }

    private void checkEnderecoConstraints() {
        final var endereco = this.funcionario.getEndereco();
        if (endereco == null) {
            this.validationHandler().append(new Error("'endereco' should not be null"));
            return;
        }

        if (endereco.isBlank()) {
            this.validationHandler().append(new Error("'endereco' should not be empty"));
            return;
        }

        final int length = endereco.trim().length();
        if (length > ENDERECO_MAX_LENGTH || length < ENDERECO_MIN_LENGTH) {
            this.validationHandler().append(new Error("'endereco' must be between 2 and 255 characters"));
        }
    }

    private void checkFuncaoConstraints() {
        final var funcao = this.funcionario.getFuncao();
        if (funcao == null) {
            this.validationHandler().append(new Error("'funcao' should not be null"));
            return;
        }

        if (funcao.isBlank()) {
            this.validationHandler().append(new Error("'funcao' should not be empty"));
            return;
        }

        final int length = funcao.trim().length();
        if (length > FUNCAO_MAX_LENGTH || length < FUNCAO_MIN_LENGTH) {
            this.validationHandler().append(new Error("'funcao' must be between 2 and 255 characters"));
        }
    }

    private void checkSalarioConstraints() {
        final var salario = this.funcionario.getSalario();
        if (salario == null) {
            this.validationHandler().append(new Error("'salario' should not be null"));
            return;
        }

        if (salario.compareTo(BigDecimal.ZERO) <= 0) {
            this.validationHandler().append(new Error("'salario' should be more than zero"));
            return;
        }
    }

    private void checkDataContratoConstraints() {
        final var dataContrato = this.funcionario.getDataContrato();
        if (dataContrato == null) {
            this.validationHandler().append(new Error("'dataContrato' should not be null"));
            return;
        }
    }

    private void checkBairroConstraints() {
        final var bairro = this.funcionario.getBairro();

        if (bairro != null && !bairro.isBlank()) { // Campo não obrigatório, validar só se diferente de nulo ou vazio
            final int length = bairro.trim().length();
            if (length > BAIRRO_MAX_LENGTH) {
                this.validationHandler().append(new Error("'bairro' must not be longer than 255 characters"));
            }
        }
    }

    private void checkCepConstraints() {
        final var cep = this.funcionario.getCep();

        if (cep != null && !cep.isBlank()) { // Campo não obrigatório, validar só se diferente de nulo ou vazio
            final int length = cep.trim().length();
            if (length > CEP_MAX_LENGTH) {
                this.validationHandler().append(new Error("'CEP' must not be longer than 255 characters"));
            }
        }
    }

    private void checkTelefoneConstraints() {
        final var telefone = this.funcionario.getTelefone();

        if (telefone != null && !telefone.isBlank()) { // Campo não obrigatório, validar só se diferente de nulo ou vazio
            final int length = telefone.trim().length();
            if (length > TELEFONE_MAX_LENGTH) {
                this.validationHandler().append(new Error("'telefone' must not be longer than 10 characters"));
            }
        }
    }
}
