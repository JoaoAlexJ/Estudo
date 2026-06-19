package projeto.app;

import projeto.Menu.FluxoMenus;
import projeto.Menu.ProdutoMenu;
import projeto.Menu.UsuarioMenu;
import projeto.auth.AuthService;
import projeto.auth.Sessao;
import projeto.printers.Printer;
import projeto.repositorios.BancoRepository;
import projeto.repositorios.ProdutoRepository;
import projeto.repositorios.UsuarioRepository;
import projeto.services.ProdutoService;
import projeto.services.UsuarioService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Teste
        Printer printer = new Printer();
        Scanner scanner = new Scanner(System.in);

        ProdutoRepository produtoRepository = new ProdutoRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        BancoRepository bancoRepository = new BancoRepository();

        ProdutoService produtoService = new ProdutoService(produtoRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        AuthService authService = new AuthService(usuarioService, scanner);

        FluxoMenus fluxoMenus = new FluxoMenus(scanner, authService, usuarioService, produtoService, bancoRepository);
        ProdutoMenu produtoMenu = new ProdutoMenu(produtoService, authService, scanner, printer);
        UsuarioMenu usuarioMenu = new UsuarioMenu(fluxoMenus, usuarioService, authService, printer, scanner);

        Dataloader dataloader = new Dataloader(produtoService, usuarioService, bancoRepository);


        dataloader.iniciar();


        //----------------//



            while (true) {

                // menu inicial


                System.out.println("========= Mercadão =========");
                System.out.println("1 - Fazer login");
                System.out.println("2 - Cadastrar uma conta");
                System.out.println("0 - sair");
                System.out.print("-> ");
                int choice = scanner.nextInt();

                scanner.nextLine();

                boolean executandoMenu1 = true;

                switch (choice) {

                    case 1:

                        // login

                        fluxoMenus.loginFluxo();

                        if (!Sessao.getUserLogado().getCargo().isAdmin()) {

                            // menu user comum


                            while (executandoMenu1) {

                                System.out.println("1 - Produtos");
                                System.out.println("2 - configurações da conta");
                                System.out.println("0 - sair");
                                System.out.print("-> ");
                                int choiceMenu = scanner.nextInt();

                                System.out.println("---------------------------");

                                scanner.nextLine();

                                switch (choiceMenu) {

                                    case 1:
                                        fluxoMenus.executarMenuProduto(produtoMenu);
                                        System.out.println("---------------------------");
                                        break;

                                    case 2:
                                        fluxoMenus.executarMenuUsuario(usuarioMenu);
                                        System.out.println("---------------------------");
                                        break;

                                    case 0:
                                        Sessao.logout();
                                        executandoMenu1 = false;
                                        break;

                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }

                            }

                        } else {

                            // menu adm

                            while (executandoMenu1) {

                                System.out.println("1 - Produtos");
                                System.out.println("2 - Usuarios");
                                System.out.println("0 - sair");
                                int choice1 = scanner.nextInt();

                                System.out.println("---------------------------");

                                scanner.nextLine();

                                switch (choice1) {

                                    case 1:
                                        fluxoMenus.executarMenuProduto(produtoMenu);
                                        break;

                                    case 2:
                                        fluxoMenus.executarMenuUsuario(usuarioMenu);
                                        break;

                                    case 0:
                                        Sessao.logout();
                                        executandoMenu1 = false;

                                        break;

                                    default:
                                        System.out.println("Opção inválida");
                                        break;

                                }


                            }

                        }

                     break;

                    case 2:
                        fluxoMenus.cadastrarUsuario();
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        return;

                    default:
                        System.out.println("Opção inválida");
                        break;

                }

            }





    }

}