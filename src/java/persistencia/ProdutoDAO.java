
package persistencia;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Categoria;
import model.Fornecedor;
import model.Produto;

public class ProdutoDAO extends DataBaseDAO {
    
    public ProdutoDAO () throws Exception{}
    
      public ArrayList<Produto> getLista() throws Exception {
    ArrayList<Produto> lista = new ArrayList<Produto>();
    String SQL = "SELECT p.*, f.nome, c.nomeCategoria FROM produto p " 
                + "INNER JOIN fornecedor f ON p.idFornecedor = f.idFornecedor " 
                + "INNER JOIN categoria c ON p.idCategoria = c.idCategoria ";


        this.conectar();
       PreparedStatement pstm = conn.prepareStatement(SQL);
       ResultSet rs = pstm.executeQuery();
       while(rs.next()){
           Produto p = new Produto();
           p.setIdProduto(rs.getInt("p.idProduto"));
           p.setCodigo(rs.getInt("p.codigo"));
           p.setNome(rs.getString("p.nome"));
           p.setMarca(rs.getString("p.marca"));
           p.setQuantidade(rs.getInt("p.quantidade"));
           p.setValidade(rs.getDate("p.validade"));
           p.setValor(rs.getDouble("p.valor"));
           p.setDataEntrada(rs.getDate("p.dataEntrada"));
           
           Fornecedor f = new Fornecedor();
          
           f.setNome(rs.getString("f.nome"));
           f.setIdFornecedor(rs.getInt("p.idFornecedor"));
           p.setFornecedor(f);
           
           Categoria c = new Categoria();
           
           c.setNomeCategoria(rs.getString("c.nomeCategoria"));
           c.setIdCategoria(rs.getInt("p.idCategoria"));
           p.setCategoria(c);
           
           lista.add(p);
       }
        this.desconectar();
        return lista;
    }
    
    
 public boolean gravar(Produto p) {
    try {
        String sql;
        this.conectar();
        if (p.getIdProduto() == 0) {
            sql = "INSERT INTO produto (codigo, nome, marca, quantidade, validade, valor, dataEntrada, idFornecedor, idCategoria) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
        } else {
            sql = "UPDATE produto SET codigo = ?, nome = ?, marca = ?, quantidade = ?, validade = ?, valor = ?, dataEntrada = ?, idFornecedor = ?, idCategoria = ?"
                    + " WHERE idProduto = ?";
        }
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, p.getCodigo());
        pstm.setString(2 , p.getNome());
        pstm.setString(3, p.getMarca());
        pstm.setInt(4, p.getQuantidade());
        pstm.setDate(5,new Date(p.getValidade().getTime()));
        pstm.setDouble(6, p.getValor());
        pstm.setDate(7, new Date(p.getDataEntrada().getTime()));
        pstm.setInt(8, p.getFornecedor().getIdFornecedor());
        pstm.setInt(9, p.getCategoria().getIdCategoria());
        if (p.getIdProduto() > 0)
            pstm.setInt(10, p.getIdProduto());
        pstm.execute();
        
        
       
        
        
        this.desconectar();
        return true;

    } catch (Exception e) {
        System.out.println(e);
        return false;
    }
}
 
 public Produto getCarregaPorID(int idProduto) throws Exception{
        Produto p = new Produto();
        String sql = "SELECT p.*, f.nome, c.nomeCategoria FROM produto p " 
                + "INNER JOIN fornecedor f ON p.idFornecedor = f.idFornecedor " 
                + "INNER JOIN categoria c ON p.idCategoria = c.idCategoria " 
                + "WHERE p.idProduto = ?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idProduto);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
           p.setIdProduto(idProduto);
           p.setCodigo(rs.getInt("p.codigo"));
           p.setNome(rs.getString("p.nome"));
           p.setMarca(rs.getString("p.marca"));
           p.setQuantidade(rs.getInt("p.quantidade"));
           p.setValidade(rs.getDate("p.validade"));
           p.setValor(rs.getDouble("p.valor"));
           p.setDataEntrada(rs.getDate("p.dataEntrada"));         
           
           Fornecedor f = new Fornecedor();
          
           f.setNome(rs.getString("f.nome"));
           f.setIdFornecedor(rs.getInt("p.idFornecedor"));
           p.setFornecedor(f);
           
           Categoria c = new Categoria();
           
           c.setNomeCategoria(rs.getString("c.nomeCategoria"));
           c.setIdCategoria(rs.getInt("p.idCategoria"));
           p.setCategoria(c);
                   
        }
        this.desconectar();
        return p;
    
    }
 
  public boolean deletar(Produto p){
        try{
            this.conectar();
            String sql = "DELETE FROM produto WHERE idProduto= ?";  
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, p.getIdProduto());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
        
}
   public void atualizarQuantidadeProduto(int idProduto, int novaQuantidade) throws SQLException, Exception {
        try {
            this.conectar();
            String sql = "UPDATE produto SET quantidade = quantidade + ? WHERE idProduto = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, novaQuantidade);
            pstm.setInt(2, idProduto);
            pstm.executeUpdate();
        } finally {
            this.desconectar();
        }
    }
   
public ArrayList<Produto> getDadosGrafico() throws Exception {
    ArrayList<Produto> dadosGrafico = new ArrayList<>();
    String sql = "SELECT p.nome AS nome_produto, SUM(cp.qtd) AS quantidade_total " +
                 "FROM produto p " +
                 "INNER JOIN consulta_produto cp ON p.idProduto = cp.idProduto " +
                 "GROUP BY p.nome " +
                 "ORDER BY quantidade_total DESC " +
                 "LIMIT 5";

    try {
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            Produto produto = new Produto();
            produto.setNome(rs.getString("nome_produto")); 
            produto.setQuantidade(rs.getInt("quantidade_total")); 
            dadosGrafico.add(produto);
        }
    } finally {
        this.desconectar();
    }

    return dadosGrafico;
}
    
}
