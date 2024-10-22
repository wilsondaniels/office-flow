package dev.wilsondaniels.officeflow.infrastructure.configuration.usecase;

import dev.wilsondaniels.officeflow.application.funcionariodepartamento.create.CreateFuncionarioDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.create.DefaultCreateFuncionarioDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.delete.DefaultDeleteFuncionarioDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.delete.DeleteFuncionarioDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.list.DefaultListFuncionarioDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.list.ListFuncionarioDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.total.DefaultGetTotalFuncionarioPorDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.funcionariodepartamento.retrieve.total.GetTotalFuncionarioPorDepartamentoUseCase;
import dev.wilsondaniels.officeflow.domain.funcionariodepartamento.FuncionarioDepartamentoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FuncionarioDepartamentoUseCaseConfig {

    private final FuncionarioDepartamentoGateway funcionarioDepartamentoGateway;

    public FuncionarioDepartamentoUseCaseConfig(final FuncionarioDepartamentoGateway funcionarioDepartamentoGateway) {
        this.funcionarioDepartamentoGateway = funcionarioDepartamentoGateway;
    }

    @Bean
    public CreateFuncionarioDepartamentoUseCase createFuncionarioDepartamentoUseCase() {
        return new DefaultCreateFuncionarioDepartamentoUseCase(funcionarioDepartamentoGateway);
    }

    @Bean
    public DeleteFuncionarioDepartamentoUseCase deleteFuncionarioDepartamentoUseCase() {
        return new DefaultDeleteFuncionarioDepartamentoUseCase(funcionarioDepartamentoGateway);
    }

    @Bean
    public ListFuncionarioDepartamentoUseCase listFuncionarioDepartamentoUseCase() {
        return new DefaultListFuncionarioDepartamentoUseCase(funcionarioDepartamentoGateway);
    }

    @Bean
    public GetTotalFuncionarioPorDepartamentoUseCase getTotalFuncionarioPorDepartamentoUseCase() {
        return new DefaultGetTotalFuncionarioPorDepartamentoUseCase(funcionarioDepartamentoGateway);
    }
}
