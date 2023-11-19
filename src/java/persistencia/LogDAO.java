package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Log;

public class LogDAO extends DataBaseDAO {
    private ArrayList<Log> lista = new ArrayList<Log>();

    public LogDAO() throws Exception {}

    public ArrayList<Log> getLista() {
        return lista;
    }

    public void carregarLista() throws Exception {
        lista.clear(); // Limpa a lista antes de carregar novamente

        String SQL = "SELECT l.*, u.nome as nomeUsuario FROM logs l " +
                     "JOIN usuario u ON l.idUsuario = u.idUsuario";
        
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL);
        
        while (rs.next()) {
            Log l = new Log();
            l.setIdUsuario(rs.getInt("idUsuario"));
            l.setNomeUsuario(rs.getString("nomeUsuario"));
            l.setAcao(rs.getString("acao"));
            l.setTabela(rs.getString("tabela"));
            l.setData(rs.getTimestamp("data"));
            lista.add(l);
        }
        
        this.desconectar();
    }

    public void gravarLog(Log l) {
        try {
            String sql = "INSERT INTO logs (idUsuario, acao, tabela, data) VALUES (?, ?, ?, now())";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, l.getIdUsuario());
            pstm.setString(2, l.getAcao());
            pstm.setString(3, l.getTabela());
            pstm.execute();
            this.desconectar();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Log getCarregaPorID(int idUsuario) throws Exception {
        Log log = null;
        String SQL = "SELECT * FROM logs WHERE idUsuario = ?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(SQL);
        pstm.setInt(1, idUsuario);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            log = new Log();
            log.setIdUsuario(rs.getInt("idUsuario"));
            log.setAcao(rs.getString("acao"));
            log.setTabela(rs.getString("tabela"));
            log.setData(rs.getDate("data"));
        }
        this.desconectar();
        return log;
    }
}
