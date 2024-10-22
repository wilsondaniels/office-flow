package dev.wilsondaniels.officeflow.infrastructure.departamento.api;

import dev.wilsondaniels.officeflow.application.departamento.create.CreateDepartamentoOutput;
import dev.wilsondaniels.officeflow.application.departamento.create.CreateDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.delete.DeleteDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.retrieve.get.GetDepartamentoByIdUseCase;
import dev.wilsondaniels.officeflow.application.departamento.retrieve.list.ListDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.update.UpdateDepartamentoUseCase;
import dev.wilsondaniels.officeflow.infrastructure.ControllerTest;
import dev.wilsondaniels.officeflow.infrastructure.departamento.dto.DepartamentoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static io.vavr.API.Right;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ControllerTest(controllers = DepartamentoAPI.class)
public class DepartamentoAPITest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CreateDepartamentoUseCase createDepartamentoUseCase;

    @MockBean
    private GetDepartamentoByIdUseCase getDepartamentoByIdUseCase;

    @MockBean
    private UpdateDepartamentoUseCase updateDepartamentoUseCase;

    @MockBean
    private DeleteDepartamentoUseCase deleteDepartamentoUseCase;

    @MockBean
    private ListDepartamentoUseCase listDepartamentoUseCase;

    @Test
    public void givenAValidCommand_whenCallsCreateDepartamento_shouldReturnDepartamentoId() throws Exception {
        // given
        final var expectedDepartamento = "Novo Setor";
        final var expectedQtdeFuncionarios = 5;

        final var aInput = new DepartamentoDTO(null, expectedDepartamento, expectedQtdeFuncionarios);

        when(createDepartamentoUseCase.execute(any()))
                .thenReturn(Right(CreateDepartamentoOutput.from("123")));

        // when
        final var request = post("/departamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(aInput));

        final var response = this.mvc.perform(request)
                .andDo(print());

        // then
        response.andExpect(status().isCreated())
                .andExpect(header().string("Location", "/departamentos/123"))
                .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo("123")));

        verify(createDepartamentoUseCase, times(1)).execute(argThat(cmd ->
                Objects.equals(expectedDepartamento, cmd.departamento())
                        && Objects.equals(expectedQtdeFuncionarios, cmd.qtdeFuncionarios())
        ));
    }
}