package projeto.Menu;

import projeto.auth.AuthService;
import projeto.entidades.Cargo;
import projeto.entidades.Categoria;
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

        String email = solicitarEmail();
        String senha = solicitarSenha();

        authService.login(email, senha);
        System.out.println("Login efetuado com sucesso!");
        System.out.println("---------------------------");

    }

    public void cadastrarUsuario(){

        String nome = solicitarNome();
        String email = solicitarEmail();
        String senha = solicitarSenha();

        Banco banco = solicitarBanco();
        ContaBancaria contaBancaria = solicitarContaBancaria(banco);

        validarSenhaBanco(banco, contaBancaria);

        usuarioService.cadastrar(nome, email, senha, Cargo.COMUM, contaBancaria);
        System.out.println("Usuario cadastrado com sucesso");
        System.out.println("-----------------------------");

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


    //----Metodos Auxiliares-----//

    private String solicitarNome(){

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

    private String solicitarSenha(){

        System.out.println("Informe uma senha válida");
        System.out.print("-> ");
        String senha = scanner.nextLine();

        if (senha == null || senha.isBlank()){
            throw new NegocioException("senha inválida");
        }

        return senha;
    }

    //------------//

    //Produtos


    private String solicitarDescricao(){
        System.out.println("Informe uma descrição válida para o produto");
        return scanner.nextLine();
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

    private double solicitarPreco(){
        System.out.println("Informe um valor válido para o produto:");
        return scanner.nextDouble();

    }

   private int solicitarEstoque(){
       System.out.println("Informe um estoque válido para o produto: ");
       return scanner.nextInt();
   }


   //---------------//
   //banco

    private Banco solicitarBanco(){

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

        return banco;
    }

    private ContaBancaria solicitarContaBancaria(Banco b){

        System.out.println("Informe o numero da conta");
        System.out.print("-> ");
        String numeroConta = scanner.nextLine();

        if (numeroConta == null || numeroConta.isBlank())
            throw new NegocioException("Numero de conta inválido");

        return b.findConta(numeroConta);

    }

    public void validarSenhaBanco(Banco b, ContaBancaria c){
        while (true) {

            System.out.println("Informe sua senha: ");
            System.out.print("-> ");
            String senhaBanco = scanner.nextLine();


            if (!b.login(c.getNumeroConta(), senhaBanco)) {
                System.out.println("Senha ou numero da conta inválido");
                continue;
            }

            System.out.println("Conta localizada!");
            System.out.println("----------------------------");

            break;
        }
    }

    //--------------//

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
