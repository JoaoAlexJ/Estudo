package projeto.Menu;

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
    private AuthService authService;
    private UsuarioService usuarioService;
    private ProdutoService produtoService;
    private BancoRepository bancoRepository;
    private Printer printer;
    private VendaService vendaService;

    public FluxoMenus(Scanner scanner, AuthService authService, UsuarioService usuarioService,
                      ProdutoService produtoService, BancoRepository bancoRepository, Printer printer, VendaService vendaService) {

        if (scanner == null)throw new NegocioException("Scanner inválido");
        if (authService == null)throw new NegocioException("Auth Service inválido");
        if (usuarioService == null)throw new NegocioException("Usuario service inválido");
        if (produtoService == null)throw new NegocioException("Produto Service inválido");
        if (bancoRepository == null)throw new NegocioException("Banco inválido");
        if (printer == null)throw new NegocioException("Printer inválido");
        if (vendaService == null)throw new NegocioException("Venda Service inválido");


        this.scanner = scanner;
        this.authService = authService;
        this.usuarioService = usuarioService;
        this.produtoService = produtoService;
        this.bancoRepository = bancoRepository;
        this.printer = printer;
        this.vendaService = vendaService;

    }

    //----- Venda -------


    public MenuAcao vendaFluxo(){

        Usuario user = Sessao.getUserLogado();

        System.out.println("Informe a descrição do produto que deseja comprar: ");
        String descricao = scanner.nextLine();
        Produto produto = produtoService.buscarPorDescricao(descricao);

        System.out.println("Informe a quantidade que deseja desse produto:");
        int quantidade = scanner.nextInt();

        if (quantidade > produto.getEstoque())throw new NegocioException("estoque insuficiente");

        ObjDeCompra objDeCompra = new ObjDeCompra(produto.getId(), produto.getCategoria(),
                produto.getDescricao(), produto.getPreco(), quantidade);



        user.getCarrinho().adicionarObjCompra(objDeCompra);
        printer.printCarrinho(user.getCarrinho());

        System.out.println("----------------------");


        boolean execute = true;

        while (execute) {


            System.out.println("1 - Finalizar pedido");
            System.out.println("2 - adicionar mais produtos ao carrinho");
            System.out.println("3 - voltar");
            System.out.print("-> ");
            int choiceCarrinho = scanner.nextInt();

            scanner.nextLine();

            switch (choiceCarrinho) {

                case 1:
                    finalizarVenda();
                    return MenuAcao.CONTINUAR;


                case 2:

                    execute = false;
                    System.out.println("----------------------");
                    return MenuAcao.CONTINUAR;

                case 3:
                    System.out.println("voltando...");
                    System.out.println("----------------------");
                    execute = false;
                    return MenuAcao.CONTINUAR;


                default:
                    System.out.println("Opção inválida");
                    return MenuAcao.CONTINUAR;


            }

        }


        return null;
    }

    public MenuAcao vendaFluxo2(Produto produto){

        Usuario user = Sessao.getUserLogado();

        System.out.println("Informe a quantidade que deseja desse produto:");
        int quantidade = scanner.nextInt();

        if (quantidade > produto.getEstoque())throw new NegocioException("estoque insuficiente");

        ObjDeCompra objDeCompra = new ObjDeCompra(produto.getId(), produto.getCategoria(),
                produto.getDescricao(), produto.getPreco(), quantidade);



        user.getCarrinho().adicionarObjCompra(objDeCompra);
        printer.printCarrinho(user.getCarrinho());

        System.out.println("----------------------");


        boolean execute = true;

        while (execute) {


            System.out.println("1 - Finalizar pedido");
            System.out.println("2 - adicionar mais produtos ao carrinho");
            System.out.println("3 - voltar");
            System.out.print("-> ");

            int choiceCarrinho = scanner.nextInt();

            scanner.nextLine();

            switch (choiceCarrinho) {

                case 1:
                    finalizarVenda();
                    System.out.println("----------------------");
                    return MenuAcao.CONTINUAR;


                case 2:

                    execute = false;
                    System.out.println("----------------------");
                    return MenuAcao.CONTINUAR;

                case 3:
                    System.out.println("voltando...");
                    System.out.println("----------------------");
                    execute = false;
                    return MenuAcao.CONTINUAR;


                default:
                    System.out.println("Opção inválida");
                    return MenuAcao.CONTINUAR;


            }

        }


        return null;
    }


    public void finalizarVenda(){
        Usuario user = Sessao.getUserLogado();

        printer.printCarrinho(user.getCarrinho());

        System.out.println("--------- Dados bancário ----------");
        System.out.println("Titular: " + user.getContaBancaria().getTitular());
        System.out.println("Numero da conta: " + user.getContaBancaria().getNumeroConta());

        validarSenhaBanco(user.getContaBancaria());

        Venda venda = vendaService.realizarVenda(user, user.getCarrinho());

        System.out.println("Pagamento aprovado!");
        System.out.printf("Valor: %.2f\n", user.getCarrinho().calcularValorTotal());
        System.out.println("------------------------------------");

        printer.printVenda(venda);
        System.out.println("------------------------------------");

        user.getCarrinho().limparCarrinho();
    }



    //----- Usuario -----

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

        validarSenhaBanco(contaBancaria);

        usuarioService.cadastrar(nome, email, senha, Cargo.COMUM, contaBancaria);
        System.out.println("Usuario cadastrado com sucesso");
        System.out.println("-----------------------------");

    }



    //----Metodos Auxiliares-----//

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
                    return vendaFluxo();

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
                    return vendaFluxo2(produto);

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

    public void cadastrarProduto(){

        String descricao = solicitarDescricao();
        Categoria categoria = solicitarCategoria();
        double preco = solicitarPreco();
        int estoque = solicitarEstoque();

        produtoService.cadastrar(categoria, descricao, preco, estoque);
        System.out.println("Produto cadastrado com sucesso!");
        System.out.println("-----------------------------");

    }

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
            System.out.print("-> ");
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

    public void validarSenhaBanco(ContaBancaria c){
        while (true) {

            System.out.println("Informe sua senha ");
            System.out.print("-> ");
            String senhaBanco = scanner.nextLine();


            if (!c.validarSenha(c.getNumeroConta(), senhaBanco)) {
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
