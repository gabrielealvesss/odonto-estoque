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
import model.Perfil;
import model.Usuario;
import persistencia.LogDAO;
import persistencia.PerfilDAO;

/**
 *
 * @author Luan
 */
public class GerenciarPerfilMenu extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String mensagem = "";
        String idPerfil = request.getParameter("idPerfil");
        String acao = request.getParameter("acao");
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        try {
            PerfilDAO pDAO = new PerfilDAO();
            Perfil p = new Perfil();
            if (acao.equals("gerenciar")) {
                p = pDAO.getCarregaPorID(Integer.parseInt(idPerfil));
                if (p.getIdPerfil() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_perfil_menu.jsp");
                    request.setAttribute("perfilv", p);
                    disp.forward(request, response);
                } else {
                    mensagem = "perfil não encontrado";
                }
            }

            if (acao.equals("desvincular")) {
                String idMenu = request.getParameter("idMenu");
                if (idMenu.equals("")) {
                    mensagem = "O campo idMenu deverá ser selecionado";

                } else {
                    if (pDAO.desvincular(Integer.parseInt(idPerfil), Integer.parseInt(idMenu))) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Desvincular");
                        l.setTabela("Perfil Menu");
                        glog.gravarLog(l);
                        mensagem = "Desvinculado com sucesso!";

                    } else {
                        mensagem = "Erro ao desvincular";
                    }
                }
            }

        } catch (Exception e) {
            out.print(e);
            mensagem = "erro ao executar";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_perfil_menu.do?acao=gerenciar&idPerfil=" + idPerfil + "';");
        out.println("</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";
        String idPerfil = request.getParameter("idPerfil");
        String idMenu = request.getParameter("idMenu");
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        try {
            if (idPerfil.equals("") || idMenu.equals("")) {
                mensagem = "Campos obrigatórios deverão ser selecionados";

            } else {
                PerfilDAO pDAO = new PerfilDAO();
                if (pDAO.vincular(Integer.parseInt(idPerfil), Integer.parseInt(idMenu))) {
                    LogDAO glog = new LogDAO();
                    Log l = new Log();
                    l.setIdUsuario(u.getIdUsuario());
                    l.setAcao("Vincular");
                    l.setTabela("Perfil Menu");
                    glog.gravarLog(l);
                    mensagem = "Vinculado com sucesso!";
                } else {
                    mensagem = "Erro ao vincular ao perfil";
                }
            }
        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_perfil_menu.do?acao=gerenciar&idPerfil=" + idPerfil + "';");
        out.println("</script>");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
