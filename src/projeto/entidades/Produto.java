package projeto.entidades;

import projeto.exception.NegocioException;

import java.util.Objects;
import java.util.UUID;

public class Produto {

    private UUID id;
    private Categoria categoria;
    private String descricao;
    private double preco;
    private int estoque;

    public Produto(Categoria categoria, String descricao, double preco, int estoque) {
        if (categoria == null)throw new NegocioException("Categoria inválida");

        if (descricao == null || descricao.isBlank()){
            throw new IllegalArgumentException("Descrição inválida");
        }
        if (preco < 0){
            throw new IllegalArgumentException("Preço inválido");
        }
        if (estoque < 0){
            throw new IllegalArgumentException("Estoque inválido");
        }

        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }


    public void aumentarEstoque(int valor){

        if (valor <= 0)throw new NegocioException("Valor inválido");

        estoque += valor;
    }

    public void diminuirEstoque(int valor){
        if (valor <= 0)throw new NegocioException("valor inválido");
        if (valor > estoque)throw new NegocioException("Valor indisponível");


        estoque -= valor;
    }


    //-------------------------//

    public void setId(UUID id) {
        if (this.id != null){
            throw new RuntimeException("ID já cadastrado");
        }

        this.id = id;
    }

    //---------------------------//


    public void setCategoria(Categoria categoria) {
        if (categoria == null)throw new NegocioException("Novo categoria inválida");
        if (categoria.equals(this.categoria))throw new NegocioException("A nova categoria precisa ser diferente da atual");

        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()){
            throw new RuntimeException("Descricao inválida");
        }
        if (descricao.equals(this.descricao))throw new NegocioException("A nova descrição precisa ser diferente da atual");

        this.descricao = descricao;
    }

    public void setPreco(double preco) {
        if (preco == this.preco)throw new NegocioException("O novo preço precisa ser diferente do atual");

        if (preco < 0){
            throw new RuntimeException("Preço inválido");
        }

        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        if (estoque == this.estoque)throw new NegocioException("O novo estoque precisa ser diferente do atual");

        if (estoque < 0){
            throw new RuntimeException("Estoque inválido");
        }

        this.estoque = estoque;
    }

    //-----------------//

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
