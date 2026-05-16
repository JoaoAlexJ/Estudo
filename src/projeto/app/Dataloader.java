package projeto.app;

import projeto.auth.Sessao;
import projeto.entidades.Cargo;
import projeto.entidades.Produto;
import projeto.entidades.Usuario;
import projeto.exception.NegocioException;
import projeto.services.ProdutoService;
import projeto.services.UsuarioService;

public class Dataloader {

    ProdutoService produtoService;
    UsuarioService usuarioService;

    public Dataloader(ProdutoService produtoService, UsuarioService usuarioService) {
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
    }

    public void iniciar(){

        Produto p1 = new Produto("Acabate", 3.50, 10);
        Produto p2 = new Produto("Melancia", 10.30, 10);
        Produto p3 = new Produto("Maçã", 3.20, 10);
        Produto p4 = new Produto("Manga", 4.00, 10);

        Usuario u1 = new Usuario("Joao", "joao@gmail.com", "123456", Cargo.ADM);
        Usuario u2 = new Usuario("Maria", "maria@gmail.com", "123456", Cargo.COMUM);
        Usuario u3 = new Usuario("Roberto", "roberto@gmail.com", "123456", Cargo.COMUM);
        Usuario u4 = new Usuario("Miguel", "miguel@gmail.com", "123456", Cargo.COMUM);

        produtoService.cadastrar(p1.getDescricao(), p1.getPreco(), p1.getEstoque());
        produtoService.cadastrar(p2.getDescricao(), p2.getPreco(), p2.getEstoque());
        produtoService.cadastrar(p3.getDescricao(), p3.getPreco(), p3.getEstoque());
        produtoService.cadastrar(p4.getDescricao(), p4.getPreco(), p4.getEstoque());

        usuarioService.cadastrar(u1.getNome(), u1.getEmail(), u1.getSenha(), u1.getCargo());
        usuarioService.cadastrar(u2.getNome(), u2.getEmail(), u2.getSenha(), u2.getCargo());
        usuarioService.cadastrar(u3.getNome(), u3.getEmail(), u3.getSenha(), u3.getCargo());
        usuarioService.cadastrar(u4.getNome(), u4.getEmail(), u4.getSenha(), u4.getCargo());


    }


}
