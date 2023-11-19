
package model;

import java.util.Date;

public class Log {
    private int idLog;
    private int idUsuario;
    private String nomeUsuario;
    private Date data;
    private String acao;
    private String tabela;

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }
    
    public String getNomeUsuario(){
        return nomeUsuario;
    }
    
    public void setNomeUsuario(String nomeUsuario) {
    this.nomeUsuario = nomeUsuario;
}

    public Log() {
    }

    public Log(int idLog, int idUsuario, String nomeUsuario, Date data, String acao, String tabela) {
        this.idLog = idLog;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.data = data;
        this.acao = acao;
        this.tabela = tabela;
    }

    @Override
    public String toString() {
        return "Log{" + "idLog=" + idLog + ", idUsuario=" + idUsuario + ", data=" + data + ", acao=" + acao + ", tabela=" + tabela + '}';
    }
                    
    
}
