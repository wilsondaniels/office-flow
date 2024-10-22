package dev.wilsondaniels.officeflow.domain.funcionario;

public record FuncionarioSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
