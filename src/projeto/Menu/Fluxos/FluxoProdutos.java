package projeto.Menu.Fluxos;

import projeto.entidades.Categoria;
import projeto.exception.NegocioException;
import projeto.services.ProdutoService;

import java.security.PublicKey;
import java.util.Scanner;

public class FluxoProdutos {

    private Scanner scanner;
    private ProdutoService produtoService;

    public FluxoProdutos(Scanner scanner, ProdutoService produtoService) {
        if (scanner == null)throw new NegocioException("Scanner inválido");
        if (produtoService == null)throw new NegocioException("Produto Service inválido");

        this.scanner = scanner;
        this.produtoService = produtoService;
    }

    //----------------------//

    public int solicitarEstoque(){
        System.out.println("Informe um estoque válido para o produto: ");
        return scanner.nextInt();
    }

    public double solicitarPreco(){
        System.out.println("Informe um valor válido para o produto:");
        return scanner.nextDouble();

    }

    public Categoria solicitarCategoria(){

        Categoria categoria = null;
        boolean execute = true;

        while (execute){

            System.out.println("===== Informe a categoria do produto =====");
            System.out.println("1 - Eletronico");
            System.out.println("2 - Alimento");
            System.out.println("3 - Higiene");
            System.out.println("4 - Mobília");
            System.out.println("5 - Decoração");
            System.out.println("6 - Pet");
            System.out.print("-> ");
            int num = scanner.nextInt();


            switch (num){

                case 1:
                    categoria = Categoria.ELETRONICO;
                    execute = false;
                    break;

                case 2:
                    categoria = Categoria.ALIMENTO;

                    execute = false;
                    break;

                case 3:
                    categoria = Categoria.HIGIENE;

                    execute = false;
                    break;

                case 4:
                    categoria = Categoria.MOBILIA;

                    execute = false;
                    break;

                case 5:
                    categoria = Categoria.DECORACAO;

                    execute = false;
                    break;

                case 6:
                    categoria = Categoria.PET;

                    execute = false;
                    break;


                default:
                    System.out.println("Opção inválida");
                    break;
            }

        }

        return categoria;

    }

    public String solicitarDescricao(){
        System.out.println("Informe uma descrição válida para o produto");
        return scanner.nextLine();
    }

    public void cadastrarProduto(){

        String descricao = solicitarDescricao();
        Categoria categoria = solicitarCategoria();
        double preco = solicitarPreco();
        int estoque = solicitarEstoque();

        produtoService.cadastrar(categoria, descricao, preco, estoque);
        System.out.println("Produto cadastrado com sucesso!");
        System.out.println("-----------------------------");

    }


}
