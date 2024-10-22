package dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.api;

import dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.dto.FuncionarioDepartamentoDTO;
import dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.dto.FuncionarioDepartamentoSimplesDTO;
import dev.wilsondaniels.officeflow.infrastructure.funcionariodepartamento.dto.TotalFuncionariosPorDepartamentoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "funcionarios-departamentos")
@Tag(name = "Funcionarios e Departamentos")
public interface FuncionarioDepartamentoAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new funcionario/departamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> create(@RequestBody FuncionarioDepartamentoDTO input);

    @DeleteMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Delete a funcionario/departamento by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionario deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Funcionario was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> deleteById(@PathVariable(name = "id") String id);

    // Conforme solicitado no retorno da lista não foi aplicado paginação, que o mais comum a ser feito
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all funcionarios/departamentos and also allows you to filter by their name and departamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    List<FuncionarioDepartamentoSimplesDTO> listByNomeFuncaoDepartamento(
            @RequestParam(name = "nome", required = false, defaultValue = "") final String nome,
            @RequestParam(name = "funcao", required = false, defaultValue = "") final String departamento);

    @GetMapping(
            value = "/quantidade-por-departamento",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List total of funcionarios by departamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    List<TotalFuncionariosPorDepartamentoDTO> listTotalFuncionariosByDepartamentoByNomeFuncaoDepartamento();
}