package projeto.printers;

import projeto.entidades.Produto;
import projeto.entidades.Usuario;

public class Printer {

    public void printProduto(Produto p){

        System.out.println("=========================");
        System.out.println("ID: "+p.getId());
        System.out.println("Descrição: "+p.getDescricao());
        System.out.println("Categoria: "+p.getCategoria());
        System.out.printf("Preço: R$ %.2f\n", p.getPreco());
        System.out.println("Estoque: "+p.getEstoque());

    }

    public void printUsuario(Usuario u){

        System.out.println("=========================");
        System.out.println("ID: "+u.getId());
        System.out.println("Cargo:" +u.getCargo());
        System.out.println("Nome: "+u.getNome());
        System.out.println("Email: "+u.getEmail());


    }


}
