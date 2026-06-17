package projeto.repositorios;

import projeto.entidades.entidadeDeDominio.Banco;
import projeto.exception.NegocioException;

import java.util.HashMap;
import java.util.Map;

public class BancoRepository {

    private Map<String, Banco> bancos = new HashMap<>();

    public void adicionarBanco(Banco banco){
        if (bancos.containsKey(banco.getNome()))throw new NegocioException("Banco já cadastrado");

        bancos.put(banco.getNome(), banco);

    }

    public Banco buscarBanco(String nome){
        return bancos.get(nome);
    }

}
