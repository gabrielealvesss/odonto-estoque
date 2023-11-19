
package model;

public class Consulta_produto {
    private long idConsultaProduto;
    private int qtd;
    private double valor;
    private Consulta consulta;
    private Produto produto;

    public long getIdConsultaProduto() {
        return idConsultaProduto;
    }

    public void setIdConsultaProduto(long idConsultaProduto) {
        this.idConsultaProduto = idConsultaProduto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }   
    
    public Consulta_produto() {
    }

    public Consulta_produto(long idConsultaProduto, int qtd, double valor, Consulta consulta, Produto produto) {
        this.idConsultaProduto = idConsultaProduto;
        this.qtd = qtd;
        this.valor = valor;
        this.consulta = consulta;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Consulta_produto{" + "idConsultaProduto=" + idConsultaProduto + ", qtd=" + qtd + ", valor=" + valor + ", consulta=" + consulta + ", produto=" + produto + '}';
    }

}
