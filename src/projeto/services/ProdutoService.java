package projeto.services;

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

    public void cadastrar(String descricao, double preco, int estoque){
        Produto p = new Produto(descricao, preco, estoque);
        produtoRepository.salvar(p);
    }

    public Produto buscar(UUID id){
        return produtoRepository.buscarPorID(id);
    }

    public List<Produto> listar (){
        return produtoRepository.listar();
    }

    public void delete(UUID id){
        produtoRepository.deletarPorId(id);
    }

    //------------------//

    public void alterarPreco(UUID id, double preco){
        buscar(id).setPreco(preco);
    }

    public void alterarDescricao(UUID id, String descricao){
        buscar(id).setDescricao(descricao);
    }

    public void alterarEstoque(UUID id, int estoque){
        buscar(id).setEstoque(estoque);
    }
}
