package projeto.repositorios;

import projeto.entidades.Usuario;
import projeto.exception.NegocioException;

import java.util.*;

public class UsuarioRepository implements Repository<Usuario>{

    private Map<UUID, Usuario> map = new HashMap<>();

    @Override
    public void salvar(Usuario entidade) {
        entidade.setId(UUID.randomUUID());
        map.put(entidade.getId(), entidade);

    }

    @Override
    public Usuario buscarPorID(UUID id) {
        return Optional.ofNullable(map.get(id))
                .orElseThrow(() -> new NegocioException("Usuario não encontrado"));
    }

    @Override
    public List<Usuario> listar() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void deletarPorId(UUID id) {
        Optional.ofNullable(map.remove(id))
                .orElseThrow(() -> new NegocioException("Usuario não encontrado"));
    }
}
