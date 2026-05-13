package projeto.app.Options;

import projeto.auth.Sessao;
import projeto.entidades.Cargo;
import projeto.entidades.Produto;
import projeto.exception.NegocioException;
import projeto.printers.Printer;
import projeto.services.ProdutoService;

import java.util.Scanner;
import java.util.UUID;

public class ProdutoOptions {

    private MenuRapido menuRapido;
    private ProdutoService produtoService;
    private Printer printer;
    private Scanner scanner;

    public ProdutoOptions(MenuRapido menuRapido, ProdutoService produtoService, Scanner scanner, Printer printer) {
        if (produtoService == null)throw new NegocioException("Produto Service inválido");
        if (menuRapido == null)throw new NegocioException("Menu rapido inválido");
        if (scanner == null)throw new NegocioException("Scanner inválido");
        if (printer == null)throw new NegocioException("Printer inválido");

        this.menuRapido = menuRapido;
        this.produtoService = produtoService;
        this.scanner = scanner;
        this.printer = printer;
    }

    public void iniciar(){

        if (Sessao.getUserLogado().getCargo() == Cargo.COMUM){

            menuRapido.produtoUser();
            int choice = scanner.nextInt();

            switch (choice){
                
                case 1:
                    System.out.print("Informe o ID do produto: \n");
                    String id = scanner.nextLine();

                    printer.printoProduto(produtoService.buscar(UUID.fromString(id)));
                    break;
                case 2:
                    for (Produto p : produtoService.listar()){
                        printer.printoProduto(p);

                        System.out.println("-------------------------");

                    }
                    break;
                case 3:

                    break;

                default:
                    System.out.println("Opção inválida");


            }

        }else {

            menuRapido.ProdutoAdm();
            int choice = scanner.nextInt();

            scanner.nextLine();

            switch (choice){

                case 1:
                    System.out.print("Informe o ID do produto: \n");
                    String id = scanner.nextLine();

                    printer.printoProduto(produtoService.buscar(UUID.fromString(id)));
                    break;

                case 2:

                    for (Produto p : produtoService.listar()){
                        printer.printoProduto(p);
                    }

                    System.out.println("-------------------------");
                    break;

                case 3:

                    System.out.print("Informe a descrição do produto: \n");
                    String descricao = scanner.nextLine();

                    System.out.print("Informe o preço do produto: \n");
                    double preco = scanner.nextInt();

                    System.out.print("Informe a quantidade em estoque: \n");
                    int estoque = scanner.nextInt();

                    produtoService.cadastrar(descricao, preco, estoque);
                    System.out.println("-------------------------------");
                    System.out.println("Produto cadastrado com sucesso!");
                    break;

                case 4:

                    System.out.print("Informe o ID do produto que deseja deletar: \n");
                    String idDel = scanner.nextLine();

                    produtoService.delete(UUID.fromString(idDel));

                    System.out.println("-------------------------------");
                    System.out.println("Produto deletado com sucesso!");
                    break;

                case 5:

                    System.out.print("Informe o ID do produto que deseja alterar: \n");
                    String idAlterarDesc = scanner.nextLine();

                    System.out.print("informe a nova descricao do produto: \n");
                    String descricaoNova = scanner.nextLine();

                    produtoService.alterarDescricao(UUID.fromString(idAlterarDesc), descricaoNova);

                    System.out.println("-------------------------------");
                    System.out.println("Alteração realizada com sucesso");
                    System.out.println("-------------------------------");

                    printer.printoProduto(produtoService.buscar(UUID.fromString(idAlterarDesc)));
                    break;

                case 6:

                    System.out.print("Informe o ID do produto que deseja alterar: \n");
                    String idAlterarPreco = scanner.nextLine();

                    System.out.print("informe o novo preço do produto: \n");
                    double novoPreco = scanner.nextDouble();

                    produtoService.alterarPreco(UUID.fromString(idAlterarPreco), novoPreco);

                    System.out.println("-------------------------------");
                    System.out.println("Alteração realizada com sucesso");
                    System.out.println("-------------------------------");

                    printer.printoProduto(produtoService.buscar(UUID.fromString(idAlterarPreco)));
                    break;

                case 7:

                    System.out.print("Informe o ID do produto que deseja alterar: \n");
                    String idAlterarEstoque = scanner.nextLine();

                    System.out.print("informe o novo estoque do produto: \n");
                    int novoEstoque = scanner.nextInt();

                    produtoService.alterarEstoque(UUID.fromString(idAlterarEstoque), novoEstoque);

                    System.out.println("-------------------------------");
                    System.out.println("Alteração realizada com sucesso");
                    System.out.println("-------------------------------");

                    printer.printoProduto(produtoService.buscar(UUID.fromString(idAlterarEstoque)));
                    break;

                case  8:
                    break;

                default:
                    System.out.println("opção inválida");
            }



        }

    }

}
