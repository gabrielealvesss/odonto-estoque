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
import model.Consulta;
import model.Fornecedor;
import model.Log;
import model.Usuario;
import persistencia.FornecedorDAO;
import persistencia.LogDAO;

/**
 *
 * @author Luan
 */
public class GerenciarFornecedor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";
        String acao = request.getParameter("acao");

        Fornecedor f = new Fornecedor();
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");
        try {
            FornecedorDAO fDAO = new FornecedorDAO();
            if (GerenciarLogin.verificarPermissao(request, response)) {
            if (acao.equals("listar")) {
                ArrayList<Fornecedor> listaFornecedor = fDAO.getLista();
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_fornecedor.jsp");
                request.setAttribute("listaFornecedor", listaFornecedor);
                disp.forward(request, response);
            }
            }else{
                mensagem = "Acesso Negado!";
            }
            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                String idFornecedor = request.getParameter("idFornecedor");
                f = fDAO.getCarregaPorID(Integer.parseInt(idFornecedor));
                if (f.getIdFornecedor() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_fornecedor.jsp");
                    request.setAttribute("f", f);
                    disp.forward(request, response);
                }
            }
            }else{
                mensagem = "Acesso Negado!";
            }
            if (acao.equals("desativar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                String idFornecedor = request.getParameter("idFornecedor");
                f.setIdFornecedor(Integer.parseInt(idFornecedor));
                if (fDAO.desativar(f)) {
                    LogDAO glog = new LogDAO();
                    Log l = new Log();
                    l.setIdUsuario(u.getIdUsuario());
                    l.setAcao("Deletar");
                    l.setTabela("Fornecedor");
                    glog.gravarLog(l);
                    mensagem = "Forncedor deletado com sucesso!";
                } else {
                    mensagem = "Erro ao deletar o fornecedor";
                }

            }else{
                mensagem = "Acesso Negado!";
            }
            }

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar a lista de Fornecedor";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_fornecedor.do?acao=listar';");
        out.println("</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String idFornecedor = request.getParameter("idFornecedor");
        String nome = request.getParameter("nome");
        String cep = request.getParameter("cep");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String mensagem = "";

        Fornecedor f = new Fornecedor();
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        try {
            if (!idFornecedor.isEmpty()) {
                f.setIdFornecedor(Integer.parseInt(idFornecedor));
            }
            FornecedorDAO fDAO = new FornecedorDAO();

            if (nome.equals("") || cep.equals("") || endereco.equals("") || telefone.equals("")) {
                mensagem = "Todos os campos deverão ser preenchidos";
            } else {
                f.setNome(nome);
                f.setCep(cep);
                f.setEndereco(endereco);
                f.setTelefone(telefone);
                if (fDAO.gravar(f)) {
                    if (!idFornecedor.isEmpty()) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Alterar");
                        l.setTabela("Fornecedor");
                        glog.gravarLog(l);
                    } else {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Gravar");
                        l.setTabela("Fornecedor");
                        glog.gravarLog(l);
                    }

                    mensagem = "Fornecedor gravado com sucesso!";
                }
            }
        } catch (Exception ex) {
            out.print(ex);
            mensagem = "Erro ao gravar o fornecedor no banco de dados";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_fornecedor.do?acao=listar';");
        out.println("</script>");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
