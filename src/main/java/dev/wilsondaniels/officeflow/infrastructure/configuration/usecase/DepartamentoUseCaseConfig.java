package dev.wilsondaniels.officeflow.infrastructure.configuration.usecase;

import dev.wilsondaniels.officeflow.application.departamento.create.CreateDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.create.DefaultCreateDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.delete.DefaultDeleteDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.delete.DeleteDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.retrieve.get.DefaultGetDepartamentoByIdUseCase;
import dev.wilsondaniels.officeflow.application.departamento.retrieve.get.GetDepartamentoByIdUseCase;
import dev.wilsondaniels.officeflow.application.departamento.retrieve.list.DefaultListDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.retrieve.list.ListDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.update.DefaultUpdateDepartamentoUseCase;
import dev.wilsondaniels.officeflow.application.departamento.update.UpdateDepartamentoUseCase;
import dev.wilsondaniels.officeflow.domain.departamento.DepartamentoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartamentoUseCaseConfig {

    private final DepartamentoGateway departamentoGateway;

    public DepartamentoUseCaseConfig(final DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = departamentoGateway;
    }

    @Bean
    public CreateDepartamentoUseCase createDepartamentoUseCase() {
        return new DefaultCreateDepartamentoUseCase(departamentoGateway);
    }

    @Bean
    public GetDepartamentoByIdUseCase getDepartamentoByIdUseCase() {
        return new DefaultGetDepartamentoByIdUseCase(departamentoGateway);
    }

    @Bean
    public UpdateDepartamentoUseCase updateDepartamentoUseCase() {
        return new DefaultUpdateDepartamentoUseCase(departamentoGateway);
    }

    @Bean
    public DeleteDepartamentoUseCase deleteDepartamentoUseCase() {
        return new DefaultDeleteDepartamentoUseCase(departamentoGateway);
    }

    @Bean
    public ListDepartamentoUseCase listDepartamentoUseCase() {
        return new DefaultListDepartamentoUseCase(departamentoGateway);
    }
}
