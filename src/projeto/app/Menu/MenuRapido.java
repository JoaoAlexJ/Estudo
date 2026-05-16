package projeto.app.Menu;

import projeto.auth.Sessao;
import projeto.entidades.Cargo;
import projeto.utils.SenhaUtil;

public class MenuRapido {

    public void mostrarMenuProduto(Cargo cargo){

        System.out.println("1 - Buscar produto");
        System.out.println("2 - Listar produtos");

        if (cargo == Cargo.ADM){

            System.out.println("3 - Cadastrar produto");
            System.out.println("4 - Deletar produto");
            System.out.println("-----------------");
            System.out.println("5 - Alterar descrição");
            System.out.println("6 - Alterar preço");
            System.out.println("7 - Alterar estoque");
        }

        System.out.println("0 - sair");
        System.out.print("-> ");


    }

    public void mostrarMenuUsuario(Cargo cargo){

        if (cargo == Cargo.COMUM){
            System.out.println("1 - alterar nome");
            System.out.println("2 - alterar e-mail");
            System.out.println("3 - alterar senha");
            System.out.println("4 - vizualizar dados da conta");
        }
        else {

            System.out.println("--------------------");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Buscar usuário");
            System.out.println("3 - listar usuários");
            System.out.println("4 - Atualizar usuario");
            System.out.println("5 - Deletar usuário");
            System.out.println("--------------------");

        }

        System.out.println("0 - Sair");

    }

}
