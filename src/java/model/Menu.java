
package model;

public class Menu {
    private int idMenu;
    private int status;
    private int exibir;
    private String nome;
    private String link;

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getExibir() {
        return exibir;
    }

    public void setExibir(int exibir) {
        this.exibir = exibir;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Menu(){}

    public Menu(int idMenu, int status, int exibir, String nome, String link) {
        this.idMenu = idMenu;
        this.status = status;
        this.exibir = exibir;
        this.nome = nome;
        this.link = link;
    }
    
    public String toString(){
        return "nome" + getNome();
    }
}
