package projeto.entidades;

import projeto.auth.AuthService;
import projeto.auth.Sessao;
import projeto.exception.NegocioException;
import projeto.repositorios.ProdutoRepository;
import projeto.services.ProdutoService;
import projeto.services.UsuarioService;

import java.util.List;
import java.util.UUID;

public class Mercado {

    private ProdutoService produtoService;
    private UsuarioService usuarioService;
    private AuthService authService;

    public Mercado(ProdutoService produtoService, UsuarioService usuarioService, AuthService authService) {
        if (produtoService == null)throw new NegocioException("Produuto Service inválido");
        if (usuarioService == null)throw new NegocioException("Usuario Service inválido");
        if (authService == null)throw new NegocioException("Auth Service inválido");

        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
        this.authService = authService;
    }

    // Produto Service //

    public void cadastrarProduto(String descricao, double preco, int estoque){
        if (validarPermissao()){
            produtoService.cadastrar(descricao, preco, estoque);
        }else {
            throw new NegocioException("Seu usuário não tem permissão para esse serviço");
        }
    }
    public Produto buscarProdutoID(UUID id){
        return produtoService.buscar(id);
    }
    public Produto buscarProdutoDescricao(String descricao){
        return produtoService.listar().stream()
                .filter(p -> p.getDescricao().equals(descricao))
                .findFirst()
                .orElseThrow(() -> new NegocioException("Produto não encontrado"));
    }

    public List<Produto> listarProdutos (){
        return produtoService.listar();
    }

    // produto adm //

    public void deletarProduto(UUID id){
        if (validarPermissao()){
            produtoService.delete(id);
        }else {
            throw new NegocioException("Seu usuário nao tem permissão para esse serviço");
        }
    }

    public void alterarPrecoProduto(UUID id, double preco){
        if (validarPermissao()){
            produtoService.alterarPreco(id, preco);
        }else {
            throw new NegocioException("Seu usuário nao tem permissão para esse serviço");
        }
    }
    public void alterarDescricaoProduto(UUID id, String descricao){
        if (validarPermissao()){
            produtoService.alterarDescricao(id, descricao);
        }else {
            throw new NegocioException("Seu usuário nao tem permissão para esse serviço");
        }
    }
    public void alterarEstoque(UUID id, int estoque){
        if (validarPermissao()){
            produtoService.alterarEstoque(id, estoque);
        }else {
            throw new NegocioException("Seu usuário nao tem permissão para esse serviço");
        }
    }

    //----------------//


    // Usuario Service //

    public void cadastrarUsuario (String nome, String email, String senha){
        if (validarPermissao()){
            usuarioService.cadastrar(nome, email, senha, Cargo.COMUM);
        }else {
            throw new NegocioException("Seu usuário nao tem permissão para esse serviço");
        }
    }

    public Usuario buscarUsuario (UUID id){
        return usuarioService.buscarPorId(id);
    }

    public Usuario buscarUsuarioEmail(String email){
        return usuarioService.listar().stream()
                .filter(p -> p.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new NegocioException("Usuario não encontrado"));
    }

    public List<Usuario> listarUsuarios(){
        return usuarioService.listar();
    }

    // Usuario adm //

    public void deletarUsuario(UUID id){
        if(Sessao.getUserLogado().getId().equals(id))throw new NegocioException("O usuário logado nao pode ser deletado");

        if (validarPermissao()){
            usuarioService.deletarPorId(id);
        }else {
            throw new NegocioException("Seu usuário nao tem permissão para esse serviço");
        }
    }

    public void alterarNomeUsuario(UUID id, String nome){
        if (validarPermissao()){
            usuarioService.alterarNome(id, nome);
        }else {
            throw new NegocioException("Seu usuário nao tem permissão para esse serviço");
        }
    }

    public void alterarEmailUsuario(UUID id, String email){
        if (validarPermissao()){
            usuarioService.alterarEmail(id, email);
        }else {
            throw new NegocioException("Seu usuário nao tem permissão para esse serviço");
        }
    }

    public void alterarSenhaUsuario(UUID id, String senha){
        if (validarPermissao()){
            usuarioService.alterSenha(id, senha);
        }else {
            throw new NegocioException("Seu usuário nao tem permissão para esse serviço");
        }
    }

    //----------------//

    //Auth Service //

    public void login(String email, String senha){
        authService.login(email, senha);
    }

    public void logout(){
        authService.logout();
    }

    public boolean validarPermissao(){
        return authService.validarPermissao();
    }

    //----------------//


}
