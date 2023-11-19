
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Categoria;
import model.Fornecedor;
import model.Log;
import model.Produto;
import model.Usuario;
import persistencia.LogDAO;
import persistencia.ProdutoDAO;


public class GerenciarProduto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";
        String acao = request.getParameter("acao");

        Produto p = new Produto();
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        try {
           

            ProdutoDAO produto = new ProdutoDAO();
             if (acao.equals("gerarGrafico")) {
                if(GerenciarLogin.verificarPermissao(request, response)){
                ArrayList<Produto> dadosGrafico = produto.getDadosGrafico();
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_produto.jsp");
                request.setAttribute("dadosGrafico", dadosGrafico);
                disp.forward(request, response);
            }
            }else{
                mensagem = "Acesso Negado!";
            }

            if (acao.equals("listar")) {
                if(GerenciarLogin.verificarPermissao(request, response)){
                ArrayList<Produto> listaProduto = produto.getLista();
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_produto.jsp");
                request.setAttribute("listaProduto", listaProduto);
                disp.forward(request, response);
            }
            }else{
                mensagem = "Acesso Negado!";
            }

            if (acao.equals("alterar")) {
                if(GerenciarLogin.verificarPermissao(request, response)){
                String idProduto = request.getParameter("idProduto");
                p = produto.getCarregaPorID(Integer.parseInt(idProduto));
                if (p.getIdProduto() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_produto.jsp");
                    request.setAttribute("p", p);
                    disp.forward(request, response);
                }
                }else{
                    mensagem = "Acesso Negado!";
                }
            }

            if (acao.equals("deletar")) {
                if(GerenciarLogin.verificarPermissao(request, response)){
                String idProduto = request.getParameter("idProduto");
                p.setIdProduto(Integer.parseInt(idProduto));
                if (produto.deletar(p)) {
                    LogDAO glog = new LogDAO();
                    Log l = new Log();
                    l.setIdUsuario(u.getIdUsuario());
                    l.setAcao("Deletar");
                    l.setTabela("Estoque");
                    glog.gravarLog(l);
                    mensagem = "Produto deletado com sucesso!";
                } else {
                    mensagem = "Erro ao deletar o produto";
                }
                }else{
                    mensagem = "Acesso Negado!";
                }

            }

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar a lista de produto";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_produto.do?acao=listar';");
        out.println("</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String idProduto = request.getParameter("idProduto");
        String codigo = request.getParameter("codigo");
        String nome = request.getParameter("nome");
        String marca = request.getParameter("marca");
        String quantidade = request.getParameter("quantidade");
        String validade = request.getParameter("validade");
        String valor = request.getParameter("valor");
        String dataEntrada = request.getParameter("dataEntrada");
        String idFornecedor = request.getParameter("idFornecedor");
        String idCategoria = request.getParameter("idCategoria");

        String mensagem = "";

        Produto p = new Produto();
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        if (!idProduto.isEmpty()) {
            p.setIdProduto(Integer.parseInt(idProduto));
        }

        if ((codigo.equals("") || nome.equals("") || marca.equals("") || quantidade.equals("")
                || validade.equals("") || valor.equals("") || dataEntrada.equals(""))
                || idFornecedor.equals("") || idCategoria.equals("")) {
            mensagem = "Todos os campos obrigatórios devem ser preenchidos!";
        } else {
            p.setCodigo(Integer.parseInt(codigo));
            p.setNome(nome);
            p.setMarca(marca);
            p.setQuantidade(Integer.parseInt(quantidade));

            
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                if(!dataEntrada.isEmpty()){
                   p.setDataEntrada(dateFormat.parse(dataEntrada));
                   p.setValidade(dateFormat.parse(validade));
                }
            } catch (ParseException e) {
                mensagem = "Erro ao converter a data.";
            }
            
           
            try {
                double valorDouble = 0;
                if(!valor.isEmpty()){
                 valorDouble = Double.parseDouble(valor.replace(".", "").replace(",", "."));
                  p.setValor(valorDouble);

            }
                p.setValor(valorDouble);
            } catch (NumberFormatException e) {
                mensagem = "Erro ao converter o valor para Double.";
            }
            Fornecedor f = new Fornecedor();
            f.setIdFornecedor(Integer.parseInt(idFornecedor));
            p.setFornecedor(f);
            
            Categoria c = new Categoria();
            c.setIdCategoria(Integer.parseInt(idCategoria));
            p.setCategoria(c);

            try {
                ProdutoDAO pDAO = new ProdutoDAO();
                if (pDAO.gravar(p)) {
                   if (!idProduto.isEmpty()) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Alterar");
                        l.setTabela("Estoque");
                        glog.gravarLog(l);
                    } else {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Gravar");
                        l.setTabela("Estoque");
                        glog.gravarLog(l);
                    }
                    mensagem = "Produto gravado com sucesso!";
                } else {
                    mensagem = "Erro ao gravar no banco de dados!";

                }
            } catch (Exception e) {
                mensagem = "Erro ao executar: " + e.getMessage();
            }
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_produto.jsp';");
        out.println("</script>");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
