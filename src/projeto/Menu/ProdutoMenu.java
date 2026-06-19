package projeto.Menu;

import projeto.auth.AuthService;
import projeto.auth.Sessao;
import projeto.entidades.Cargo;
import projeto.entidades.Produto;
import projeto.exception.NegocioException;
import projeto.printers.Printer;
import projeto.services.ProdutoService;

import java.util.Scanner;
import java.util.UUID;

public class ProdutoMenu {

    private ProdutoService produtoService;
    private AuthService authService;
    private Printer printer;
    private Scanner scanner;

    public ProdutoMenu(ProdutoService produtoService, AuthService authService, Scanner scanner, Printer printer) {
        if (produtoService == null)throw new NegocioException("Produto Service inválido");
        if (authService == null)throw new NegocioException("Auth service inválido");
        if (scanner == null)throw new NegocioException("Scanner inválido");
        if (printer == null)throw new NegocioException("Printer inválido");


        this.produtoService = produtoService;
        this.authService = authService;
        this.scanner = scanner;
        this.printer = printer;
    }

    public MenuAcao iniciar(){

        System.out.println("1 - Buscar produto");
        System.out.println("2 - Listar produtos");

        if (Sessao.getUserLogado().getCargo() == Cargo.ADM){

            System.out.println("3 - Cadastrar produto");
            System.out.println("4 - Deletar produto");
            System.out.println("-----------------");
            System.out.println("5 - Alterar descrição");
            System.out.println("6 - Alterar preço");
            System.out.println("7 - Alterar estoque");
        }

            System.out.println("0 - sair");
            System.out.print("-> ");

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

                    if (!authService.validarPermissao()){
                        System.out.println("Opção inválida");
                        return MenuAcao.CONTINUAR;
                    }else {

                        System.out.println("Informe a descrição do produto: ");
                        String descricao = scanner.nextLine();

                        System.out.println("Informe o preço do produto");
                        double preco = scanner.nextDouble();

                        System.out.println("Informe o estoque do produto: ");
                        int estoque = scanner.nextInt();

                        produtoService.cadastrar(descricao, preco, estoque);
                        System.out.println("Produto cadastrado com sucesso!");
                        System.out.println("---------------------------");

                        return MenuAcao.CONTINUAR;
                    }

                case 4:

                    if (!authService.validarPermissao()) {
                        System.out.println("Opção inválida");
                        return MenuAcao.CONTINUAR;
                    }else {
                        System.out.println("Informe o ID do produto");
                        String idDel = scanner.nextLine();

                        produtoService.delete(UUID.fromString(idDel));
                        System.out.println("Produto deletado com sucesso!");
                        System.out.println("---------------------------");

                        return MenuAcao.CONTINUAR;
                    }

                case 5:

                    if (!authService.validarPermissao()) {
                        System.out.println("Opção inválida");
                        return MenuAcao.CONTINUAR;
                    }else {
                        System.out.println("Infome o ID do produto que deseja alterar: ");
                        String idAlterDesc = scanner.nextLine();

                        System.out.println("Informe a nova descrição: ");
                        String descricao = scanner.nextLine();

                        produtoService.alterarDescricao(UUID.fromString(idAlterDesc), descricao);
                        System.out.println("Informação alterada com sucesso!");

                        printer.printProduto(produtoService.buscar(UUID.fromString(idAlterDesc)));
                        System.out.println("---------------------------");
                        return MenuAcao.CONTINUAR;

                    }

                case 6:

                    if (!authService.validarPermissao()) {
                        System.out.println("Opção inválida");
                        return MenuAcao.CONTINUAR;
                    }else {

                        System.out.println("Infome o ID do produto que deseja alterar: ");
                        String idAlterPreco = scanner.nextLine();

                        System.out.println("Informe o novo preço: ");
                        double preco = scanner.nextDouble();

                        produtoService.alterarPreco(UUID.fromString(idAlterPreco), preco);
                        System.out.println("Informação alterada com sucesso!");

                        printer.printProduto(produtoService.buscar(UUID.fromString(idAlterPreco)));
                        System.out.println("---------------------------");

                        return MenuAcao.CONTINUAR;

                    }

                case 7:

                    if (!authService.validarPermissao()) {
                        System.out.println("Opção inválida");
                        return MenuAcao.CONTINUAR;
                    }else {

                        System.out.println("Infome o ID do produto que deseja alterar: ");
                        String idAlterEstoque = scanner.nextLine();

                        System.out.println("Informe o novo estoque: ");
                        int estoque = scanner.nextInt();

                        produtoService.alterarEstoque(UUID.fromString(idAlterEstoque), estoque);
                        System.out.println("Informação alterada com sucesso!");

                        printer.printProduto(produtoService.buscar(UUID.fromString(idAlterEstoque)));
                        System.out.println("---------------------------");

                        return MenuAcao.CONTINUAR;

                    }

                case  0:
                    System.out.println("saindo...");
                    System.out.println("---------------------------");
                    return MenuAcao.SAIR;


                default:
                    System.out.println("Opção inválida");
                    return MenuAcao.CONTINUAR;
            }




    }

}
