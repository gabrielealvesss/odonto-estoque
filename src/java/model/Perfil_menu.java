
package model;
public class Perfil_menu {
    private int idPerfil;
    private int idMenu;
    private Perfil perfil;
    private Menu menu;

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    public Perfil_menu(){}

    public Perfil_menu(int idPerfil, int idMenu, Perfil perfil, Menu menu) {
        this.idPerfil = idPerfil;
        this.idMenu = idMenu;
        this.perfil = perfil;
        this.menu = menu;
    }
    
    public String toString(){
        return "Perfil: " + getIdPerfil();
    }
}
