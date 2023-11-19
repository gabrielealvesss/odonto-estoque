
package model;

import java.util.Date;

public class Usuario extends Pessoa {
    private int idUsuario;
    private int status;
    private int matricula;
    private int idPerfil;
    private int tipo;
    private String login;
    private String senha;
    private String endereco;
    private String cep;
    private String bairro;
    private String numero;
    private String complemento;
    private String cargo;
    private String orgaoE;
    private String foto;
    private Perfil perfil;
    
    
    public int getTipo(){
        return tipo;
    }
    
    public void setTipo(int tipo){
        this.tipo = tipo;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getOrgaoE() {
        return orgaoE;
    }

    public void setOrgaoE(String orgaoE) {
        this.orgaoE = orgaoE;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    
    public Usuario(){}

    public Usuario(String nome, String cpf, String rg, String nomeP, String nomeM, Date dataNasc, int idUsuario, int status, int matricula, int idPerfil, int tipo, String login, String senha, String endereco, String cep, String bairro, String numero, String complemento, String cargo, String orgaoE, String foto, Perfil perfil) {
        super(nome, cpf, rg, nomeP, nomeM, dataNasc);
        this.idUsuario = idUsuario;
        this.status = status;
        this.matricula = matricula;
        this.idPerfil = idPerfil;
        this.tipo = tipo;
        this.login = login;
        this.senha = senha;
        this.endereco = endereco;
        this.cep = cep;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.cargo = cargo;
        this.orgaoE = orgaoE;
        this.foto = foto;
        this.perfil = perfil;
    }
    
    
    public String toString(){
        return "nome" + getNome();
    }

}
