package projeto.entidades;

import projeto.exception.NegocioException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Venda {


    private UUID id;
    private Usuario comprador;
    private Carrinho carrinho;
    private double valor;
    private LocalDate data;

    public Venda(Usuario comprador, Carrinho carrinho, double valor) {
        if (comprador == null)throw new NegocioException("comprador inválido");
        if (carrinho.getProdutos().isEmpty())throw new NegocioException("Nenhum produto foi localizado");
        if (valor < 0)throw new NegocioException("Valor inválido");

        this.id = UUID.randomUUID();
        this.comprador = comprador;
        this.carrinho = carrinho;
        this.valor = valor;
        this.data = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }
}
