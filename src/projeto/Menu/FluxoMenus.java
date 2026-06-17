package projeto.Menu;

import projeto.auth.AuthService;
import projeto.entidades.Cargo;
import projeto.entidades.ContaBancaria;
import projeto.entidades.entidadeDeDominio.Banco;
import projeto.exception.NegocioException;
import projeto.repositorios.BancoRepository;
import projeto.services.ProdutoService;
import projeto.services.UsuarioService;

import java.util.Scanner;

public class FluxoMenus {

    private Scanner scanner;
    private AuthService authService;
    private UsuarioService usuarioService;
    private ProdutoService produtoService;
    private BancoRepository bancoRepository;

    public FluxoMenus(Scanner scanner, AuthService authService, UsuarioService usuarioService,
                      ProdutoService produtoService, BancoRepository bancoRepository) {

        if (scanner == null)throw new NegocioException("Scanner inválido");
        if (authService == null)throw new NegocioException("Auth Service inválido");
        if (usuarioService == null)throw new NegocioException("Usuario service inválido");
        if (produtoService == null)throw new NegocioException("Produto Service inválido");
        if (bancoRepository == null)throw new NegocioException("Banco inválido");

        this.scanner = scanner;
        this.authService = authService;
        this.usuarioService = usuarioService;
        this.produtoService = produtoService;
        this.bancoRepository = bancoRepository;
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


        //--------banco-------//

        Banco banco = null;
        boolean executeMenuBanco = true;


        while (executeMenuBanco) {

            System.out.println("---- Informe o seu banco ----");
            System.out.println("1 - Bradesco");
            System.out.println("2 - Santander");
            System.out.println("3 - Itaú");
            System.out.print("-> ");
            int coice = scanner.nextInt();

            scanner.nextLine();

            switch (coice) {

                case 1:
                    banco = bancoRepository.buscarBanco("Bradesco");
                    executeMenuBanco = false;

                    break;

                case 2:
                    banco = bancoRepository.buscarBanco("Santander");
                    executeMenuBanco = false;

                    break;

                case 3:
                    banco = bancoRepository.buscarBanco("Itaú");
                    executeMenuBanco = false;

                    break;

                default:
                    System.out.println("Opção inválida");
                    break;

            }
        }

        //--------Conta Bancaria-------//

        ContaBancaria contaBancaria = null;

        while (true) {

            System.out.println("Informe o numero da sua conta");
            System.out.print("-> ");
            String numeroConta = scanner.nextLine();

            contaBancaria = banco.findConta(numeroConta);

            if (contaBancaria == null){
                throw new NegocioException("Conta não encontrada");
            }

            System.out.println("Informe sua senha: ");
            System.out.print("-> ");

            String senhaBanco = scanner.nextLine();


            if (!banco.login(numeroConta, senhaBanco)) {
                System.out.println("Senha ou numero da conta inválido");
                continue;
            }

            System.out.println("Conta localizada!");
            System.out.println("----------------------------");
            break;

        }
            usuarioService.cadastrar(nome, email, senha, Cargo.COMUM, contaBancaria);
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
