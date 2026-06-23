package projeto.Menu.Fluxos;

import projeto.Menu.MenuAcao;
import projeto.Menu.ProdutoMenu;
import projeto.Menu.UsuarioMenu;
import projeto.auth.AuthService;
import projeto.auth.Sessao;
import projeto.entidades.*;
import projeto.entidades.entidadeDeDominio.Banco;
import projeto.exception.NegocioException;
import projeto.printers.Printer;
import projeto.repositorios.BancoRepository;
import projeto.services.ProdutoService;
import projeto.services.UsuarioService;
import projeto.services.VendaService;

import java.util.Scanner;

public class FluxoMenus {

    private Scanner scanner;
    private FluxoVenda fluxoVenda;

    public FluxoMenus(Scanner scanner, FluxoVenda fluxoVenda) {

        if (scanner == null)throw new NegocioException("Scanner inválido");

        this.scanner = scanner;
        this.fluxoVenda = fluxoVenda;

    }

    public MenuAcao menuDecisao(){

        boolean execute = true;

        while (execute) {

            System.out.println("1- comprar");
            System.out.println("2- voltar");
            System.out.print("-> ");
            int menu = scanner.nextInt();

            scanner.nextLine();

            switch (menu){

                case 1:

                    execute = false;
                    return fluxoVenda.vendaFluxo();

                case 2:

                    execute = false;
                    System.out.println("----------------------");
                    return MenuAcao.CONTINUAR;

                default:

                    System.out.println("Opção inválida");
                    break;



            }
        }

        return null;
    }

    public MenuAcao menuDecisao2(Produto produto){

        boolean execute = true;

        while (execute) {

            System.out.println("1- comprar");
            System.out.println("2- voltar");
            System.out.print("-> ");
            int menu = scanner.nextInt();

            scanner.nextLine();

            switch (menu){

                case 1:

                    execute = false;
                    return fluxoVenda.vendaFluxo2(produto);

                case 2:

                    execute = false;
                    System.out.println("----------------------");
                    return MenuAcao.CONTINUAR;

                default:

                    System.out.println("Opção inválida");
                    break;



            }
        }

        return null;
    }


    public void executarMenuProduto(ProdutoMenu produtoMenu){

        while (true){

            MenuAcao acao = produtoMenu.iniciar();

            if (acao == MenuAcao.SAIR)break;

        }


    }

    public void executarMenuUsuario(UsuarioMenu usuarioMenu){

        while (true){

            MenuAcao acao = usuarioMenu.iniciar();

            if (acao == MenuAcao.SAIR)break;

        }


    }

}