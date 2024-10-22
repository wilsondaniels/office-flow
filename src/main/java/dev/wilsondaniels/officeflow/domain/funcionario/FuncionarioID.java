package dev.wilsondaniels.officeflow.domain.funcionario;

import dev.wilsondaniels.officeflow.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class FuncionarioID extends Identifier {
    private final String value;

    private FuncionarioID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static FuncionarioID unique() {
        return FuncionarioID.from(UUID.randomUUID());
    }

    public static FuncionarioID from(final String anId) {
        return new FuncionarioID(anId);
    }

    public static FuncionarioID from(final UUID anId) {
        return new FuncionarioID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FuncionarioID that = (FuncionarioID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
