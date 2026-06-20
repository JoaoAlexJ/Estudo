package projeto.repositorios;

import projeto.entidades.Venda;
import projeto.exception.NegocioException;

import java.util.*;

public class VendaRepository implements Repository<Venda>{

    Map<UUID, Venda> vendas = new HashMap<>();

    @Override
    public void salvar(Venda entidade) {
        vendas.put(entidade.getId(), entidade);
    }

    @Override
    public Venda buscarPorID(UUID id) {
        return Optional.ofNullable(vendas.get(id))
                .orElseThrow(() -> new NegocioException("Venda não localizada"));
    }

    @Override
    public List<Venda> listar() {
        return new ArrayList<>(vendas.values());
    }

    @Override
    public void deletarPorId(UUID id) {
        Optional.ofNullable(vendas.remove(id))
                .orElseThrow(() -> new NegocioException("Venda não localizada"));
    }
}
