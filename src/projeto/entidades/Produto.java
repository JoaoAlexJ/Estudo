package projeto.entidades;

import java.util.UUID;

public class Produto {

    private UUID id;
    private String descricao;
    private double preco;
    private int estoque;

    public Produto(String descricao, double preco, int estoque) {
        if (descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException("Descrição inválida");
        }
        if (preco < 0){
            throw new IllegalArgumentException("Preço inválido");
        }
        if (estoque < 0){
            throw new IllegalArgumentException("Estoque inválido");
        }

        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    public void setId(UUID id) {
        if (this.id != null){
            throw new RuntimeException("ID já cadastrado");
        }

        this.id = id;
    }

    //---------------------------//

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    //-----------------//

    public UUID getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }
}
