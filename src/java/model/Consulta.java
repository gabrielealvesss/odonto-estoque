package model;

import java.util.ArrayList;
import java.util.Date;

public class Consulta {

    private int idConsulta;
    private int codigo;
    private int idUsuario;
    private int idPaciente;
    private double valorTotal;
    private Date data;
    private Date hora;
    private String procedimento;
    private String anamnese;
    private Usuario vendedor;
    private Paciente paciente;

    private ArrayList<Consulta_produto> carrinho;


    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
      public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ArrayList<Consulta_produto> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ArrayList<Consulta_produto> carrinho) {
        this.carrinho = carrinho;
    }

    public Consulta() {
    }

    public Consulta(int idConsulta, int codigo, int idUsuario, int idPaciente, double valorTotal, Date data, Date hora, String procedimento, String anamnese, Usuario vendedor, Paciente paciente, ArrayList<Consulta_produto> carrinho) {
        this.idConsulta = idConsulta;
        this.codigo = codigo;
        this.idUsuario = idUsuario;
        this.idPaciente = idPaciente;
        this.valorTotal = valorTotal;
        this.data = data;
        this.hora = hora;
        this.procedimento = procedimento;
        this.anamnese = anamnese;
        this.vendedor = vendedor;
        this.paciente = paciente;
        this.carrinho = carrinho;
    }

    public String toString(){
        return "Perfil: " + getIdConsulta();
    }
}
