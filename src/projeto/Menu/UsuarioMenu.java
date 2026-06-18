package projeto.Menu;

import projeto.auth.AuthService;
import projeto.auth.Sessao;
import projeto.entidades.Cargo;
import projeto.entidades.Usuario;
import projeto.exception.NegocioException;
import projeto.printers.Printer;
import projeto.services.UsuarioService;

import java.util.Scanner;
import java.util.UUID;

public class UsuarioMenu {

    private FluxoMenus fluxoMenus;
    private UsuarioService usuarioService;
    private AuthService authService;
    private Printer printer;
    private Scanner scanner;

    public UsuarioMenu(FluxoMenus fluxoMenus, UsuarioService usuarioService, AuthService authService, Printer printer, Scanner scanner) {
        if (fluxoMenus == null)throw new NegocioException("Fluxo menu inválido");
        if (usuarioService == null)throw new NegocioException("Usuario Service inválido");
        if (authService == null)throw new NegocioException("Auth Service inválido");
        if (printer == null)throw new NegocioException("Printer inválido");
        if (scanner == null)throw new NegocioException("Scarnner inválido");

        this.fluxoMenus = fluxoMenus;
        this.usuarioService = usuarioService;
        this.authService = authService;
        this.printer = printer;
        this.scanner = scanner;
    }

    public MenuAcao iniciar(){

        if (Sessao.getUserLogado().getCargo() == Cargo.COMUM){
            System.out.println("1 - alterar nome");
            System.out.println("2 - alterar e-mail");
            System.out.println("3 - alterar senha");
            System.out.println("4 - vizualizar dados da conta");
        }
        else {

            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Buscar usuário");
            System.out.println("3 - listar usuários");
            System.out.println("4 - Atualizar usuario");
            System.out.println("5 - Deletar usuário");

        }

        System.out.println("0 - Sair");
        System.out.print("-> ");

        int choice = scanner.nextInt();

        scanner.nextLine();

        switch (choice){

            case 1:

                if (!authService.validarPermissao()){

                    System.out.println("Informe o seu novo nome");
                    String nome = scanner.nextLine();

                    usuarioService.alterarNome(Sessao.getUserLogado().getId(), nome);
                    System.out.println("Nome alterado com sucesso!");
                    System.out.println("--------------------");

                    printer.printUsuario(Sessao.getUserLogado());
                    System.out.println("--------------------");
                    return MenuAcao.CONTINUAR;
                } else {

                    fluxoMenus.cadastrarUsuario();
                    System.out.println("--------------------");
                    return MenuAcao.CONTINUAR;

                }

            case 2:

                if (!authService.validarPermissao()){

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

                if (!authService.validarPermissao()){

                    System.out.println("Informe a nova senha: ");
                    String senha = scanner.nextLine();

                    usuarioService.alterSenha(Sessao.getUserLogado().getId(), senha);
                    System.out.println("Senha alterada com sucesso!");
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

                if (!authService.validarPermissao()){

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

                if (!authService.validarPermissao()){
                    System.out.println("Opção inválido");
                    return MenuAcao.CONTINUAR;
                }else{

                    System.out.println("Informe o ID do usuário");
                    String id = scanner.nextLine();

                    Usuario user = usuarioService.buscarPorId(UUID.fromString(id));
                    usuarioService.deletarPorId(user.getId());

                    System.out.println("O seguinte usuario foi deletado: ");
                    printer.printUsuario(user);
                    return MenuAcao.CONTINUAR;


                }

            case 0:
                System.out.println("Saindo...");
                System.out.println("---------------------------");

                return MenuAcao.SAIR;

            default:
                System.out.println("Opção inválida");
                System.out.println("---------------------------");

                return MenuAcao.CONTINUAR;

        }

    }
}
