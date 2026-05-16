package projeto.auth;

import projeto.entidades.Usuario;
import projeto.exception.NegocioException;
import projeto.services.UsuarioService;
import projeto.utils.SenhaUtil;

import java.util.Optional;

public class AuthService {

    private UsuarioService usuarioService;

    public AuthService(UsuarioService usuarioService) {
        if (usuarioService == null){
            throw new NegocioException("Usuario service inválido");
        }

        this.usuarioService = usuarioService;
    }
    public void login(String email, String senha){
        Usuario user = usuarioService.listar().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new NegocioException("Usuario não encontrado"));

        if (SenhaUtil.comparar(senha, user.getSenha())){
            Sessao.logar(user);
        }else {
            throw new NegocioException("Login inválido");
        }
    }

    public void logout(){
        if (!Sessao.isLogado()) throw new NegocioException("nenhum Usuario está logado");

        Sessao.logout();
    }

    public boolean validarPermissao(){
        return Sessao.getUserLogado().getCargo().isAdmin();
    }


}
