/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Consulta;
import model.Log;
import model.Paciente;
import model.Usuario;
import persistencia.ConsultaDAO;
import persistencia.LogDAO;
import persistencia.PacienteDAO;
import persistencia.UsuarioDAO;

/**
 *
 * @author Luan
 */
public class GerenciarConsulta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";
        String acao = request.getParameter("acao");

        Consulta c = new Consulta();
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        try {
            ConsultaDAO cDAO = new ConsultaDAO();
            if (acao.equals("listar")) {
                ArrayList<Consulta> listaConsulta = cDAO.getLista();
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_consulta.jsp");
                request.setAttribute("listaConsulta", listaConsulta);
                disp.forward(request, response);
            }
            if (acao.equals("alterar")) {
                if(GerenciarLogin.verificarPermissao(request, response)){
                String idConsulta = request.getParameter("idConsulta");
                c = cDAO.getCarregaPorID(Integer.parseInt(idConsulta));
                if (c.getIdConsulta() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_consulta.jsp");
                    request.setAttribute("c", c);
                    disp.forward(request, response);
                }
                }else{
                    mensagem = "Acesso Negado!";
                }
            }
            if (acao.equals("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    String idConsulta = request.getParameter("idConsulta");
                    c.setIdConsulta(Integer.parseInt(idConsulta));
                    if (cDAO.deletar(c)) {
                        mensagem = "Consulta cancelada com sucesso!";
                    } else {
                        mensagem = "Erro ao deletar a consulta";
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
        out.println("location.href='gerenciar_consulta.do?acao=listar';");
        out.println("</script>");

    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {

        String mensagem = "";

        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        try {
            String acao = request.getParameter("acao");
            String idConsulta = request.getParameter("idConsulta");
            String codigo = request.getParameter("codigo");
            String data = request.getParameter("data");
            String hora = request.getParameter("hora");
            String procedimento = request.getParameter("procedimento");
            String anamnese = request.getParameter("anamnese");
            String valorTotal = request.getParameter("valorTotal");
            String idUsuario = request.getParameter("idUsuario");
            String idPaciente = request.getParameter("idPaciente");

            ConsultaDAO cDAO = new ConsultaDAO();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");

            Consulta c = new Consulta();

            if (codigo.isEmpty() || data.isEmpty() || hora.isEmpty() || procedimento.isEmpty() || anamnese.isEmpty()
                    || valorTotal.isEmpty() || idPaciente.isEmpty()) {
                mensagem = "Os campos obrigatórios devem ser preenchidos!";
            } else {
                c.setCodigo(Integer.parseInt(codigo));
                c.setData(dateFormat.parse(data));
                c.setHora(horaFormat.parse(hora));
                c.setProcedimento(procedimento);
                c.setAnamnese(anamnese);

                double valorDouble = Double.parseDouble(valorTotal.replace(".", "").replace(",", "."));
                c.setValorTotal(valorDouble);

                PacienteDAO pDAO = new PacienteDAO();
                Paciente p = pDAO.getCarregaPorID(Integer.parseInt(idPaciente));
                c.setPaciente(p);

                UsuarioDAO uDAO = new UsuarioDAO();
                u.setIdUsuario(Integer.parseInt(idUsuario));
                Usuario vendedor = uDAO.getCarregaPorID(Integer.parseInt(idUsuario));
                c.setVendedor(vendedor);
                
                                                
                if (!idConsulta.isEmpty()) {
                            c = cDAO.getCarregaPorID(Integer.parseInt(idConsulta));
                            
                            session.setAttribute("consulta", c);
                            response.sendRedirect("form_consulta2.jsp?acao=alterar");
                            
                        } else {
                             session.setAttribute("consulta", c);
                            response.sendRedirect("form_consulta2.jsp?acao=novo");
                            LogDAO glog = new LogDAO();
                            Log l = new Log();
                            l.setIdUsuario(u.getIdUsuario());
                            l.setAcao("Consulta Criada");
                            l.setTabela("Consulta");
                            glog.gravarLog(l);
                        }

                if (idConsulta.isEmpty()) {
                    if (cDAO.gravar(c)) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Gravar");
                        l.setTabela("Consulta");
                        glog.gravarLog(l);
                        mensagem = "Consulta gravada com sucesso!";
                    } else {
                        mensagem = "Erro ao gravar no banco de dados!";
                    }
                } else {
                    c.setIdConsulta(Integer.parseInt(idConsulta));
                    if (cDAO.gravar(c)) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Alterar");
                        l.setTabela("Consulta");
                        glog.gravarLog(l);
                        mensagem = "Consulta atualizada com sucesso!";
                    } else {
                        mensagem = "Erro ao atualizar a consulta!";
                    }
                }
            }
        } catch (Exception e) {
            mensagem = "Erro ao executar: " + e.getMessage();
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_consulta.do?acao=listar';");
        out.println("</script>");
    }
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
