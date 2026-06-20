package projeto.services;

import projeto.entidades.Categoria;
import projeto.entidades.Produto;
import projeto.exception.NegocioException;
import projeto.repositorios.ProdutoRepository;

import java.util.List;
import java.util.UUID;

public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        if (produtoRepository == null){
            throw new NegocioException("Produto Repository Nulo");
        }

        this.produtoRepository = produtoRepository;
    }

    //--------------------------//






    public void cadastrar(Categoria categoria, String descricao, double preco, int estoque){
        Produto p = new Produto(categoria, descricao, preco, estoque);
        produtoRepository.salvar(p);
    }

    public Produto buscar(UUID id){
        return produtoRepository.buscarPorID(id);
    }

    public Produto buscarPorDescricao(String descricao){
        return listar().stream()
                .filter(p -> p.getDescricao().equalsIgnoreCase(descricao))
                .findFirst()
                .orElseThrow(() -> new NegocioException("Produto não encontrado"));
    }

    public List<Produto> listarPorCategoria(Categoria categoria){

        if (categoria == null)throw new NegocioException("Categoria inválida");

        return listar().stream()
                .filter(p -> p.getCategoria().equals(categoria))
                .toList();

    }

    public List<Produto> listar (){
        return produtoRepository.listar();
    }

    public void delete(UUID id){
        produtoRepository.deletarPorId(id);
    }

    //------------------//

    public void alterarCategoria(UUID id, Categoria categoria){buscar(id).setCategoria(categoria);}

    public void alterarPreco(UUID id, double preco){
        buscar(id).setPreco(preco);
    }

    public void alterarDescricao(UUID id, String descricao){
        buscar(id).setDescricao(descricao);
    }

    public void alterarEstoque(UUID id, int estoque){
        buscar(id).setEstoque(estoque);
    }

    //-------------------//

    public void aumentarEstoque(UUID id, int valor){
        buscar(id).aumentarEstoque(valor);
    }

    public void diminuirEstoque(UUID id, int valor){
        buscar(id).diminuirEstoque(valor);
    }
}
