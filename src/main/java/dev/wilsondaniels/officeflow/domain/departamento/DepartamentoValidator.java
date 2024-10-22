package dev.wilsondaniels.officeflow.domain.departamento;

import dev.wilsondaniels.officeflow.domain.validation.Error;
import dev.wilsondaniels.officeflow.domain.validation.ValidationHandler;
import dev.wilsondaniels.officeflow.domain.validation.Validator;

public class DepartamentoValidator extends Validator {

    public static final int DEPARTAMENTO_MAX_LENGTH = 255;
    public static final int DEPARTAMENTO_MIN_LENGTH = 2;
    private final Departamento departamento;

    public DepartamentoValidator(final Departamento departamento, final ValidationHandler aHandler) {
        super(aHandler);
        this.departamento = departamento;
    }

    @Override
    public void validate() {
        checkDepartamentoConstraints();
        checkQtdeFuncionariosConstraints();
    }

    private void checkDepartamentoConstraints() {
        final var departamento = this.departamento.getDepartamento();
        if (departamento == null) {
            this.validationHandler().append(new Error("'departamento' should not be null"));
            return;
        }

        if (departamento.isBlank()) {
            this.validationHandler().append(new Error("'departamento' should not be empty"));
            return;
        }

        final int length = departamento.trim().length();
        if (length > DEPARTAMENTO_MAX_LENGTH || length < DEPARTAMENTO_MIN_LENGTH) {
            this.validationHandler().append(new Error("'departamento' must be between 2 and 255 characters"));
        }
    }

    private void checkQtdeFuncionariosConstraints() {
        final var qtdeFuncionarios = this.departamento.getQtdeFuncionarios();
        if (qtdeFuncionarios == null) {
            this.validationHandler().append(new Error("'qtdeFuncionarios' should not be null"));
            return;
        }
    }
}
