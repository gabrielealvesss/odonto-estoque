
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Fornecedor;

public class FornecedorDAO extends DataBaseDAO {
    
    public FornecedorDAO () throws Exception{}
    
    public ArrayList<Fornecedor> getLista() throws Exception{
        
        ArrayList<Fornecedor> lista = new ArrayList<>();
        String SQL = "SELECT * FROM fornecedor";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL);
        while(rs.next()){
            Fornecedor f = new Fornecedor();
            f.setIdFornecedor(rs.getInt("idFornecedor"));
            f.setNome(rs.getString("nome"));
            f.setCep(rs.getString("cep"));
            f.setEndereco(rs.getString("endereco"));
            f.setTelefone(rs.getString("telefone"));
            lista.add(f);
        }
        this.desconectar();
        return lista;
    
    }
    public boolean gravar (Fornecedor f){
    
        try{
          String sql;
          this.conectar();
          if(f.getIdFornecedor()==0){
              sql = "INSERT INTO fornecedor (nome, cep, endereco, telefone) VALUES (?, ?, ?, ?)";
          }else{
              sql = "UPDATE fornecedor SET nome = ?, cep = ?, endereco = ?, telefone = ? WHERE idFornecedor = ?";
          }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, f.getNome());
            pstm.setString(2, f.getCep());
            pstm.setString(3, f.getEndereco());
            pstm.setString(4, f.getTelefone());
            if(f.getIdFornecedor()>0){ 
                pstm.setInt(5, f.getIdFornecedor());
            }
            pstm.execute();
            this.desconectar();
            return true;            
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    
    }
    
      public Fornecedor getCarregaPorID(int idFornecedor) throws Exception{
        Fornecedor f = new Fornecedor();
        String sql = "SELECT * FROM fornecedor WHERE idFornecedor = ?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idFornecedor);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            f.setIdFornecedor(rs.getInt("idFornecedor"));
            f.setNome(rs.getString("nome"));
            f.setCep(rs.getString("cep"));
            f.setEndereco(rs.getString("endereco"));
            f.setTelefone(rs.getString("telefone"));
        }
        this.desconectar();
        return f;
    
    }
    
        public boolean desativar(Fornecedor f){
    try{
        this.conectar();
        String SQL = "DELETE FROM fornecedor WHERE idFornecedor = ?";
        PreparedStatement pstm = conn.prepareStatement(SQL);
        pstm.setInt(1, f.getIdFornecedor());
        pstm.execute();
        this.desconectar();
        return true;
    } catch(Exception e){
        System.out.println(e);
        return false;
    }
}
}
