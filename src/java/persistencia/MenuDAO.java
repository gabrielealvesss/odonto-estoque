/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Menu;

/**
 *
 * @author Gabriele
 */
public class MenuDAO extends DataBaseDAO {
   
    public MenuDAO() throws Exception{}
   
    public boolean gravar (Menu m){
        try{
           
            String sql;
            this.conectar();
            if(m.getIdMenu()==0){
                sql = "INSERT INTO menu (nome, link, exibir, status) VALUES (?, ?, ?, ? )";
               
            }else {
                sql  = "UPDATE menu SET nome=?, link=?, exibir= ?, status=? WHERE idMenu=?";
               
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,m.getNome());
            pstm.setString(2,m.getLink());
            pstm.setInt(3,m.getExibir());
            pstm.setInt(4,m.getStatus());
           
            if(m.getIdMenu()> 0 ){
                pstm.setInt(5,m.getIdMenu());
               
            }
            pstm.execute();
            this.desconectar();
            return true;
           
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
   
    public ArrayList<Menu> getLista() throws Exception{
           
        ArrayList<Menu> lista = new ArrayList<Menu>();
        String sql = "SELECT * FROM menu";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while(rs.next()){
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("idMenu"));
            m.setNome(rs.getString("nome"));
            m.setLink(rs.getString("link"));
            m.setExibir(rs.getInt("exibir"));
            m.setStatus(rs.getInt("status"));
           
            lista.add(m);                    
        }
        this.desconectar();
        return lista;
       
           
       
    }
   
    public Menu getCarregaPorID(int idMenu) throws Exception{
       
        Menu m = new Menu();
        String sql= "SELECT * FROM menu WHERE idMenu = ?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idMenu);
        ResultSet rs = pstm.executeQuery();
       
        if (rs.next()){
            m.setIdMenu(idMenu);
            m.setNome(rs.getString("nome"));
            m.setLink(rs.getString("link"));
            m.setExibir(rs.getInt("exibir"));
            m.setStatus(rs.getInt("status"));
           
        }
       
        this.desconectar();
        return m;
       
       
    }
   
    public boolean deletar(Menu m){
       
        try{
            String sql = "DELETE FROM menu WHERE idMenu=?";    
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,m.getIdMenu());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}