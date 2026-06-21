package projeto.entidades;

import projeto.entidades.entidadeDeDominio.Banco;
import projeto.exception.NegocioException;

import java.util.UUID;

public class ContaBancaria {

    private UUID id;
    private Banco banco;
    private String titular;
    private String numeroConta;
    private String senha;
    private double saldo;

    public ContaBancaria(Banco banco, String titular, String numeroConta, String senha, double saldo) {

        if (banco == null)throw new NegocioException("Banco inválido");
        if (titular == null || titular.isBlank())throw new NegocioException("Titular inválido");
        if (numeroConta == null || numeroConta.isBlank())throw new NegocioException("Numero da conta inválido");
        if (senha == null || senha.isBlank())throw new NegocioException("Senha inválida");
        if (saldo < 0)throw new NegocioException("Saldo inválido");

        this.id = UUID.randomUUID();
        this.banco = banco;
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.senha = senha;
        this.saldo = saldo;
    }


    //--------- metodos saldo -----------//

    public double sacar(double valor){

        if (valor <= 0)throw new NegocioException("Valor inválido");
        if (valor > saldo)throw new NegocioException("Saldo insuficiente");

        saldo -= valor;

        return valor;
    }

    public void depositar(double valor){
        if (valor <= 0)throw new NegocioException("Valor inválido");

        saldo += valor;
    }

    public boolean validarSenha(String numeroConta, String senha){

        if (numeroConta.equals(this.numeroConta) && senha.equals(this.senha))return true; else return false;

    }

    public String getTitular() {
        return titular;
    }

    public UUID getId() {
        return id;
    }

    public Banco getBanco() {
        return banco;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public double getSaldo() {
        return saldo;
    }
}
