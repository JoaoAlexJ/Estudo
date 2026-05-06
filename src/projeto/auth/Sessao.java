package projeto.auth;

import projeto.entidades.Usuario;
import projeto.exception.NegocioException;

public class Sessao {

    private static Usuario userLogado;

    public static void logar(Usuario user){
        if (user == null || user.getId() == null){
            throw new NegocioException("usuario inválido");
        }

        userLogado = user;
    }

    public static void logout(){
        userLogado = null;
    }

    public static boolean isLogado(){
        return userLogado != null;
    }

    public static Usuario getUserLogado() {
        return userLogado;
    }
}
