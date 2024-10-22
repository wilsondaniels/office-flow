package dev.wilsondaniels.officeflow.infrastructure.configuration.usecase;

import dev.wilsondaniels.officeflow.application.funcionario.create.CreateFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.create.DefaultCreateFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.delete.DefaultDeleteFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.delete.DeleteFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.retrieve.get.DefaultGetFuncionarioByIdUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.retrieve.get.GetFuncionarioByIdUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.retrieve.list.DefaultListFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.retrieve.list.ListFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.retrieve.total.DefaultGetTotalFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.retrieve.total.GetTotalFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.update.DefaultUpdateFuncionarioUseCase;
import dev.wilsondaniels.officeflow.application.funcionario.update.UpdateFuncionarioUseCase;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FuncionarioUseCaseConfig {

    private final FuncionarioGateway funcionarioGateway;

    public FuncionarioUseCaseConfig(final FuncionarioGateway funcionarioGateway) {
        this.funcionarioGateway = funcionarioGateway;
    }

    @Bean
    public CreateFuncionarioUseCase createFuncionarioUseCase() {
        return new DefaultCreateFuncionarioUseCase(funcionarioGateway);
    }

    @Bean
    public GetFuncionarioByIdUseCase getFuncionarioByIdUseCase() {
        return new DefaultGetFuncionarioByIdUseCase(funcionarioGateway);
    }

    @Bean
    public UpdateFuncionarioUseCase updateFuncionarioUseCase() {
        return new DefaultUpdateFuncionarioUseCase(funcionarioGateway);
    }

    @Bean
    public DeleteFuncionarioUseCase deleteFuncionarioUseCase() {
        return new DefaultDeleteFuncionarioUseCase(funcionarioGateway);
    }

    @Bean
    public ListFuncionarioUseCase listFuncionarioUseCase() {
        return new DefaultListFuncionarioUseCase(funcionarioGateway);
    }

    @Bean
    public GetTotalFuncionarioUseCase getTotalFuncionarioUseCase() {
        return new DefaultGetTotalFuncionarioUseCase(funcionarioGateway);
    }
}
