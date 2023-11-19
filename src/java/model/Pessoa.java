
package model;

import java.util.Date;

public abstract class Pessoa {
     private String nome;
     private String cpf;
     private String rg;
     private String nomeP;
     private String nomeM;
     private int sexo;
     private Date dataNasc;

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNomeP() {
        return nomeP;
    }

    public void setNomeP(String nomeP) {
        this.nomeP = nomeP;
    }

    public String getNomeM() {
        return nomeM;
    }

    public void setNomeM(String nomeM) {
        this.nomeM = nomeM;
    }
    
    public int getSexo(){
        return sexo;
    }
    
    public void setSexo(int sexo){
        this.sexo = sexo;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
    
    public Pessoa(){}

    public Pessoa(String nome, String cpf, String rg, String nomeP, String nomeM, Date dataNasc) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.nomeP = nomeP;
        this.nomeM = nomeM;
        this.dataNasc = dataNasc;
    }
}
