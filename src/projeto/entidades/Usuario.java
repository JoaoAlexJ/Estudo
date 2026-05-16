package projeto.entidades;

import projeto.exception.NegocioException;
import projeto.utils.SenhaUtil;

import java.util.Objects;
import java.util.UUID;

public class Usuario {

    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private Cargo cargo;


    public Usuario(String nome, String email, String senha, Cargo cargo) {
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

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
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
