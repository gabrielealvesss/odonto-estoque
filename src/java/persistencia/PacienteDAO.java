package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Paciente;

public class PacienteDAO extends DataBaseDAO {

    public PacienteDAO() throws Exception {
    }

    public ArrayList<Paciente> getLista() throws Exception {
        ArrayList<Paciente> lista = new ArrayList<Paciente>();
        String SQL = "SELECT p.* FROM paciente p ";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(SQL);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Paciente p = new Paciente();
            p.setIdPaciente(rs.getInt("p.idPaciente"));
            p.setNome(rs.getString("p.nome"));
            p.setTelefone(rs.getString("p.telefone"));
            p.setDataNasc(rs.getDate("p.dataNasc"));
            p.setCpf(rs.getString("p.cpf"));
            p.setRg(rs.getString("p.rg"));
            p.setOrgaoE(rs.getString("p.orgaoE"));
            p.setSexo(rs.getInt("p.sexo"));
            p.setNomeP(rs.getString("p.nomeP"));
            p.setNomeM(rs.getString("p.nomeM"));
            p.setEndereco(rs.getString("p.endereco"));
            p.setCep(rs.getString("p.cep"));
            p.setBairro(rs.getString("p.bairro"));
            p.setNumero(rs.getString("p.numero"));
            p.setComplemento(rs.getString("p.complemento"));
            p.setEmail(rs.getString("p.email"));
            p.setTipo(rs.getInt("p.tipo"));
            lista.add(p);
        }
        this.desconectar();
        return lista;
    }

    public boolean gravar(Paciente p) {
        try {
            String sql;
            this.conectar();
            if (p.getIdPaciente() == 0) {
                sql = "INSERT INTO paciente (nome, telefone, dataNasc, cpf, rg, orgaoE, sexo, nomeP, nomeM, endereco, cep, bairro, numero, complemento, email, tipo)"
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            } else {
                sql = "UPDATE paciente SET nome=?, telefone=?, dataNasc=?, cpf=?, rg=?, orgaoE=?,  sexo=?, nomeP=?, nomeM=?, endereco=?, cep=?, bairro=?, numero=?, complemento=?,email=?, tipo=?"
                        + " WHERE idPaciente=?";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, p.getNome());
            pstm.setString(2, p.getTelefone());
            pstm.setDate(3, new Date(p.getDataNasc().getTime()));
            pstm.setString(4, p.getCpf());
            pstm.setString(5, p.getRg());
            pstm.setString(6, p.getOrgaoE());
            pstm.setInt(7, p.getSexo());
            pstm.setString(8, p.getNomeM());
            pstm.setString(9, p.getNomeP());
            pstm.setString(10, p.getEndereco());
            pstm.setString(11, p.getCep());
            pstm.setString(12, p.getBairro());
            pstm.setString(13, p.getNumero());
            pstm.setString(14, p.getComplemento());
            pstm.setString(15, p.getEmail());
            pstm.setInt(16, p.getTipo());
            if (p.getIdPaciente() > 0) {
                pstm.setInt(17, p.getIdPaciente());
            }
            pstm.execute();

            this.desconectar();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Paciente getCarregaPorID(int idPaciente) throws Exception {
        Paciente p = new Paciente();
        String sql = "SELECT * FROM paciente WHERE idPaciente=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idPaciente);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            p.setIdPaciente(idPaciente);
            p.setNome(rs.getString("nome"));
            p.setTelefone(rs.getString("telefone"));
            p.setDataNasc(rs.getDate("dataNasc"));
            p.setCpf(rs.getString("cpf"));
            p.setRg(rs.getString("rg"));
            p.setOrgaoE(rs.getString("orgaoE"));
            p.setSexo(rs.getInt("sexo"));
            p.setNomeP(rs.getString("nomeP"));
            p.setNomeM(rs.getString("nomeM"));
            p.setEndereco(rs.getString("endereco"));
            p.setCep(rs.getString("cep"));
            p.setBairro(rs.getString("bairro"));
            p.setNumero(rs.getString("numero"));
            p.setComplemento(rs.getString("complemento"));
            p.setEmail(rs.getString("email"));
            p.setTipo(rs.getInt("tipo"));

        }
        this.desconectar();
        return p;

    }

    public boolean deletar(Paciente p) {
        try {
            this.conectar();
            String sql = "DELETE FROM paciente WHERE idPaciente= ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, p.getIdPaciente());
            pstm.execute();
            this.desconectar();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }
}
