package projeto.services;

import projeto.utils.SenhaUtil;
import projeto.entidades.Cargo;
import projeto.entidades.Usuario;
import projeto.exception.NegocioException;
import projeto.repositorios.UsuarioRepository;

import java.util.List;
import java.util.UUID;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrar(String nome, String email, String  senha, Cargo cargo){

       usuarioRepository.listar().stream()
               .filter(u -> u.getEmail().equals(email))
               .findAny()
               .ifPresent(u -> {
                   throw new NegocioException("O e-mail já está em uso");
               });

       //-------//

        String senhaCriptografada = SenhaUtil.criptografar(senha);

        Usuario user = new Usuario(nome, email, senhaCriptografada, cargo);
        usuarioRepository.salvar(user);

    }

    public Usuario buscarPorId(UUID id){
        return usuarioRepository.buscarPorID(id);
    }

    public List<Usuario> listar (){
        return usuarioRepository.listar();
    }

    public void deletarPorId(UUID id){
        usuarioRepository.deletarPorId(id);
    }

    //-------------//

    public void alterarNome(UUID id, String nome){
        buscarPorId(id).setNome(nome);
    }
    public void alterarEmail(UUID id, String email){
        listar().stream()
                .filter(u -> u.getEmail().equals(email))
                .findAny()
                .ifPresent(u -> {
                    throw new NegocioException("O e-mail já está em uso");
                });

        buscarPorId(id).setEmail(email);
    }
    public void alterSenha(UUID id, String senhaNova){

        buscarPorId(id).setSenha(senhaNova);
    }
}
