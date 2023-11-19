
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Categoria;


public class CategoriaDAO extends DataBaseDAO{
    
    public CategoriaDAO() throws Exception{}
    
    public ArrayList<Categoria> getLista() throws Exception{
        
        ArrayList<Categoria> lista = new ArrayList<>();
        String SQL = "SELECT * FROM categoria";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL);
        while(rs.next()){
            Categoria c = new Categoria();
            c.setIdCategoria(rs.getInt("idCategoria"));
            c.setNomeCategoria(rs.getString("NomeCategoria"));
            lista.add(c);
        }
        this.desconectar();
        return lista;
    
    }
    public boolean gravar (Categoria c){
    
        try{
          String sql;
          this.conectar();
          if(c.getIdCategoria()==0){
              sql = "INSERT INTO categoria (nomeCategoria) VALUES (?)";
          }else{
              sql = "UPDATE categoria SET nomeCategoria = ? WHERE idCategoria=?";
          }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, c.getNomeCategoria());
            if(c.getIdCategoria()>0){ 
                pstm.setInt(2, c.getIdCategoria());
            }
            pstm.execute();
            this.desconectar();
            return true;            
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    
    }
    
      public Categoria getCarregaPorID(int idCategoria) throws Exception{
        Categoria c = new Categoria();
        String sql = "SELECT * FROM categoria WHERE idCategoria=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idCategoria);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            c.setIdCategoria(idCategoria);
            c.setNomeCategoria(rs.getString("NomeCategoria"));
        }
        this.desconectar();
        return c;
    
    }
    
        public boolean desativar(Categoria c){
    try{
        this.conectar();
        String SQL = "DELETE FROM categoria WHERE idCategoria=?";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        pstm.setInt(1, c.getIdCategoria());
        pstm.execute();
        this.desconectar();
        return true;
    } catch(Exception e){
        System.out.println(e);
        return false;
    }
}

}
