package dev.wilsondaniels.officeflow.infrastructure.departamento.api;

import dev.wilsondaniels.officeflow.infrastructure.departamento.dto.DepartamentoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "departamentos")
@Tag(name = "Departamentos")
public interface DepartamentoAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new departamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> create(@RequestBody DepartamentoDTO input);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a departamento by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departamento retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Departamento was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> getById(@PathVariable(name = "id") String id);

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a departamento by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departamento updated successfully"),
            @ApiResponse(responseCode = "404", description = "Departamento was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> updateById(@PathVariable(name = "id") String id, @RequestBody DepartamentoDTO input);

    @DeleteMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Delete a departamento by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Departamento deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Departamento was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> deleteById(@PathVariable(name = "id") String id);

    // Conforme solicitado no retorno da lista não foi aplicado paginação, que o mais comum a ser feito
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "List all departments and also allows you to filter by their name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    List<DepartamentoDTO> listByDepto(
            @RequestParam(name = "departamento", required = false, defaultValue = "") final String departamento);
}