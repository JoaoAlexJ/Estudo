package projeto.entidades.entidadeDeDominio;

import projeto.entidades.ContaBancaria;
import projeto.exception.NegocioException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Banco {

    private UUID id;
    private String nome;
    private Map<String , ContaBancaria> contas;

    public Banco(String nome) {
        if (nome == null || nome.isBlank())throw new NegocioException("Nome inválido");

        this.id = UUID.randomUUID();
        this.nome = nome;
        this.contas = new HashMap<>();
    }

    public ContaBancaria findConta(String numeroConta){
        if (numeroConta == null || numeroConta.isBlank())throw new NegocioException("Numero da conta inválido");

        ContaBancaria conta = contas.get(numeroConta);

        if (conta == null)throw new NegocioException("Conta não encontrada");

        return conta;
    }

    public void adicionarConta(ContaBancaria contaBancaria){
        if (contas.containsKey(contaBancaria.getNumeroConta()))throw new NegocioException("Numero de conta já cadastrado");

        contas.put(contaBancaria.getNumeroConta(), contaBancaria);

    }

    public boolean login(String numeroConta, String senha){

        if (findConta(numeroConta).getSenha().equals(senha))return true; else return false;


    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Map<String, ContaBancaria> getContas() {
        return contas;
    }
}
