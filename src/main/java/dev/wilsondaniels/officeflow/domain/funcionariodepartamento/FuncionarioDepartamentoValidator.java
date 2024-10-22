package dev.wilsondaniels.officeflow.domain.funcionariodepartamento;

import dev.wilsondaniels.officeflow.domain.validation.Error;
import dev.wilsondaniels.officeflow.domain.validation.ValidationHandler;
import dev.wilsondaniels.officeflow.domain.validation.Validator;

public class FuncionarioDepartamentoValidator extends Validator {

    private final FuncionarioDepartamento funcionarioDepartamento;

    public FuncionarioDepartamentoValidator(final FuncionarioDepartamento funcionarioDepartamento, final ValidationHandler aHandler) {
        super(aHandler);
        this.funcionarioDepartamento = funcionarioDepartamento;
    }

    @Override
    public void validate() {
        checkFuncionarioConstraints();
        checkDepartamentoConstraints();
    }

    private void checkFuncionarioConstraints() {
        final var funcionario = this.funcionarioDepartamento.getFuncionario();
        if (funcionario == null) {
            this.validationHandler().append(new Error("'funcionario' should not be null"));
            return;
        }
    }

    private void checkDepartamentoConstraints() {
        final var departamento = this.funcionarioDepartamento.getDepartamento();
        if (departamento == null) {
            this.validationHandler().append(new Error("'departamento' should not be null"));
            return;
        }
    }
}
