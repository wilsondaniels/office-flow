package dev.wilsondaniels.officeflow.infrastructure.funcionario.persistence;

import dev.wilsondaniels.officeflow.domain.funcionario.Funcionario;
import dev.wilsondaniels.officeflow.domain.funcionario.FuncionarioID;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Funcionario")
public class FuncionarioJpaEntity {

    @Id
    @Column(name = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    private String bairro;
    private String cep;
    private String telefone;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal salario;

    @Column(name = "data_contrato", nullable = false)
    private LocalDate dataContrato;

    @Column(nullable = false)
    private String funcao;

    public FuncionarioJpaEntity() {
    }

    private FuncionarioJpaEntity(UUID id, String nome, String endereco, String bairro, String cep, String telefone, BigDecimal salario, LocalDate dataContrato, String funcao) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone = telefone;
        this.salario = salario;
        this.dataContrato = dataContrato;
        this.funcao = funcao;
    }

    public static FuncionarioJpaEntity from(final Funcionario funcionario) {
        return new FuncionarioJpaEntity(
                UUID.fromString(funcionario.getId().getValue()),
                funcionario.getNome(),
                funcionario.getEndereco(),
                funcionario.getBairro(),
                funcionario.getCep(),
                funcionario.getTelefone(),
                funcionario.getSalario(),
                funcionario.getDataContrato(),
                funcionario.getFuncao()
        );
    }

    public Funcionario toAggregate() {
        return Funcionario.with(
                FuncionarioID.from(id),
                nome,
                endereco,
                bairro,
                cep,
                telefone,
                salario,
                dataContrato,
                funcao
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(LocalDate dataContrato) {
        this.dataContrato = dataContrato;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuncionarioJpaEntity that = (FuncionarioJpaEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}