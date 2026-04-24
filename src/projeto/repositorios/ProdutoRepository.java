package projeto.repositorios;

import projeto.entidades.Produto;
import projeto.exception.NegocioException;

import java.util.*;

public class ProdutoRepository implements Repository<Produto>{
    private Map<UUID, Produto> produtoMap = new HashMap<>();


    @Override
    public void salvar(Produto entidade) {

        entidade.setId(UUID.randomUUID());
        produtoMap.put(entidade.getId(), entidade);
    }

    @Override
    public Produto buscarPorID(UUID id) {
        return Optional.ofNullable(produtoMap.get(id))
                .orElseThrow(() -> new NegocioException("Produto não encontrado"));
    }

    @Override
    public List<Produto> listar() {
        return new ArrayList<>(produtoMap.values());
    }

    @Override
    public void deletarPorId(UUID id) {
        Optional.ofNullable(produtoMap.remove(id))
                .orElseThrow(() -> new NegocioException("Produto não encontrado"));
    }
}
