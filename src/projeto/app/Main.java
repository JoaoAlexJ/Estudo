package projeto.app;

import projeto.app.Menu.MenuAcao;
import projeto.app.Menu.MenuRapido;
import projeto.app.Menu.ProdutoMenu;
import projeto.app.Menu.UsuarioMenu;
import projeto.auth.AuthService;
import projeto.auth.Sessao;
import projeto.entidades.Cargo;
import projeto.entidades.Mercado;
import projeto.printers.Printer;
import projeto.repositorios.ProdutoRepository;
import projeto.repositorios.UsuarioRepository;
import projeto.services.ProdutoService;
import projeto.services.UsuarioService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Teste

        ProdutoRepository produtoRepository = new ProdutoRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();

        ProdutoService produtoService = new ProdutoService(produtoRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        AuthService authService = new AuthService(usuarioService);

        Printer printer = new Printer();
        Scanner scanner = new Scanner(System.in);

        MenuRapido menuRapido = new MenuRapido();
        ProdutoMenu produtoMenu = new ProdutoMenu(menuRapido, produtoService,scanner, printer);
        UsuarioMenu usuarioMenu = new UsuarioMenu(menuRapido, usuarioService, printer, scanner);

        Dataloader dataloader = new Dataloader(produtoService, usuarioService);
        Mercado mercado = new Mercado(produtoService, usuarioService, authService);

        dataloader.iniciar();


        //----------------//



            while (true) {

                System.out.println("1 - Fazer login");
                System.out.println("2 - Cadastrar uma conta");
                System.out.println("0 - sair");
                System.out.print("-> ");
                int choice = scanner.nextInt();

                scanner.nextLine();

                boolean executandoMenu1 = true;

                switch (choice) {

                    case 1:
                        System.out.print("Informe o seu email: \n");
                        String email = scanner.nextLine();

                        System.out.print("Informe sua senha: \n");
                        String senha = scanner.nextLine();

                        mercado.login(email, senha);
                        System.out.println("Login efetuado com sucesso!");
                        System.out.println("---------------------------");

                        if (!Sessao.getUserLogado().getCargo().isAdmin()) {

                            while (executandoMenu1) {

                                System.out.println("1 - Produtos");
                                System.out.println("2 - configurações da conta: ");
                                System.out.println("0 - sair");
                                System.out.print("-> ");
                                int choiceMenu = scanner.nextInt();

                                System.out.println("---------------------------");

                                scanner.nextLine();

                                switch (choiceMenu) {

                                    case 1:
                                        executarMenuProduto(produtoMenu);
                                        System.out.println("---------------------------");
                                        break;

                                    case 2:
                                        executarMenuUsuario(usuarioMenu);
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

                            while (executandoMenu1) {

                                System.out.println("1 - Produtos");
                                System.out.println("2 - Usuarios");
                                System.out.println("0 - sair");
                                int choice1 = scanner.nextInt();

                                scanner.nextLine();

                                switch (choice1) {

                                    case 1:
                                        executarMenuProduto(produtoMenu);
                                        break;

                                    case 2:
                                        executarMenuUsuario(usuarioMenu);
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

                    case 0:
                        System.out.println("Saindo...");
                        return;
                }

            }

    }
        public static void executarMenuProduto(ProdutoMenu produtoMenu){

            while (true){

                MenuAcao acao = produtoMenu.iniciar();

                if (acao == MenuAcao.SAIR)break;

            }


        }

    public static void executarMenuUsuario(UsuarioMenu usuarioMenu){

        while (true){

            MenuAcao acao = usuarioMenu.iniciar();

            if (acao == MenuAcao.SAIR)break;

        }


    }

    }