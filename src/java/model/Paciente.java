
package model;

import java.util.Date;

public class Paciente extends Pessoa {
    private int idPaciente;
    private int tipo;
    private String telefone;
    private String orgaoE;
    private String endereco;
    private String cep;
    private String bairro;
    private String numero;
    private String complemento;
    private String email;
    
    
    public int getTipo(){
        return tipo;
    }
    
    public void setTipo(int tipo){
        this.tipo = tipo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getOrgaoE() {
        return orgaoE;
    }

    public void setOrgaoE(String orgaoE) {
        this.orgaoE = orgaoE;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Paciente (){}
    
    public Paciente(String nome, String cpf, String rg, String nomeP, String nomeM, Date dataNasc, int idPaciente, String telefone, String orgaoE, String endereco, String cep, String bairro, String numero, String complemento, String email) {
        super(nome, cpf, rg, nomeP, nomeM, dataNasc);
        this.idPaciente = idPaciente;
        this.tipo = tipo;
        this.telefone = telefone;
        this.orgaoE = orgaoE;
        this.endereco = endereco;
        this.cep = cep;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.email = email;
    }
    
    public String toString() {
        return "nome" + getNome();
    }

}
