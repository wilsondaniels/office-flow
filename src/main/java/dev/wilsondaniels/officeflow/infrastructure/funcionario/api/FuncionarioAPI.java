package dev.wilsondaniels.officeflow.infrastructure.funcionario.api;

import dev.wilsondaniels.officeflow.infrastructure.funcionario.dto.FuncionarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "funcionarios")
@Tag(name = "Funcionarios")
public interface FuncionarioAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new funcionario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> create(@RequestBody FuncionarioDTO input);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a funcionario by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionario retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Funcionario was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a funcionario by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionario updated successfully"),
            @ApiResponse(responseCode = "404", description = "Funcionario was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> updateById(@PathVariable(name = "id") String id, @RequestBody FuncionarioDTO input);

    @DeleteMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Delete a funcionario by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionario deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Funcionario was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> deleteById(@PathVariable(name = "id") String id);

    // Conforme solicitado no retorno da lista não foi aplicado paginação, que o mais comum a ser feito
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all funcionarios and also allows you to filter by their name and funcao")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    List<FuncionarioDTO> listByNomeFuncaoDepartamento(
            @RequestParam(name = "nome", required = false, defaultValue = "") final String nome,
            @RequestParam(name = "funcao", required = false, defaultValue = "") final String funcao);

    @GetMapping(
            value = "/total/{salarioMinimo}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "The total number of employees with salaries above a value salarioMinimo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Long countTotalNumberOfFuncionario(
            @PathVariable(name = "salarioMinimo") final double salarioMinimo);

    @GetMapping(
            value = "/ranking-por-salario",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all funcionarios and also allows you to filter by their name and funcao")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    List<FuncionarioDTO> listFuncionariosRankedBySalarioAsc();
}