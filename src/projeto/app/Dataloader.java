package projeto.app;

import projeto.auth.Sessao;
import projeto.entidades.Cargo;
import projeto.entidades.ContaBancaria;
import projeto.entidades.Produto;
import projeto.entidades.Usuario;
import projeto.entidades.entidadeDeDominio.Banco;
import projeto.exception.NegocioException;
import projeto.repositorios.BancoRepository;
import projeto.services.ProdutoService;
import projeto.services.UsuarioService;

public class Dataloader {

    ProdutoService produtoService;
    UsuarioService usuarioService;
    BancoRepository bancoRepository;

    public Dataloader(ProdutoService produtoService, UsuarioService usuarioService, BancoRepository bancoRepository) {
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
        this.bancoRepository = bancoRepository;
    }

    public void iniciar(){

        Produto p1 = new Produto("Acabate", 3.50, 10);
        Produto p2 = new Produto("Melancia", 10.30, 10);
        Produto p3 = new Produto("Maçã", 3.20, 10);
        Produto p4 = new Produto("Manga", 4.00, 10);

        //---------------------------//

        Banco santander = new Banco("Santander");
        Banco bradesco = new Banco("Bradesco");
        Banco itau = new Banco("Itaú");

        //----------------------------//

        ContaBancaria c1 = new ContaBancaria(santander,"Joao", "741", "123456", 1000);
        ContaBancaria c2 = new ContaBancaria(bradesco,"Maria", "852", "123456", 1000);
        ContaBancaria c3 = new ContaBancaria(itau,"Roberto", "741", "123456", 1000);

        ContaBancaria cCadastro = new ContaBancaria(itau, "Paulo", "891", "123456", 1000 );

        //-----------------------------//

        Usuario u1 = new Usuario("Joao", "joao@gmail.com", "123456", Cargo.ADM, null);
        Usuario u2 = new Usuario("Maria", "maria@gmail.com", "123456", Cargo.COMUM, c2);
        Usuario u3 = new Usuario("Roberto", "roberto@gmail.com", "123456", Cargo.COMUM, c3);

        //-----------------------------//

        santander.adicionarConta(c1);
        bradesco.adicionarConta(c2);
        itau.adicionarConta(cCadastro);
        itau.adicionarConta(c3);

        //-----------------------------//



        produtoService.cadastrar(p1.getDescricao(), p1.getPreco(), p1.getEstoque());
        produtoService.cadastrar(p2.getDescricao(), p2.getPreco(), p2.getEstoque());
        produtoService.cadastrar(p3.getDescricao(), p3.getPreco(), p3.getEstoque());
        produtoService.cadastrar(p4.getDescricao(), p4.getPreco(), p4.getEstoque());

        //-----------------------------//


        usuarioService.cadastrar(u1.getNome(), u1.getEmail(), u1.getSenha(), u1.getCargo(), c1);
        usuarioService.cadastrar(u2.getNome(), u2.getEmail(), u2.getSenha(), u2.getCargo(), c2);
        usuarioService.cadastrar(u3.getNome(), u3.getEmail(), u3.getSenha(), u3.getCargo(), c3);

        //-----------------------------//

        bancoRepository.adicionarBanco(bradesco);
        bancoRepository.adicionarBanco(santander);
        bancoRepository.adicionarBanco(itau);


    }


}
