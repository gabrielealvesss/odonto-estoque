
package model;

import java.util.Date;

public class Produto {
    private int idProduto;
    private int codigo;
    private int quantidade;
    private int idFornecedor;
    private int idCategoria;
    private int idUsuario;
    private double valor;
    private Date validade;
    private Date dataEntrada;
    private String nome;
    private String marca;
    private Fornecedor fornecedor;
    private Categoria categoria;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    public Produto(){}

    public Produto(int idProduto, int codigo, int quantidade, int idFornecedor, int idCategoria, int idUsuario, double valor, Date validade, Date dataEntrada, String nome, String marca, Fornecedor fornecedor, Categoria categoria) {
        this.idProduto = idProduto;
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.idFornecedor = idFornecedor;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
        this.valor = valor;
        this.validade = validade;
        this.dataEntrada = dataEntrada;
        this.nome = nome;
        this.marca = marca;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
    }
    
    public String toString(){
        return "Nome: " + getNome();
    }
  
       
}
