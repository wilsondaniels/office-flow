package dev.wilsondaniels.officeflow.domain.departamento;

import dev.wilsondaniels.officeflow.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class DepartamentoID extends Identifier {
    private final String value;

    private DepartamentoID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static DepartamentoID unique() {
        return DepartamentoID.from(UUID.randomUUID());
    }

    public static DepartamentoID from(final String anId) {
        return new DepartamentoID(anId);
    }

    public static DepartamentoID from(final UUID anId) {
        return new DepartamentoID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DepartamentoID that = (DepartamentoID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
