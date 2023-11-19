/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Log;
import model.Menu;
import model.Usuario;
import persistencia.LogDAO;
import persistencia.MenuDAO;


public class GerenciarMenu extends HttpServlet {

 

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        PrintWriter out = response.getWriter();
        String mensagem = "";
       
       
        String acao = request.getParameter("acao");
        String idMenu = request.getParameter("idMenu");
       
        Menu m = new Menu();
         HttpSession session = request.getSession();
      Usuario u = (Usuario) session.getAttribute("ulogado");
       
        try{
           
            MenuDAO mDAO = new MenuDAO();
            if(acao.equals("alterar")){
              if(GerenciarLogin.verificarPermissao(request, response)){
                m = mDAO.getCarregaPorID(Integer.parseInt(idMenu));
                if(m.getIdMenu()>0){
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_menu.jsp");
                    request.setAttribute("menu", m);
                    disp.forward(request, response);                  
                }else{
                    mensagem = "O Menu não foi encontrado.";
                }
            }else{
                  mensagem = "Acesso Negado!";
              }
        }
            if(acao.equals("deletar")){
                if(GerenciarLogin.verificarPermissao(request, response)){
                m.setIdMenu(Integer.parseInt(idMenu));
                if(mDAO.deletar(m)){
                    LogDAO glog = new LogDAO();
                    Log l = new Log();
                    l.setIdUsuario(u.getIdUsuario());
                    l.setAcao("Deletar");
                    l.setTabela("Menu");
                    glog.gravarLog(l);
                    mensagem = "Menu excluido com sucesso!";
                   
                }else{
                    mensagem = "Erro ao excluir menu. Este menu está associado a um perfil e não pode ser DELETADO!";
                }
                }else{
                  mensagem = "Acesso Negado!";
               
            }
               
            }
        }catch(Exception e){
            out.print(e);
            mensagem = "Erro ao executar!";  
        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" +mensagem+ "');");
        out.println("location.href='listar_menu.jsp';");
        out.println("</script>");
       
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        PrintWriter out = response.getWriter();
       
                String mensagem = "";
       
        String idMenu = request.getParameter("idMenu");
        String nome = request.getParameter("nome");
        String link = request.getParameter("link");
        String exibir = request.getParameter("exibir");
        String status = request.getParameter("status");
       
       
        Menu m = new Menu();
          HttpSession session = request.getSession();
      Usuario u = (Usuario) session.getAttribute("ulogado");
       
        try{
            MenuDAO mDAO = new MenuDAO();
            if(!idMenu.isEmpty()){
                m.setIdMenu(Integer.parseInt(idMenu));          
            }
            if(nome.equals("") || link.equals("") || exibir.equals("") || status.equals("")){
                    mensagem = "Os campos obrigatórios devem ser preenchidos!";

                }else{
                m.setNome(nome);
                m.setLink(link);
                m.setExibir(Integer.parseInt(exibir));
                m.setStatus(Integer.parseInt(status));

                if(mDAO.gravar(m)){
                    if(!idMenu.isEmpty()){
                    LogDAO glog = new LogDAO();
                    Log l = new Log();
                    l.setIdUsuario(u.getIdUsuario());
                    l.setAcao("Alterar ");
                    l.setTabela("Menu");
                    glog.gravarLog(l);
                    }else{
                    LogDAO glog = new LogDAO();
                    Log l = new Log();
                    l.setIdUsuario(u.getIdUsuario());
                    l.setAcao("Gravar");
                    l.setTabela("Menu");
                    glog.gravarLog(l);
                }
                    mensagem = "Menu gravado com sucesso!";
                   
                }else{
                    mensagem = "Erro ao gravar o menu!";
                }

            }

     
    }catch (Exception e){
        out.print(e);
        mensagem = "Erro ao executar!";
       
}
        out.println("<script type='text/javascript'>");
        out.println("alert('" +mensagem+ "');");
        out.println("location.href='listar_menu.jsp';");
        out.println("</script>");
       
    }


 
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}