
package model;

import java.util.ArrayList;

public class Perfil {
   private int idPerfil;
    private int status;
    private String nome; 

    
    private ArrayList<Menu> menus;
    private ArrayList<Menu> naoMenus;
    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
     
    public String toString(){
    return "nome: " + getNome();
    }

 
    public ArrayList<Menu> getMenus() {
        return menus;
    }

    public void setMenus(ArrayList<Menu> menus) {
        this.menus = menus;
    }

    public ArrayList<Menu> getNaoMenus() {
        return naoMenus;
    }

    public void setNaoMenus(ArrayList<Menu> naoMenus) {
        this.naoMenus = naoMenus;
    }

    public Perfil(){}
    
    public Perfil(int idPerfil, int status, String nome) {
        this.idPerfil = idPerfil;
        this.status = status;
        this.nome = nome;
    }
   
    
}
