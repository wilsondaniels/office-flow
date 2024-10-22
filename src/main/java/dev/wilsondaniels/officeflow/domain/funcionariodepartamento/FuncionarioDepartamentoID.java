package dev.wilsondaniels.officeflow.domain.funcionariodepartamento;

import dev.wilsondaniels.officeflow.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class FuncionarioDepartamentoID extends Identifier {
    private final String value;

    private FuncionarioDepartamentoID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static FuncionarioDepartamentoID unique() {
        return FuncionarioDepartamentoID.from(UUID.randomUUID());
    }

    public static FuncionarioDepartamentoID from(final String anId) {
        return new FuncionarioDepartamentoID(anId);
    }

    public static FuncionarioDepartamentoID from(final UUID anId) {
        return new FuncionarioDepartamentoID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FuncionarioDepartamentoID that = (FuncionarioDepartamentoID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
