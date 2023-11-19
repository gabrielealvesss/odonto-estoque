/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Log;
import model.Paciente;
import model.Usuario;
import persistencia.LogDAO;
import persistencia.PacienteDAO;

/**
 *
 * @author Luan
 */
public class GerenciarPaciente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String mensagem = "";
        String acao = request.getParameter("acao");

        Paciente p = new Paciente();
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");

        try {
            PacienteDAO paciente = new PacienteDAO();
            if (acao.equals("listar")) {
                if(GerenciarLogin.verificarPermissao(request, response)){
                ArrayList<Paciente> listaPaciente = paciente.getLista();
                RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_paciente.jsp");
                request.setAttribute("listaPaciente", listaPaciente);
                disp.forward(request, response);
            }
            }else{
                mensagem = "Acesso Negado!";
            }

            if (acao.equals("alterar")) {
                if(GerenciarLogin.verificarPermissao(request, response)){
                String idPaciente = request.getParameter("idPaciente");
                p = paciente.getCarregaPorID(Integer.parseInt(idPaciente));
                if (p.getIdPaciente() > 0) {
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_paciente.jsp");
                    request.setAttribute("p", p);
                    disp.forward(request, response);
                }
            }
            }else{
                mensagem = "Acesso Negado!";
            }

            if (acao.equals("deletar")) {
                if(GerenciarLogin.verificarPermissao(request, response)){
                String idPaciente = request.getParameter("idPaciente");
                p.setIdPaciente(Integer.parseInt(idPaciente));
                if (paciente.deletar(p)) {
                    LogDAO glog = new LogDAO();
                    Log l = new Log();
                    l.setIdUsuario(u.getIdUsuario());
                    l.setAcao("Deletar");
                    l.setTabela("Paciente");
                    glog.gravarLog(l);
                    mensagem = "Paciente deletado com sucesso!";
                } else {
                    mensagem = "Erro ao deletar o paciente";
                }

            }
            }else{
                mensagem = "Acesso Negado!";
            }

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar a lista de paciente";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_paciente.do?acao=listar';");
        out.println("</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String idPaciente = request.getParameter("idPaciente");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String dataNasc = request.getParameter("dataNasc");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String orgaoE = request.getParameter("orgaoE");
        String sexo = request.getParameter("sexo");
        String nomeP = request.getParameter("nomeP");
        String nomeM = request.getParameter("nomeM");
        String endereco = request.getParameter("endereco");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String email = request.getParameter("email");
        String tipo = request.getParameter("tipo");

        String mensagem = "";
        HttpSession session = request.getSession();
        Usuario u = (Usuario) session.getAttribute("ulogado");
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Paciente p = new Paciente();
            PacienteDAO pDAO = new PacienteDAO();

            if (!idPaciente.isEmpty()) {
                p.setIdPaciente(Integer.parseInt(idPaciente));
            }

            if (nome.equals("") || telefone.equals("") || dataNasc.equals("") || cpf.equals("")
                    || rg.equals("") || orgaoE.equals("") || sexo.equals("") || nomeP.equals("")
                    || nomeM.equals("") || endereco.equals("") || cep.equals("") || bairro.equals("") || numero.equals("")
                    || complemento.equals("") || email.equals("") || tipo.equals("")) {
                mensagem = "Todos os campos obrigatórios devem ser preenchidos!";
            } else {
                p.setNome(nome);
                p.setTelefone(telefone);
                if (!dataNasc.isEmpty()) {
                    p.setDataNasc(dateFormat.parse(dataNasc));
                }
                p.setCpf(cpf);
                p.setRg(rg);
                p.setOrgaoE(orgaoE);
                p.setSexo(Integer.parseInt(sexo));
                p.setNomeP(nomeP);
                p.setNomeM(nomeM);
                p.setEndereco(endereco);
                p.setCep(cep);
                p.setBairro(bairro);
                p.setNumero(numero);
                p.setComplemento(complemento);
                p.setEmail(email);
                p.setTipo(Integer.parseInt(tipo));

                if (pDAO.gravar(p)) {
                    if (!idPaciente.isEmpty()) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Alterar");
                        l.setTabela("Paciente");
                        glog.gravarLog(l);
                    } else {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(u.getIdUsuario());
                        l.setAcao("Gravar");
                        l.setTabela("Paciente");
                        glog.gravarLog(l);
                    }
                    mensagem = "Paciente gravado com sucesso!";
                } else {
                    mensagem = "Erro ao gravar no banco de dados!";

                }
            }
        } catch (Exception e) {
            out.print(e);
            mensagem = "erro ao executar";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_paciente.jsp';");
        out.println("</script>");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
