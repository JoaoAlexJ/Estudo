package projeto.Menu.Fluxos;

import projeto.auth.AuthService;
import projeto.entidades.Cargo;
import projeto.entidades.ContaBancaria;
import projeto.entidades.entidadeDeDominio.Banco;
import projeto.exception.NegocioException;
import projeto.services.UsuarioService;

import java.util.Scanner;

public class FluxoUsuario {

    private Scanner scanner;
    private FluxoBanco fluxoBanco;

    private AuthService authService;
    private UsuarioService usuarioService;

    public FluxoUsuario(Scanner scanner, AuthService authService, FluxoBanco fluxoBanco, UsuarioService usuarioService) {
        if (scanner == null)throw new NegocioException("Scanner inválido");
        if (authService == null)throw new NegocioException("Auth service inválido");
        if (fluxoBanco == null)throw new NegocioException("Fluxo banco inválido");
        if (usuarioService == null)throw new NegocioException("Usuario Service inválido");


        this.scanner = scanner;
        this.authService = authService;
        this.fluxoBanco = fluxoBanco;
        this.usuarioService = usuarioService;
    }

    public String solicitarNome(){

        System.out.println("Informe um nome válido");
        System.out.print("-> ");
        String nome = scanner.nextLine();

        if (nome == null || nome.isBlank()){
            throw new NegocioException("Nome inválido");
        }

        return nome;
    }

    private String solicitarEmail(){

        System.out.println("Informe um e-mail válido");
        System.out.print("-> ");
        String email = scanner.nextLine();

        if (email == null || email.isBlank()){
            throw new NegocioException("e-mail inválido");
        }

        return email;
    }

    public String solicitarSenha(){

        System.out.println("Informe uma senha válida");
        System.out.print("-> ");
        String senha = scanner.nextLine();

        if (senha == null || senha.isBlank()){
            throw new NegocioException("senha inválida");
        }

        return senha;
    }

    public void cadastrarUsuario(){

        String nome = solicitarNome();
        String email = solicitarEmail();
        String senha = solicitarSenha();

        Banco banco = fluxoBanco.solicitarBanco();
        ContaBancaria contaBancaria = fluxoBanco.solicitarContaBancaria(banco);

        fluxoBanco.validarSenhaBanco(contaBancaria);

        usuarioService.cadastrar(nome, email, senha, Cargo.COMUM, contaBancaria);
        System.out.println("Usuario cadastrado com sucesso");
        System.out.println("-----------------------------");

    }

    public void loginFluxo(){

        String email = solicitarEmail();
        String senha = solicitarSenha();

        authService.login(email, senha);
        System.out.println("Login efetuado com sucesso!");
        System.out.println("---------------------------");

    }


}
