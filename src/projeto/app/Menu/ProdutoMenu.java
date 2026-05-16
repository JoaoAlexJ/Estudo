package projeto.app.Menu;

import projeto.auth.Sessao;
import projeto.entidades.Produto;
import projeto.exception.NegocioException;
import projeto.printers.Printer;
import projeto.services.ProdutoService;

import java.util.Scanner;
import java.util.UUID;

public class ProdutoMenu {

    private MenuRapido menuRapido;
    private ProdutoService produtoService;
    private Printer printer;
    private Scanner scanner;

    public ProdutoMenu(MenuRapido menuRapido, ProdutoService produtoService, Scanner scanner, Printer printer) {
        if (produtoService == null)throw new NegocioException("Produto Service inválido");
        if (menuRapido == null)throw new NegocioException("Menu rapido inválido");
        if (scanner == null)throw new NegocioException("Scanner inválido");
        if (printer == null)throw new NegocioException("Printer inválido");

        this.menuRapido = menuRapido;
        this.produtoService = produtoService;
        this.scanner = scanner;
        this.printer = printer;
    }

    public MenuAcao iniciar(){

            menuRapido.mostrarMenuProduto(Sessao.getUserLogado().getCargo());
            int choice = scanner.nextInt();

            System.out.println("---------------------------");


        scanner.nextLine();

            switch (choice){
                
                case 1:
                    System.out.print("Informe o ID do produto: \n");
                    String id = scanner.nextLine();

                    printer.printProduto(produtoService.buscar(UUID.fromString(id)));
                    System.out.println("---------------------------");

                    return MenuAcao.CONTINUAR;

                case 2:
                    for (Produto p : produtoService.listar()){
                        printer.printProduto(p);

                    }
                    System.out.println("---------------------------");
                    return MenuAcao.CONTINUAR;

                case 3:

                    if (!Sessao.getUserLogado().getCargo().isAdmin()){
                        System.out.println("Opção inválida");
                        return MenuAcao.CONTINUAR;
                    }else {

                        System.out.println("Informe a descrição do produto: ");
                        String descricao = scanner.nextLine();

                        System.out.println("Informe o preço do produto");
                        double preco = scanner.nextInt();

                        System.out.println("Informe o estoque do produto: ");
                        int estoque = scanner.nextInt();

                        produtoService.cadastrar(descricao, preco, estoque);
                        System.out.println("Produto cadastrado com sucesso!");
                        System.out.println("-------------------------------");
                        return MenuAcao.CONTINUAR;
                    }

                case 4:

                    if (!Sessao.getUserLogado().getCargo().isAdmin()) {
                        System.out.println("Opção inválida");
                        return MenuAcao.CONTINUAR;
                    }else {
                        System.out.println("Informe o ID do produto");
                        String idDel = scanner.nextLine();

                        produtoService.delete(UUID.fromString(idDel));
                        System.out.println("Produto deletado com sucesso!");
                        System.out.println("-------------------------------");
                        return MenuAcao.CONTINUAR;
                    }

                case 5:

                    if (!Sessao.getUserLogado().getCargo().isAdmin()) {
                        System.out.println("Opção inválida");
                        return MenuAcao.CONTINUAR;
                    }else {
                        System.out.println("Infome o ID do produto que deseja alterar: ");
                        String idAlterDesc = scanner.nextLine();

                        System.out.println("Informe a nova descrição: ");
                        String descricao = scanner.nextLine();

                        produtoService.alterarDescricao(UUID.fromString(idAlterDesc), descricao);
                        System.out.println("Informação alterada com sucesso!");
                        System.out.println("-------------------------------");

                        printer.printProduto(produtoService.buscar(UUID.fromString(idAlterDesc)));
                        return MenuAcao.CONTINUAR;

                    }

                case 6:
                    if (!Sessao.getUserLogado().getCargo().isAdmin()) {
                        System.out.println("Opção inválida");
                        return MenuAcao.CONTINUAR;
                    }else {

                        System.out.println("Infome o ID do produto que deseja alterar: ");
                        String idAlterPreco = scanner.nextLine();

                        System.out.println("Informe o novo preço: ");
                        double preco = scanner.nextDouble();

                        produtoService.alterarPreco(UUID.fromString(idAlterPreco), preco);
                        System.out.println("Informação alterada com sucesso!");
                        System.out.println("-------------------------------");

                        printer.printProduto(produtoService.buscar(UUID.fromString(idAlterPreco)));
                        return MenuAcao.CONTINUAR;

                    }

                case 7:

                    if (!Sessao.getUserLogado().getCargo().isAdmin()) {
                        System.out.println("Opção inválida");
                        return MenuAcao.CONTINUAR;
                    }else {

                        System.out.println("Infome o ID do produto que deseja alterar: ");
                        String idAlterEstoque = scanner.nextLine();

                        System.out.println("Informe o novo estoque: ");
                        int estoque = scanner.nextInt();

                        produtoService.alterarEstoque(UUID.fromString(idAlterEstoque), estoque);
                        System.out.println("Informação alterada com sucesso!");
                        System.out.println("-------------------------------");

                        printer.printProduto(produtoService.buscar(UUID.fromString(idAlterEstoque)));
                        return MenuAcao.CONTINUAR;

                    }

                case  0:
                    System.out.println("saindo...");
                    return MenuAcao.SAIR;


                default:
                    System.out.println("Opção inválida");
                    return MenuAcao.CONTINUAR;
            }




    }

}
