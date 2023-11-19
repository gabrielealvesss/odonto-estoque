
package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Perfil;
import model.Usuario;

public class UsuarioDAO extends DataBaseDAO{
    
    public UsuarioDAO () throws Exception{}
    
    public ArrayList<Usuario> getLista() throws Exception{
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        String SQL = "SELECT u.*, p.nome, p.status FROM usuario u " 
                + "INNER JOIN perfil p ON p.idPerfil = u.idPerfil";
        this.conectar();
       PreparedStatement pstm = conn.prepareStatement(SQL);
       ResultSet rs = pstm.executeQuery();
       while(rs.next()){
           Usuario u = new Usuario();
           u.setIdUsuario(rs.getInt("u.idUsuario"));
           u.setNome(rs.getString("u.nome"));
           u.setLogin(rs.getString("u.login"));
           u.setSenha(rs.getString("u.senha"));
           u.setStatus(rs.getInt("u.status"));
           u.setCpf(rs.getString("u.cpf"));
           u.setRg(rs.getString("u.rg"));
           u.setSexo(rs.getInt("u.sexo"));
           u.setNomeP(rs.getString("u.nomeP"));
           u.setNomeM(rs.getString("u.nomeM"));
           u.setDataNasc(rs.getDate("u.dataNasc"));
           u.setMatricula(rs.getInt("u.matricula"));
           u.setEndereco(rs.getString("u.endereco"));
           u.setCep(rs.getString("u.cep"));
           u.setBairro(rs.getString("u.bairro"));
           u.setNumero(rs.getString("u.numero"));
           u.setComplemento(rs.getString("u.complemento"));
           u.setCargo(rs.getString("u.cargo"));
           u.setOrgaoE(rs.getString("u.orgaoE"));
           u.setFoto(rs.getString("u.foto"));
           
           Perfil p = new Perfil();
           p.setNome(rs.getString("p.nome"));
           p.setStatus(rs.getInt("p.status"));
           p.setIdPerfil(rs.getInt("u.idPerfil"));
           u.setPerfil(p);
           
           lista.add(u);
       }
        this.desconectar();
        return lista;
    }
    
    
 public boolean gravar(Usuario u) {
    try {
        String sql;
        this.conectar();
        if (u.getIdUsuario() == 0) {
            sql = "INSERT INTO usuario (login, senha, nome, status, cpf, rg, dataNasc, sexo, matricula, nomeP, nomeM, endereco, cep, bairro, numero, complemento, cargo, orgaoE, foto, idPerfil)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        } else {
            sql = "UPDATE usuario SET login=?, senha=?, nome=?, status=?, cpf=?, rg=?, dataNasc=?, sexo=?, matricula=?, nomeP=?, nomeM=?, endereco=?, cep=?, bairro=?, numero=?, complemento=?, cargo=?, orgaoE=?, foto=?, idPerfil=? "
                    + " WHERE idUsuario=?";
        }
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, u.getLogin());
        pstm.setString(2, u.getSenha());
        pstm.setString(3, u.getNome());
        pstm.setInt(4, u.getStatus());
        pstm.setString(5, u.getCpf());
        pstm.setString(6, u.getRg());
        pstm.setDate(7,new Date(u.getDataNasc().getTime()));
        pstm.setInt(8, u.getSexo());
        pstm.setInt(9, u.getMatricula());
        pstm.setString(10, u.getNomeP());
        pstm.setString(11, u.getNomeM());
        pstm.setString(12, u.getEndereco());
        pstm.setString(13, u.getCep());
        pstm.setString(14, u.getBairro());
        pstm.setString(15, u.getNumero());
        pstm.setString(16, u.getComplemento());
        pstm.setString(17, u.getCargo());
        pstm.setString(18, u.getOrgaoE());
        pstm.setString(19, u.getFoto());
        pstm.setInt(20, u.getPerfil().getIdPerfil());
        if (u.getIdUsuario() > 0)
            pstm.setInt(21, u.getIdUsuario());
        pstm.execute();
        
        
       
        
        
        this.desconectar();
        return true;

    } catch (Exception e) {
        System.out.println(e);
        return false;
    }
}
 
 public Usuario getCarregaPorID(int idUsuario) throws Exception{
        Usuario u = new Usuario();
        String sql = "SELECT * FROM usuario WHERE idUsuario=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idUsuario);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
           u.setIdUsuario(idUsuario);
           u.setNome(rs.getString("nome"));
           u.setLogin(rs.getString("login"));
           u.setSenha(rs.getString("senha"));
           u.setStatus(rs.getInt("status"));
           u.setCpf(rs.getString("cpf"));
           u.setRg(rs.getString("rg"));
           u.setSexo(rs.getInt("sexo"));
           u.setNomeP(rs.getString("nomeP"));
           u.setNomeM(rs.getString("nomeM"));
           u.setDataNasc(rs.getDate("dataNasc"));
           u.setMatricula(rs.getInt("matricula"));
           u.setEndereco(rs.getString("endereco"));
           u.setCep(rs.getString("cep"));
           u.setBairro(rs.getString("bairro"));
           u.setNumero(rs.getString("numero"));
           u.setComplemento(rs.getString("complemento"));
           u.setCargo(rs.getString("cargo"));
           u.setOrgaoE(rs.getString("orgaoE"));
           u.setFoto(rs.getString("foto"));
           PerfilDAO pDAO = new PerfilDAO();
           u.setPerfil(pDAO.getCarregaPorID(rs.getInt("idPerfil")));
        }
        this.desconectar();
        return u;
    
    }
 
  public boolean deletar(Usuario u){
        try{
            this.conectar();
            String sql = "DELETE FROM usuario WHERE idUsuario= ?";  
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, u.getIdUsuario());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
        
}
  
  public Usuario getRecuperarUsuario(String login){
      Usuario u = new Usuario();
      String sql =  "SELECT u.* FROM usuario u " 
              + "WHERE u.login = ?";
      
      try{
          this.conectar();
          PreparedStatement pstm = conn.prepareStatement(sql);
          pstm.setString(1, login);
          ResultSet rs = pstm.executeQuery();
          if(rs.next()){
          u.setIdUsuario(rs.getInt("u.idUsuario"));
          u.setNome(rs.getString("u.nome"));
          u.setLogin(rs.getString("u.login"));
          u.setSenha(rs.getString("u.senha"));
          u.setStatus(rs.getInt("u.status"));
          
          
          PerfilDAO pDAO = new PerfilDAO();
          u.setPerfil(pDAO.getCarregaPorID(rs.getInt("u.idPerfil")));
      }
          this.desconectar();
          return u;
      }catch(Exception e){
          System.out.println(e);
          return null;
      }
  }
}
