package dev.wilsondaniels.officeflow.domain.departamento;

public record DepartamentoSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
