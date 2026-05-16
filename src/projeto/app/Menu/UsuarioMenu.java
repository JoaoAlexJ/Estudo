package projeto.app.Menu;

import projeto.auth.Sessao;
import projeto.entidades.Cargo;
import projeto.entidades.Usuario;
import projeto.exception.NegocioException;
import projeto.printers.Printer;
import projeto.services.UsuarioService;

import java.util.Scanner;
import java.util.UUID;

public class UsuarioMenu {

    private MenuRapido menuRapido;
    private UsuarioService usuarioService;
    private Printer printer;
    private Scanner scanner;

    public UsuarioMenu(MenuRapido menuRapido, UsuarioService usuarioService, Printer printer, Scanner scanner) {
        if (menuRapido == null)throw new NegocioException("MenuRapido inválido");
        if (usuarioService == null)throw new NegocioException("Usuario Service inválido");
        if (printer == null)throw new NegocioException("Printer inválido");
        if (scanner == null)throw new NegocioException("Scarnner inválido");

        this.menuRapido = menuRapido;
        this.usuarioService = usuarioService;
        this.printer = printer;
        this.scanner = scanner;
    }

    public MenuAcao iniciar(){

        menuRapido.mostrarMenuUsuario(Sessao.getUserLogado().getCargo());
        System.out.print("-> ");
        int choice = scanner.nextInt();

        scanner.nextLine();

        switch (choice){

            case 1:

                if (!Sessao.getUserLogado().getCargo().isAdmin()){

                    System.out.println("Informe o seu novo nome");
                    String nome = scanner.nextLine();

                    usuarioService.alterarNome(Sessao.getUserLogado().getId(), nome);
                    System.out.println("Nome alterado com sucesso!");
                    System.out.println("--------------------");

                    printer.printUsuario(Sessao.getUserLogado());
                    System.out.println("--------------------");
                    return MenuAcao.CONTINUAR;
                } else {

                    System.out.println("Informe o nome do novo usuário: ");
                    String nomeCadastro = scanner.nextLine();

                    System.out.println("Informe um e-mail válido: ");
                    String emailCadastro = scanner.nextLine();

                    System.out.println("Informe a senha do novo usuário: ");
                    String senha = scanner.nextLine();


                    usuarioService.cadastrar(nomeCadastro, emailCadastro, senha, Cargo.COMUM);
                    System.out.println("Usuario cadastrado com sucesso!");
                    System.out.println("--------------------");

                    printer.printUsuario(Sessao.getUserLogado());
                    System.out.println("--------------------");
                    return MenuAcao.CONTINUAR;

                }

            case 2:

                if (!Sessao.getUserLogado().getCargo().isAdmin()){

                    System.out.println("Informe o seu novo e-mail");
                    String email = scanner.nextLine();

                    usuarioService.alterarEmail(Sessao.getUserLogado().getId(), email);
                    System.out.println("E-mail alterado com sucesso!");

                    System.out.println("--------------------");
                    printer.printUsuario(Sessao.getUserLogado());
                    System.out.println("--------------------");
                    return MenuAcao.CONTINUAR;

                }
                else {

                    System.out.println("Informe o ID do usuário:");
                    String id = scanner.nextLine();

                    printer.printUsuario(usuarioService.buscarPorId(UUID.fromString(id)));
                    System.out.println("--------------------");

                    return MenuAcao.CONTINUAR;
                }


            case 3:

                if (!Sessao.getUserLogado().getCargo().isAdmin()){

                    System.out.println("Informe a nova senha: ");
                    String senha = scanner.nextLine();

                    usuarioService.alterSenha(Sessao.getUserLogado().getId(), senha);
                    System.out.println("Senha alterada com sucesso!");
                    System.out.println("--------------------");

                    printer.printUsuario(Sessao.getUserLogado());
                    System.out.println("--------------------");
                    return MenuAcao.CONTINUAR;

                }
                else {

                    for (Usuario u : usuarioService.listar()){
                        printer.printUsuario(u);
                    }

                    System.out.println("--------------------");
                    return MenuAcao.CONTINUAR;

                }

            case 4:

                if (!Sessao.getUserLogado().getCargo().isAdmin()){

                    printer.printUsuario(Sessao.getUserLogado());
                    System.out.println("---------------------------");

                    return MenuAcao.CONTINUAR;
                }else {

                    System.out.println("Informe o ID do usuario: ");
                    String id = scanner.nextLine();

                    Usuario user = usuarioService.buscarPorId(UUID.fromString(id));

                    printer.printUsuario(user);
                    System.out.println("--------------------");

                    System.out.println("Selecione qual dado deseja atualizar");
                    System.out.println("1 - nome");
                    System.out.println("2 - e-mail");
                    System.out.println("3 - Senha");
                    System.out.println("0 - voltar");

                    int choiceAlterar = scanner.nextInt();

                    scanner.nextLine();

                    switch (choiceAlterar){

                        case 1:

                            System.out.println("Informe o novo nome: ");
                            String nome = scanner.nextLine();

                            usuarioService.alterarNome(user.getId(), nome);
                            System.out.println("Nome Alterado com sucesso!");
                            System.out.println("--------------------");

                            printer.printUsuario(user);
                            System.out.println("--------------------");

                            return MenuAcao.CONTINUAR;

                        case 2:

                            System.out.println("Informe o novo e-mail: ");
                            String email = scanner.nextLine();

                            usuarioService.alterarEmail(user.getId(), email);
                            System.out.println("E-mail Alterado com sucesso!");
                            System.out.println("--------------------");

                            printer.printUsuario(user);
                            System.out.println("--------------------");

                            return MenuAcao.CONTINUAR;

                        case 3:

                            System.out.println("Informe a novo senha: ");
                            String senha = scanner.nextLine();

                            usuarioService.alterSenha(user.getId(), senha);
                            System.out.println("Senha Alterada com sucesso!");
                            System.out.println("--------------------");

                            printer.printUsuario(user);
                            System.out.println("--------------------");

                            return MenuAcao.CONTINUAR;

                        case 0:

                            System.out.println("Saindo...");
                            return MenuAcao.CONTINUAR;


                        default:
                            System.out.println("Opção inválida");
                            return MenuAcao.CONTINUAR;
                    }


                }

            case 5:

                if (!Sessao.getUserLogado().getCargo().isAdmin()){
                    System.out.println("Opção inválido");
                    return MenuAcao.CONTINUAR;
                }else{

                    System.out.println("Informe o ID do usuário");
                    String id = scanner.nextLine();

                    Usuario user = usuarioService.buscarPorId(UUID.fromString(id));
                    usuarioService.deletarPorId(user.getId());

                    System.out.println("O seguinte usuario foi deletado: ");
                    printer.printUsuario(user);
                    System.out.println("---------------------------------");
                    return MenuAcao.CONTINUAR;


                }

            case 0:
                System.out.println("Saindo...");
                return MenuAcao.SAIR;

            default:
                System.out.println("Opção inválida");
                return MenuAcao.CONTINUAR;

        }

    }
}
