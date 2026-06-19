package projeto.entidades;

import projeto.exception.NegocioException;

import java.util.UUID;

public class ObjDeCompra {

    private UUID id;
    private Categoria categoria;
    private String descricao;
    private double preco;
    private int quantidade;

    public ObjDeCompra(UUID id, Categoria categoria, String descricao, double preco, int quantidade) {
        if (id == null)throw new NegocioException("ID inválido");

        if (categoria == null)throw new NegocioException("Categoria inválida");

        if (descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException("Descrição inválida");
        }
        if (preco < 0){
            throw new IllegalArgumentException("Preço inválido");
        }
        if (quantidade <= 0){
            throw new IllegalArgumentException("Estoque inválido");
        }

        this.id = id;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public UUID getId() {
        return id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
