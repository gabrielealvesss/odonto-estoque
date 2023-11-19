package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Categoria;
import model.Log;
import model.Usuario;
import persistencia.CategoriaDAO;
import persistencia.LogDAO;

@WebServlet(name = "GerenciarCategoria", urlPatterns = {"/GerenciarCategoria.do"})
public class GerenciarCategoria extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GerenciarCategoria</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Servlet GerenciarCategoria at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";
        String acao = request.getParameter("acao");

        Categoria c = new Categoria();
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        try {
            CategoriaDAO cDAO = new CategoriaDAO();
            if (acao.equals("listar")) {
                ArrayList<Categoria> listaCategoria = cDAO.getLista();
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_categoria.jsp");
                request.setAttribute("listaCategoria", listaCategoria);
                disp.forward(request, response);
            }
            if (acao.equals("alterar")) {
                String idCategoria = request.getParameter("idCategoria");
                c = cDAO.getCarregaPorID(Integer.parseInt(idCategoria));
                if (c.getIdCategoria() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_categoria.jsp");
                    request.setAttribute("c", c);
                    disp.forward(request, response);
                }

            }
            if (acao.equals("desativar")) {
                String idCategoria = request.getParameter("idCategoria");
                c.setIdCategoria(Integer.parseInt(idCategoria));
                if (cDAO.desativar(c)) {
                    LogDAO glog = new LogDAO();
                    Log l = new Log();
                    l.setIdUsuario(u.getIdUsuario());
                    l.setAcao("Deletar");
                    l.setTabela("Categoria");
                    glog.gravarLog(l);
                    mensagem = "Categoria deletada com sucesso!";
                } else {
                    mensagem = "Erro ao deletar esta categoria!";
                }

            }

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar uma lista de categoria";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='GerenciarCategoria.do?acao=listar';");
        out.println("</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idCategoria = request.getParameter("idCategoria");
        String nomeCategoria = request.getParameter("nomeCategoria");
        String mensagem = "";

        Categoria c = new Categoria();
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        try {
            if (!idCategoria.isEmpty()) {
                c.setIdCategoria(Integer.parseInt(idCategoria));
            }
            CategoriaDAO cDAO = new CategoriaDAO();

            if (nomeCategoria.equals("") || nomeCategoria.isEmpty()) {
                mensagem = "Todos os campos deverão ser preenchidos";
            } else {
                c.setNomeCategoria(nomeCategoria);
                if (cDAO.gravar(c)) {
                    if (!idCategoria.isEmpty()) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Alterar");
                        l.setTabela("Categoria");
                        glog.gravarLog(l);
                    } else {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Gravar");
                        l.setTabela("Categoria");
                        glog.gravarLog(l);
                    }
                    mensagem = "Categoria gravada com sucesso!";
                }
            }
        } catch (Exception ex) {
            out.print(ex);
            mensagem = "Erro ao gravar a categoria no banco de dados";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='GerenciarCategoria.do?acao=listar';");
        out.println("</script>");
    }

}
