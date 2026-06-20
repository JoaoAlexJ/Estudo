package projeto.printers;

import projeto.entidades.*;
import projeto.services.VendaService;

public class Printer {

    public  void printVenda(Venda venda){
        System.out.println("========== Venda ==========");
        System.out.println("ID: ");
        System.out.println("Comprador: ");
        printCarrinho(venda.getCarrinho());
        System.out.println("Data: "+venda.getData());



    }


    public void printCarrinho(Carrinho carrinho){

        System.out.println("===== Carrinho de compra ========");
       for (ObjDeCompra o : carrinho.getProdutos()){
           printObjCompra(o);
       }

        System.out.println("-------------------------------");
        System.out.println("Valor Total: "+carrinho.calcularValorTotal());

    }

    public void printObjCompra(ObjDeCompra compra){
        System.out.println("-------------------------------");
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
