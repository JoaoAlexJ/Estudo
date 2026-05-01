package projeto.entidades;

import java.util.Objects;
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
        if (descricao == null || descricao.isBlank()){
            throw new RuntimeException("Descricao inválida");
        }

        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        if (preco < 0){
            throw new RuntimeException("Preço inválido");
        }

        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        if (estoque < 0){
            throw new RuntimeException("Estoque inválido");
        }

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

    //---------------------//


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof Produto)) return false;

        Produto produto = (Produto) o;

        if (id == null || produto.id == null) {
            return false;
        }

        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
