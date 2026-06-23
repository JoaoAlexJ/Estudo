package projeto.Menu.Fluxos;

import projeto.Menu.ProdutoMenu;
import projeto.Menu.UsuarioMenu;
import projeto.auth.Sessao;

import java.util.Scanner;

public class FluxoPrincipal {

    private static final Scanner scanner = new Scanner(System.in);

    private static int menuSecundario(){

        System.out.println("========= Mercadão =========");
        System.out.println("1 - Fazer login");
        System.out.println("2 - Cadastrar uma conta");
        System.out.println("0 - sair");
        System.out.print("-> ");
        int choice = scanner.nextInt();

        scanner.nextLine();

        return choice;

    }


    public static void iniciar(FluxoUsuario fluxoUsuario, FluxoMenus fluxoMenus, ProdutoMenu produtoMenu,
                               UsuarioMenu usuarioMenu){

        while (true) {

           int choice = menuSecundario();

            boolean executandoMenu1 = true;

            switch (choice) {

                case 1:

                    // login

                    fluxoUsuario.loginFluxo();

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
                    fluxoUsuario.cadastrarUsuario();
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


