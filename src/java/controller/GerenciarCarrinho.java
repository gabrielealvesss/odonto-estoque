package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Consulta;
import model.Consulta_produto;
import model.Log;
import model.Produto;
import model.Usuario;
import persistencia.LogDAO;
import persistencia.ProdutoDAO;

public class GerenciarCarrinho extends HttpServlet {

    private static final int LIMITE_QUANTIDADE_BAIXA = 5; // Ajuste conforme necessário

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            Usuario u = (Usuario) session.getAttribute("ulogado");
            try {
                Consulta c = (Consulta) session.getAttribute("consulta");
                ArrayList<Consulta_produto> carrinho = c.getCarrinho();
                String acao = request.getParameter("acao");
                ProdutoDAO pDAO = new ProdutoDAO();

                if (acao.equals("add")) {
                    // Lógica para adicionar produtos (sem alterações)
                } else if (acao.equals("addMultiplos")) {
                    // Lógica para lidar com a adição de múltiplos produtos
                    Map<String, String[]> parametros = request.getParameterMap();

                    for (Map.Entry<String, String[]> entry : parametros.entrySet()) {
                        String chave = entry.getKey();
                        String[] valores = entry.getValue();

                        if (chave.startsWith("produtos[")) {
                            String idProduto = chave.substring(chave.indexOf("[") + 1, chave.indexOf("]"));
                            String quantidade = valores[0];

                            // Verifica se a quantidade é menor que 1
                            if (Integer.parseInt(quantidade) < 1) {
                                out.println("A quantidade deve ser pelo menos 1.");
                                return;
                            }

                            // Procura o produto no carrinho com base no ID
                            boolean produtoEncontrado = false;
                            for (Consulta_produto cp : carrinho) {
                                if (cp.getProduto().getIdProduto() == Integer.parseInt(idProduto)) {
                                    // Atualiza a quantidade do produto encontrado
                                    cp.setQtd(cp.getQtd() + Integer.parseInt(quantidade));
                                    produtoEncontrado = true;

                                    // Adiciona um alerta se a quantidade estiver abaixo do limite
                                    if (cp.getQtd() <= LIMITE_QUANTIDADE_BAIXA) {
                                        out.println("Alerta: A quantidade está ficando baixa.");
                                        LogDAO glog = new LogDAO();
                                        Log l = new Log();
                                        l.setIdUsuario(u.getIdUsuario());
                                        l.setAcao("Quantidade Baixa no Carrinho");
                                        l.setTabela("Carrinho");
                                        glog.gravarLog(l);
                                    }

                                    // Atualiza a quantidade no banco de dados
                                    pDAO.atualizarQuantidadeProduto(Integer.parseInt(idProduto), -Integer.parseInt(quantidade));
                                    break;
                                }
                            }

                            // Se o produto não foi encontrado no carrinho, adiciona um novo item
                            if (!produtoEncontrado) {
                                Produto p = pDAO.getCarregaPorID(Integer.parseInt(idProduto));
                                Consulta_produto cp = new Consulta_produto();
                                cp.setProduto(p);
                                cp.setQtd(Integer.parseInt(quantidade));
                                cp.setValor(p.getValor());
                                carrinho.add(cp);

                                // Adiciona um alerta se a quantidade estiver abaixo do limite
                                if (Integer.parseInt(quantidade) <= LIMITE_QUANTIDADE_BAIXA) {
                                    out.println("Alerta: A quantidade está ficando baixa.");
                                }

                                // Atualiza a quantidade no banco de dados
                                pDAO.atualizarQuantidadeProduto(Integer.parseInt(idProduto), -Integer.parseInt(quantidade));
                            }
                        }
                    }

                    c.setCarrinho(carrinho);
                    session.setAttribute("consulta", c);
                    response.sendRedirect("form_consulta2.jsp?acao=c");
                } else if (acao.equals("del")) {
                    // Lógica para remover produtos (sem alterações)
                }
            } catch (Exception e) {
                out.print(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
