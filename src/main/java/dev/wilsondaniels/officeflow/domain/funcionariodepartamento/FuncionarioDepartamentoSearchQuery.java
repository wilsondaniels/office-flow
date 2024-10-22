package dev.wilsondaniels.officeflow.domain.funcionariodepartamento;

public record FuncionarioDepartamentoSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
