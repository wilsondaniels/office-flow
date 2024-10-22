package dev.wilsondaniels.officeflow.domain.funcionario;

import dev.wilsondaniels.officeflow.domain.AggregateRoot;
import dev.wilsondaniels.officeflow.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends AggregateRoot<FuncionarioID> implements Cloneable {

    private String nome;
    private String endereco;
    private String bairro;
    private String cep;
    private String telefone;
    private BigDecimal salario;
    private LocalDate dataContrato;
    private String funcao;

    private Funcionario(
            final FuncionarioID id,
            final String nome,
            final String endereco,
            final String bairro,
            final String cep,
            final String telefone,
            final BigDecimal salario,
            final LocalDate dataContrato,
            final String funcao
    ) {
        super(id);
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone = telefone;
        this.salario = salario;
        this.dataContrato = dataContrato;
        this.funcao = funcao;
    }

    public static Funcionario newFuncionario(final String nome,
                                              final String endereco,
                                              final String bairro,
                                              final String cep,
                                              final String telefone,
                                              final BigDecimal salario,
                                              final LocalDate dataContrato,
                                              final String funcao) {
        final var id = FuncionarioID.unique();
        return new Funcionario(id, nome, endereco, bairro, cep, telefone, salario, dataContrato, funcao);
    }

    public static Funcionario with(
            final FuncionarioID id,
            final String nome,
            final String endereco,
            final String bairro,
            final String cep,
            final String telefone,
            final BigDecimal salario,
            final LocalDate dataContrato,
            final String funcao
    ) {
        return new Funcionario(id, nome, endereco, bairro, cep,
                telefone, salario, dataContrato, funcao
        );
    }

    public static Funcionario with(final Funcionario funcionario) {
        return with(funcionario.getId(), funcionario.nome, funcionario.endereco,
                funcionario.bairro, funcionario.cep, funcionario.telefone,
                funcionario.salario, funcionario.dataContrato, funcionario.funcao);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new FuncionarioValidator(this, handler).validate();
    }

    public Funcionario update(
            final String nome,
            final String endereco,
            final String bairro,
            final String cep,
            final String telefone,
            final BigDecimal salario,
            final LocalDate dataContrato,
            final String funcao
    ) {
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone = telefone;
        this.salario = salario;
        this.dataContrato = dataContrato;
        this.funcao = funcao;
        return this;
    }

    public FuncionarioID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public LocalDate getDataContrato() {
        return dataContrato;
    }

    public String getFuncao() {
        return funcao;
    }

    @Override
    public Funcionario clone() {
        try {
            return (Funcionario) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}