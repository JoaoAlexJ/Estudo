package projeto.repositorios;

import java.util.List;
import java.util.UUID;

public interface Repository <T>{

    void salvar (T entidade);
    T buscarPorID(UUID id);
    List<T> listar();
    void deletarPorId (UUID id);

}
