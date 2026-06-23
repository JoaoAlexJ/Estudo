package projeto.app;

import projeto.Menu.Fluxos.*;
import projeto.Menu.ProdutoMenu;
import projeto.Menu.UsuarioMenu;
import projeto.auth.AuthService;
import projeto.auth.Sessao;
import projeto.printers.Printer;
import projeto.repositorios.BancoRepository;
import projeto.repositorios.ProdutoRepository;
import projeto.repositorios.UsuarioRepository;
import projeto.repositorios.VendaRepository;
import projeto.services.ProdutoService;
import projeto.services.UsuarioService;
import projeto.services.VendaService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Printer printer = new Printer();
        Scanner scanner = new Scanner(System.in);

        //--------Repositorios--------

        ProdutoRepository produtoRepository = new ProdutoRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        BancoRepository bancoRepository = new BancoRepository();
        VendaRepository vendaRepository = new VendaRepository();


        //-------- Services --------

        ProdutoService produtoService = new ProdutoService(produtoRepository);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        AuthService authService = new AuthService(usuarioService, scanner);
        VendaService vendaService = new VendaService(vendaRepository, produtoService);


        //-------- Fluxos --------

        FluxoProdutos fluxoProdutos = new FluxoProdutos(scanner, produtoService);
        FluxoBanco fluxoBanco = new FluxoBanco(scanner, bancoRepository);
        FluxoVenda fluxoVenda = new FluxoVenda(scanner, printer, fluxoBanco, vendaService, produtoService);
        FluxoUsuario fluxoUsuario = new FluxoUsuario(scanner, authService, fluxoBanco, usuarioService);
        FluxoMenus fluxoMenus = new FluxoMenus(scanner, fluxoVenda);


        //--------Menus--------

        ProdutoMenu produtoMenu = new ProdutoMenu(produtoService, authService, scanner, printer, fluxoProdutos, fluxoMenus, fluxoVenda);
        UsuarioMenu usuarioMenu = new UsuarioMenu(fluxoUsuario, usuarioService, vendaService, authService, printer, scanner);


        //--------Dataloader--------
        Dataloader dataloader = new Dataloader(produtoService, usuarioService, bancoRepository);
        dataloader.iniciar();


        //----------------//



        try {

            FluxoPrincipal.iniciar(fluxoUsuario, fluxoMenus, produtoMenu, usuarioMenu);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        scanner.close();

    }

}