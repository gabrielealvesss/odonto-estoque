/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.Consulta;
import model.Consulta_produto;
import model.Paciente;
import model.Usuario;

public class ConsultaDAO extends DataBaseDAO {

    public ConsultaDAO() throws Exception {
    }

    public ArrayList<Consulta> getLista() throws Exception {
        ArrayList<Consulta> lista = new ArrayList<Consulta>();
        String SQL = "SELECT c.*, u.nome, p.nome FROM consulta c "
                + "INNER JOIN usuario u ON c.idUsuario = u.idUsuario "
                + "INNER JOIN paciente p ON c.idPaciente = p.idPaciente ";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(SQL);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Consulta c = new Consulta();
            c.setIdConsulta(rs.getInt("c.idConsulta"));
            c.setCodigo(rs.getInt("c.codigo"));
            c.setData(rs.getDate("c.data"));
            c.setHora(rs.getTime("c.hora"));
            c.setProcedimento(rs.getString("c.procedimento"));
            c.setAnamnese(rs.getString("c.anamnese"));

            Usuario u = new Usuario();
            u.setNome(rs.getString("u.nome"));
            u.setIdUsuario(rs.getInt("c.idUsuario"));

            c.setVendedor(u);

            Paciente p = new Paciente();

            p.setNome(rs.getString("p.nome"));
            p.setIdPaciente(rs.getInt("c.idPaciente"));

            c.setPaciente(p);
            lista.add(c);
        }
        this.desconectar();
        return lista;
    }

    public boolean gravar(Consulta c) {
        try {
            String sql;
            this.conectar();
            if (c.getIdConsulta() == 0) {
                sql = "INSERT INTO consulta (codigo, data, hora, procedimento, anamnese, valorTotal, idUsuario, idPaciente)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE consulta SET codigo = ?, data = ?, hora = ?, procedimento = ?, anamnese = ?, valorTotal = ?, idUsuario = ?, idPaciente = ?"
                        + " WHERE idConsulta = ?";
            }
                System.out.println(c.getProcedimento());
                System.out.println(c.getAnamnese());
                System.out.println(c.getData());
                System.out.println(c.getHora());
                System.out.println(c.getCodigo());
                System.out.println(c.getPaciente().getIdPaciente());
                System.out.println(c.getVendedor().getIdUsuario());
                System.out.println(c.getValorTotal());
                System.out.println();
            PreparedStatement pstm = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            pstm.setInt(1, c.getCodigo());
            pstm.setDate(2, new Date(c.getData().getTime()));
            pstm.setTimestamp(3, new Timestamp(c.getHora().getTime()));
            pstm.setString(4, c.getProcedimento());
            pstm.setString(5, c.getAnamnese());
            pstm.setDouble(6, c.getValorTotal());
            pstm.setInt(7, c.getVendedor().getIdUsuario());
            pstm.setInt(8, c.getPaciente().getIdPaciente());
            if (c.getIdConsulta() > 0) {
                pstm.setInt(9, c.getIdConsulta());
            }
            pstm.execute();
            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Consulta getCarregaPorID(int idConsulta) throws Exception {
        Consulta c = new Consulta();
        String sql = "SELECT c.*, u.nome, p.nome FROM consulta c "
                + "INNER JOIN usuario u ON c.idUsuario = u.idUsuario "
                + "INNER JOIN paciente p ON c.idPaciente = p.idPaciente "
                + "WHERE c.idConsulta = ?";

        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idConsulta);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            c.setIdConsulta(idConsulta);
            c.setCodigo(rs.getInt("c.codigo"));
            c.setData(rs.getDate("c.data"));
            c.setHora(rs.getTime("c.hora"));
            c.setValorTotal(rs.getDouble("c.valorTotal"));
            c.setProcedimento(rs.getString("c.procedimento"));
            c.setAnamnese(rs.getString("c.anamnese"));

            Usuario u = new Usuario();
            u.setNome(rs.getString("u.nome"));
            u.setIdUsuario(rs.getInt("c.idUsuario"));

            c.setVendedor(u);

            Paciente p = new Paciente();

            p.setNome(rs.getString("p.nome"));
            p.setIdPaciente(rs.getInt("c.idPaciente"));

            c.setPaciente(p);

            c.setCarrinho(getCarrinhoConsulta(idConsulta));

        }
        this.desconectar();
        return c;

    }

    public ArrayList<Consulta_produto> getCarrinhoConsulta(int idConsulta) throws Exception {
        ArrayList<Consulta_produto> lista = new ArrayList<Consulta_produto>();
        String SQL = "SELECT * FROM consulta_produto WHERE idConsulta=? ";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(SQL);
        pstm.setInt(1, idConsulta);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Consulta_produto cp = new Consulta_produto();
            cp.getIdConsultaProduto();
            cp.getProduto();
            cp.getQtd();
            cp.getValor();
            lista.add(cp);
        }
        this.desconectar();
        return lista;
    }

    public boolean deletar(Consulta c) {
        try {
            this.conectar();
            String sql = "DELETE FROM consulta WHERE idConsulta= ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, c.getIdConsulta());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

}
