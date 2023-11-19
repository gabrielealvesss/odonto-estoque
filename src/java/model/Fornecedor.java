
package model;
public class Fornecedor {
    private int idFornecedor;
    private String nome;
    private String cep;
    private String endereco;
    private String telefone;

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public Fornecedor(){}

    public Fornecedor(int idFornecedor, String nome, String cep, String endereco, String telefone) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.cep = cep;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    
    public String toString(){
        return "nome" + getNome();
    }
}
