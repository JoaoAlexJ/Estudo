package projeto.entidades;

import projeto.exception.NegocioException;
import projeto.utils.SenhaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Usuario {

    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private Cargo cargo;
    private ContaBancaria contaBancaria;
    private List<ObjDeCompra> carrinho;

    public Usuario(String nome, String email, String senha, Cargo cargo, ContaBancaria contaBancaria) {
        if (nome == null || nome.isBlank()){
            throw new NegocioException("Nome inválido");
        }
        if (email == null ||email.isBlank()){
            throw new NegocioException("Email inválido");
        }
        if (senha == null || senha.isBlank()){
            throw new NegocioException("Senha inválida");
        }
        if (cargo == null){
            throw new NegocioException("Cargo inválido");
        }

        if (cargo == Cargo.COMUM && contaBancaria == null)
            throw new NegocioException("você precisa cadastrar uma conta bancaria válida");

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.contaBancaria = contaBancaria;
        this.carrinho = new ArrayList<>();
    }

    //------------------//

    public  void adicionarObjetoCompra(ObjDeCompra o){
        if (o == null)throw new NegocioException("Produto inválido");

        carrinho.add(o);
    }

    public void removerProduto(ObjDeCompra o){
        if (o == null)throw new NegocioException("Produto inválido");

        carrinho.remove(o);
    }

    public List<ObjDeCompra> getCarrinho() {
        return new ArrayList<>(carrinho);
    }

    //------------------//

    public boolean possuiContaBancaria(){
        return contaBancaria != null;
    }

    //------------------//
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()){
            throw new NegocioException("Nome inválido");
        }

        this.nome = nome;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()){
            throw new NegocioException("E-mail inválido");
        }

        this.email = email;
    }

    public void setSenha(String senha) {

        if (senha == null || senha.isBlank()){
            throw new NegocioException("Senha inválido");
        }
        if (senha.equals(this.senha)){
            throw new NegocioException("A nova senha precisa ser diferente da atual");
        }

        this.senha = SenhaUtil.criptografar(senha);
    }

    public void setId(UUID id) {

        if (this.id != null){
            throw new NegocioException("Usuário já cadastrado");
        }

        this.id = id;
    }
    //-----------------//


    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;

        Usuario usuario = (Usuario) o;

        if (id == null || usuario.id == null) {
            return false;
        }

        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
