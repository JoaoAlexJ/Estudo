package projeto.services;

import projeto.entidades.Carrinho;
import projeto.entidades.ObjDeCompra;
import projeto.entidades.Usuario;
import projeto.entidades.Venda;
import projeto.exception.NegocioException;
import projeto.repositorios.VendaRepository;

import java.util.List;
import java.util.UUID;

public class VendaService {

    private VendaRepository vendaRepository;
    private  ProdutoService produtoService;

    public VendaService(VendaRepository vendaRepository, ProdutoService produtoService) {
       if (vendaRepository == null){
           throw new NegocioException("Repository inválido");
       }
       if (produtoService == null)throw new NegocioException("Produto service inválido");

        this.vendaRepository = vendaRepository;
    }

    //-------------------------------//


    public Venda realizarVenda(Usuario user, Carrinho carrinho){

        double valor = carrinho.calcularValorTotal();
        user.getContaBancaria().sacar(valor);

        for (ObjDeCompra o: carrinho.getProdutos()){
            produtoService.diminuirEstoque(o.getId(), o.getQuantidade());

        }

        Venda venda = new Venda(user, carrinho, valor);

        vendaRepository.salvar(venda);
        return venda;
    }

    public Venda buscarVenda(UUID id){
        return vendaRepository.buscarPorID(id);
    }

    public List<Venda> listarVendas(){
        return vendaRepository.listar();
    }

    public void deletarVenda(UUID id){
        vendaRepository.deletarPorId(id);
    }

    //----------------------------------//

    public List<Venda> listarVendasCliente(UUID id){

        return listarVendas().stream()
                .filter(v -> v.getComprador().getId().equals(id))
                .toList();
    }




}
