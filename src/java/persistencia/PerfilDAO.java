/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.Menu;
import model.Perfil;

/**
 *
 * @author pfela
 */
public class PerfilDAO extends DataBaseDAO{
    
    public PerfilDAO() throws Exception{}
    
    public ArrayList<Perfil> getLista() throws Exception{
        
        ArrayList<Perfil> lista = new ArrayList<Perfil>();
        String SQL = "SELECT * FROM perfil";
        this.conectar();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQL);
        while(rs.next()){
            Perfil p = new Perfil();
            p.setIdPerfil(rs.getInt("idPerfil"));
            p.setNome(rs.getString("nome"));
            p.setStatus(rs.getInt("status"));
            lista.add(p);
        }
        this.desconectar();
        return lista;
    
    }
    public boolean gravar (Perfil p){
    
        try{
          String sql;
          this.conectar();
          if(p.getIdPerfil()==0){
              sql = "INSERT INTO perfil (nome,status) VALUES (?,?)";
          }else{
              sql = "UPDATE perfil SET nome=?, status=? WHERE idPerfil=?";
          }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, p.getNome());
            pstm.setInt(2, p.getStatus());
            if(p.getIdPerfil()>0){
                pstm.setInt(3,p.getIdPerfil());
            }
            pstm.execute();
            this.desconectar();
            return true;            
            
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
        
    
    }
    
    public Perfil getCarregaPorID(int idPerfil) throws Exception{
        Perfil p = new Perfil();
        String sql = "SELECT * FROM perfil WHERE idPerfil=?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1,idPerfil);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            p.setIdPerfil(idPerfil);
            p.setNome(rs.getString("nome"));
            p.setStatus(rs.getInt("status"));
            p.setMenus(menusVinculadosPorPerfil(idPerfil));
            p.setNaoMenus(menusNaoVinculadosPorPerfil(idPerfil));
        }
        this.desconectar();
        return p;
    
    }
    
    
   public boolean deletar(Perfil p){
       
        try{
            String sql = "DELETE FROM perfil WHERE idPerfil=?";    
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,p.getIdPerfil());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    
    
    public boolean desativar(Perfil p){
        
        try{
            this.conectar();
            String SQL = "UPDATE perfil SET status=2 WHERE idPerfil=?";
            PreparedStatement pstm = conn.prepareStatement(SQL);
            pstm.setInt(1, p.getIdPerfil());
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    
    }
    
    
    public ArrayList<Menu> menusVinculadosPorPerfil(int idPerfil) throws Exception{
        
        ArrayList<Menu> lista = new ArrayList<Menu> ();
        String sql = "SELECT m.* FROM perfil_menu as pm, menu as m "
                + "WHERE pm.idMenu = m.idMenu AND pm.idPerfil = ?";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idPerfil);
        ResultSet rs = pstm.executeQuery();
        
        while(rs.next()){
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("m.idMenu"));
            m.setNome(rs.getString("m.nome"));
            m.setLink(rs.getString("m.link"));
            m.setStatus(rs.getInt("m.status"));
            m.setExibir(rs.getInt("m.exibir"));
            lista.add(m);
        }
        
        this.desconectar();
        return lista;
    }
    
    public ArrayList<Menu> menusNaoVinculadosPorPerfil(int idPerfil) throws Exception{
        
        ArrayList<Menu> lista = new ArrayList<Menu> ();
        String sql = "SELECT m.* FROM  menu as m "
                + "WHERE m.idMenu NOT IN ( SELECT pm.idMenu FROM perfil_menu as pm WHERE pm.idPerfil=?)";
        this.conectar();
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, idPerfil);
        ResultSet rs = pstm.executeQuery();
        
        while(rs.next()){
            Menu m = new Menu();
            m.setIdMenu(rs.getInt("m.idMenu"));
            m.setNome(rs.getString("m.nome"));
            m.setLink(rs.getString("m.link"));
            m.setStatus(rs.getInt("m.status"));
            m.setExibir(rs.getInt("m.exibir"));
            lista.add(m);
        }
        
        this.desconectar();
        return lista;
    }
    
    public boolean vincular(int idPerfil, int idMenu){
        
        try{
            String sql = "INSERT INTO perfil_menu (idPerfil, idMenu) VALUES (?,?)";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idPerfil);
            pstm.setInt(2, idMenu);
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public boolean desvincular (int idPerfil, int idMenu){
        try{
            String sql = "DELETE FROM perfil_menu WHERE idPerfil=? AND idMenu=?";
            this.conectar();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idPerfil);
            pstm.setInt(2, idMenu);
            pstm.execute();
            this.desconectar();
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
            
            
    }
}
