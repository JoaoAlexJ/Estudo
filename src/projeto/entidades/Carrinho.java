package projeto.entidades;

import projeto.exception.NegocioException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carrinho {

    private List<ObjDeCompra> carrinho = new ArrayList<>();

    public void adicionarObjCompra(ObjDeCompra objDeCompra){

        carrinho.add(objDeCompra);
    }

    public void removerObjCompra(ObjDeCompra objDeCompra){
        if (!carrinho.contains(objDeCompra))throw new NegocioException("Objeto de compra não localizado");

        carrinho.remove(objDeCompra);
    }

    public double calcularValorTotal(){

        double valorTotal = 0;

       for (ObjDeCompra o : carrinho){
           valorTotal += o.getPreco();

       }

       return valorTotal;
    }

    public void limparCarrinho(){

        carrinho.clear();
    }

    //------------------------//


    public List<ObjDeCompra> getProdutos() {
        return new ArrayList<>(carrinho);
    }
}
