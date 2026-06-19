package projeto.printers;

import projeto.entidades.ObjDeCompra;
import projeto.entidades.Produto;
import projeto.entidades.Usuario;

public class Printer {

    public void printCarrinho(Usuario user){

        System.out.println("===== Carrinho de compra ========");
        System.out.println("User: "+user.getNome());
        System.out.println(user.getEmail());
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");

        double valorTotal = 0;

        for(ObjDeCompra o : user.getCarrinho()){
            printObjCompra(o);

            valorTotal += o.getPreco();
            System.out.println("----------------------");
        }

        System.out.printf("Valor Total: R$ %.2f\n",valorTotal );
    }

    public void printObjCompra(ObjDeCompra compra){
        System.out.println("=========================");
        System.out.println("ID: "+compra.getId());
        System.out.println("Descrição: "+compra.getDescricao());
        System.out.println("Categoria: "+compra.getCategoria());
        System.out.printf("Preço: R$ %.2f\n", compra.getPreco());
        System.out.println("quantidade: "+compra.getQuantidade());

    }

    //----------------------//

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
