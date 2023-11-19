package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

public class GerenciarPerfil extends HttpServlet {

    //*consulta = venda cliente = paciente vendedor = usuario
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";
        String acao = request.getParameter("acao");

        Perfil p = new Perfil();
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        try {
            PerfilDAO pDAO = new PerfilDAO();
            if (acao.equals("listar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    ArrayList<Perfil> listaPerfis = pDAO.getLista();
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_perfil.jsp");
                    request.setAttribute("listaPerfil", listaPerfis);
                    disp.forward(request, response);
                }
            } else {
                mensagem = "Acesso Negado!";
            }
            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    String idPerfil = request.getParameter("idPerfil");
                    p = pDAO.getCarregaPorID(Integer.parseInt(idPerfil));
                    if (p.getIdPerfil() > 0) {
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_perfil.jsp");
                        request.setAttribute("p", p);
                        disp.forward(request, response);
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            }
            if (acao.equals("desativar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    String idPerfil = request.getParameter("idPerfil");
                    p.setIdPerfil(Integer.parseInt(idPerfil));
                    if (pDAO.desativar(p)) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Desativar");
                        l.setTabela("Perfil");
                        glog.gravarLog(l);
                        mensagem = "Perfil deletado com sucesso!";
                    } else {
                        mensagem = "Erro ao desativar o perfil";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            }
            if (acao.equals("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    String idPerfil = request.getParameter("idPerfil");
                    p.setIdPerfil(Integer.parseInt(idPerfil));
                    if (pDAO.deletar(p)) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Deletar");
                        l.setTabela("Perfil");
                        glog.gravarLog(l);
                        mensagem = "Perfil deletado com sucesso!";
                    } else {
                        mensagem = "Erro ao deletar o perfil";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            }


        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar a lista de perfis";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_perfis.do?acao=listar';");
        out.println("</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";

        String idPerfil = request.getParameter("idPerfil");
        String nome = request.getParameter("nome");
        String status = request.getParameter("status");

        Perfil p = new Perfil();
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        try {
            PerfilDAO pDAO = new PerfilDAO();
            if (!idPerfil.isEmpty()) {
                p.setIdPerfil(Integer.parseInt(idPerfil));
            }
            if (nome.equals("") || (nome.isEmpty()) || status.equals("") || status.isEmpty()) {
                mensagem = "Todos os campos deverão ser preenchidos";

            } else {
                p.setNome(nome);
                p.setStatus(Integer.parseInt(status));
                if (pDAO.gravar(p)) {
                    if (!idPerfil.isEmpty()) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Alterar");
                        l.setTabela("Perfil");
                        glog.gravarLog(l);
                    } else {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Gravar");
                        l.setTabela("Perfil");
                        glog.gravarLog(l);
                    }
                    mensagem = "Perfil gravado com sucesso!";
                }
            }

        } catch (Exception ex) {
            out.print(ex);
            mensagem = "Erro ao gravar o perfil no banco de dados";

        }
        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_perfis.do?acao=listar';");
        out.println("</script>");
    }
}
