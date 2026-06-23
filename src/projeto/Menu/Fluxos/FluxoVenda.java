package projeto.Menu.Fluxos;

import projeto.Menu.MenuAcao;
import projeto.auth.Sessao;
import projeto.entidades.ObjDeCompra;
import projeto.entidades.Produto;
import projeto.entidades.Usuario;
import projeto.entidades.Venda;
import projeto.exception.NegocioException;
import projeto.printers.Printer;
import projeto.services.ProdutoService;
import projeto.services.VendaService;

import java.util.Scanner;

public class FluxoVenda {

    private Scanner scanner;
    private Printer printer;

    private FluxoBanco fluxoBanco;
    private VendaService vendaService;
    private ProdutoService produtoService;

    public FluxoVenda(Scanner scanner, Printer printer, FluxoBanco fluxoBanco, VendaService vendaService, ProdutoService produtoService) {

        this.scanner = scanner;
        this.printer = printer;
        this.fluxoBanco = fluxoBanco;
        this.vendaService = vendaService;
        this.produtoService = produtoService;
    }

    public MenuAcao vendaFluxo(){

        Usuario user = Sessao.getUserLogado();

        System.out.println("Informe a descrição do produto que deseja comprar: ");
        String descricao = scanner.nextLine();
        Produto produto = produtoService.buscarPorDescricao(descricao);

        System.out.println("Informe a quantidade que deseja desse produto:");
        int quantidade = scanner.nextInt();

        if (quantidade > produto.getEstoque())throw new NegocioException("estoque insuficiente");

        ObjDeCompra objDeCompra = new ObjDeCompra(produto.getId(), produto.getCategoria(),
                produto.getDescricao(), produto.getPreco(), quantidade);



        user.getCarrinho().adicionarObjCompra(objDeCompra);
        printer.printCarrinho(user.getCarrinho());

        System.out.println("----------------------");


        boolean execute = true;

        while (execute) {


            System.out.println("1 - Finalizar pedido");
            System.out.println("2 - adicionar mais produtos ao carrinho");
            System.out.println("3 - voltar");
            System.out.print("-> ");
            int choiceCarrinho = scanner.nextInt();

            scanner.nextLine();

            switch (choiceCarrinho) {

                case 1:
                    finalizarVenda();
                    return MenuAcao.CONTINUAR;


                case 2:

                    execute = false;
                    System.out.println("----------------------");
                    return MenuAcao.CONTINUAR;

                case 3:
                    System.out.println("voltando...");
                    System.out.println("----------------------");
                    execute = false;
                    return MenuAcao.CONTINUAR;


                default:
                    System.out.println("Opção inválida");
                    return MenuAcao.CONTINUAR;


            }

        }


        return null;
    }

    public MenuAcao vendaFluxo2(Produto produto){

        Usuario user = Sessao.getUserLogado();

        System.out.println("Informe a quantidade que deseja desse produto:");
        int quantidade = scanner.nextInt();

        if (quantidade > produto.getEstoque())throw new NegocioException("estoque insuficiente");

        ObjDeCompra objDeCompra = new ObjDeCompra(produto.getId(), produto.getCategoria(),
                produto.getDescricao(), produto.getPreco(), quantidade);



        user.getCarrinho().adicionarObjCompra(objDeCompra);
        printer.printCarrinho(user.getCarrinho());

        System.out.println("----------------------");


        boolean execute = true;

        while (execute) {


            System.out.println("1 - Finalizar pedido");
            System.out.println("2 - adicionar mais produtos ao carrinho");
            System.out.println("3 - voltar");
            System.out.print("-> ");

            int choiceCarrinho = scanner.nextInt();

            scanner.nextLine();

            switch (choiceCarrinho) {

                case 1:
                    finalizarVenda();
                    System.out.println("----------------------");
                    return MenuAcao.CONTINUAR;


                case 2:

                    execute = false;
                    System.out.println("----------------------");
                    return MenuAcao.CONTINUAR;

                case 3:
                    System.out.println("voltando...");
                    System.out.println("----------------------");
                    execute = false;
                    return MenuAcao.CONTINUAR;


                default:
                    System.out.println("Opção inválida");
                    return MenuAcao.CONTINUAR;


            }

        }


        return null;
    }

    public void finalizarVenda(){
        Usuario user = Sessao.getUserLogado();

        printer.printCarrinho(user.getCarrinho());

        System.out.println("--------- Dados bancário ----------");
        System.out.println("Titular: " + user.getContaBancaria().getTitular());
        System.out.println("Numero da conta: " + user.getContaBancaria().getNumeroConta());

        fluxoBanco.validarSenhaBanco(user.getContaBancaria());

        Venda venda = vendaService.realizarVenda(user, user.getCarrinho());

        System.out.println("Pagamento aprovado!");
        System.out.printf("Valor: %.2f\n", user.getCarrinho().calcularValorTotal());
        System.out.println("------------------------------------");

        printer.printVenda(venda);
        System.out.println("------------------------------------");

        user.getCarrinho().limparCarrinho();
    }

}
