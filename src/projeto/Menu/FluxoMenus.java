package projeto.Menu;

import projeto.auth.AuthService;
import projeto.entidades.Cargo;
import projeto.exception.NegocioException;
import projeto.services.ProdutoService;
import projeto.services.UsuarioService;

import java.util.Scanner;

public class FluxoMenus {

    private Scanner scanner;
    private AuthService authService;
    private UsuarioService usuarioService;
    private ProdutoService produtoService;

    public FluxoMenus(Scanner scanner, AuthService authService, UsuarioService usuarioService, ProdutoService produtoService) {
        if (scanner == null)throw new NegocioException("Scanner inválido");
        if (authService == null)throw new NegocioException("Auth Service inválido");
        if (usuarioService == null)throw new NegocioException("Usuario service inválido");
        if (produtoService == null)throw new NegocioException("Produto Service inválido");

        this.scanner = scanner;
        this.usuarioService = usuarioService;
        this.produtoService = produtoService;
    }

    public void loginFluxo(){

        System.out.print("Informe o seu email: \n");
        String email = scanner.nextLine();

        System.out.print("Informe sua senha: \n");
        String senha = scanner.nextLine();

        authService.login(email, senha);
        System.out.println("Login efetuado com sucesso!");
        System.out.println("---------------------------");

    }

    public void cadastrarUsuario(){

        System.out.println("Informe um nome de usuario: ");
        String nome = scanner.nextLine();

        System.out.println("Informe um e-mail válido: ");
        String email = scanner.nextLine();

        System.out.println("Informe uma senha válida: ");
        String senha = scanner.nextLine();

        usuarioService.cadastrar(nome, email, senha, Cargo.COMUM);
        System.out.println("Usuario cadastrado com sucesso");
        System.out.println("-----------------------------");


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
