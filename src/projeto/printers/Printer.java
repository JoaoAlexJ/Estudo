package projeto.printers;

import projeto.entidades.*;
import projeto.services.VendaService;

public class Printer {

    public  void printVenda(Venda venda){
        System.out.println("========== Venda ==========");
        System.out.println("ID da venda: "+venda.getId());
        System.out.println("Comprador: "+venda.getComprador().getEmail());
        System.out.println("Data: "+venda.getData());

        for (ObjDeCompra o : venda.getCarrinho().getProdutos()){
            printObjCompra(o);
        }
        System.out.println("------------------------------------");
        System.out.printf("Valor da venda: %.2f\n", venda.getValor());


    }


    public void printCarrinho(Carrinho carrinho){

        System.out.println("===== Carrinho de compra ========");
       for (ObjDeCompra o : carrinho.getProdutos()){
           printObjCompra(o);
       }

        System.out.println("-------------------------------");
        System.out.printf("Valor Total: %.2f\n", carrinho.calcularValorTotal());

    }

    public void printObjCompra(ObjDeCompra compra){
        System.out.println("-------------------------------");
        System.out.println("ID do produto: "+compra.getId());
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
