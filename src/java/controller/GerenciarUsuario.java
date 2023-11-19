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
import model.Perfil;
import model.Usuario;
import persistencia.LogDAO;
import persistencia.UsuarioDAO;

public class GerenciarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet gerenciar_usuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet gerenciar_usuario at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Usuario ulogado = (Usuario) session.getAttribute("ulogado");

        Usuario u = new Usuario();

        try {
            UsuarioDAO usuario = new UsuarioDAO();
            if (GerenciarLogin.verificarPermissao(request, response)) {
                if (acao.equals("listar")) {
                    ArrayList<Usuario> listaCategoria = usuario.getLista();
                    RequestDispatcher disp = getServletContext().getRequestDispatcher("/listar_usuario.jsp");
                    request.setAttribute("listaUsuario", listaCategoria);
                    disp.forward(request, response);
                }
            } else {
                mensagem = "Acesso Negado!";
            }

            if (acao.equals("alterar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    String idUsuario = request.getParameter("idUsuario");
                    u = usuario.getCarregaPorID(Integer.parseInt(idUsuario));
                    if (u.getIdUsuario() > 0) {
                        RequestDispatcher disp = getServletContext().getRequestDispatcher("/form_usuario.jsp");
                        request.setAttribute("u", u);
                        disp.forward(request, response);
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }
            }

            if (acao.equals("deletar")) {
                if (GerenciarLogin.verificarPermissao(request, response)) {
                    String idUsuario = request.getParameter("idUsuario");
                    u.setIdUsuario(Integer.parseInt(idUsuario));
                    if (usuario.deletar(u)) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(ulogado.getIdUsuario());
                        l.setAcao("Deletar");
                        l.setTabela("Usuario");
                        glog.gravarLog(l);
                        mensagem = "Usuario deletado com sucesso!";
                    } else {
                        mensagem = "Erro ao deletar usuario";
                    }
                } else {
                    mensagem = "Acesso Negado!";
                }

            }

        } catch (Exception e) {
            out.print(e);
            mensagem = "Erro ao executar!!!";
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='gerenciar_usuario.do?acao=listar';");
        out.println("</script>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String idUsuario = request.getParameter("idUsuario");
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String status = request.getParameter("status");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String sexo = request.getParameter("sexo");
        String nomeP = request.getParameter("nomeP");
        String nomeM = request.getParameter("nomeM");
        String dataNasc = request.getParameter("dataNasc");
        String matricula = request.getParameter("matricula");
        String endereco = request.getParameter("endereco");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String cargo = request.getParameter("cargo");
        String orgaoE = request.getParameter("orgaoE");
        String foto = request.getParameter("foto");
        String idPerfil = request.getParameter("idPerfil");

        String mensagem = "";

        Usuario u = new Usuario();

        if (!idUsuario.isEmpty()) {
            u.setIdUsuario(Integer.parseInt(idUsuario));
        }

        if (nome.equals("") || login.equals("") || senha.equals("") || status.equals("")
                || cpf.equals("") || rg.equals("") || sexo.equals("") || nomeP.equals("")
                || nomeM.equals("") || dataNasc.equals("") || matricula.equals("")
                || endereco.equals("") || cep.equals("") || numero.equals("")
                || cargo.equals("") || orgaoE.equals("")
                || idPerfil.equals("")) {
            mensagem = "Todos os campos obrigatórios devem ser preenchidos!";
        } else {
            u.setNome(nome);
            u.setLogin(login);
            u.setSenha(senha);
            u.setStatus(Integer.parseInt(status));
            u.setCpf(cpf);
            u.setRg(rg);
            u.setSexo(Integer.parseInt(sexo));
            u.setNomeP(nomeP);
            u.setNomeM(nomeM);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataNascimento = dateFormat.parse(dataNasc);
                u.setDataNasc(dataNascimento);
            } catch (ParseException e) {
                mensagem = "Erro ao converter a data de nascimento.";
            }

            u.setMatricula(Integer.parseInt(matricula));
            u.setEndereco(endereco);
            u.setCep(cep);
            u.setBairro(bairro);
            u.setNumero(numero);
            u.setComplemento(complemento);
            u.setCargo(cargo);
            u.setOrgaoE(orgaoE);
            u.setFoto(foto);
            u.setIdPerfil(Integer.parseInt(idPerfil));

            Perfil p = new Perfil();
            p.setIdPerfil(Integer.parseInt(idPerfil));
            u.setPerfil(p);

            try {
                HttpSession session = request.getSession();
                Usuario ulogado = (Usuario) session.getAttribute("ulogado");
                UsuarioDAO uDAO = new UsuarioDAO();
                if (uDAO.gravar(u)) {
                    if (!idUsuario.isEmpty()) {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(ulogado.getIdUsuario());
                        l.setAcao("Alterar");
                        l.setTabela("Usuario");
                        glog.gravarLog(l);
                    } else {
                        LogDAO glog = new LogDAO();
                        Log l = new Log();
                        l.setIdUsuario(ulogado.getIdUsuario());
                        l.setAcao("Gravar");
                        l.setTabela("Usuario");
                        glog.gravarLog(l);
                    }
                    mensagem = "Usuário gravado com sucesso!";
                } else {
                    mensagem = "Erro ao gravar no banco de dados!";

                }
            } catch (Exception e) {
                mensagem = "Erro ao executar: " + e.getMessage();
            }
        }

        out.println("<script type='text/javascript'>");
        out.println("alert('" + mensagem + "');");
        out.println("location.href='listar_usuario.jsp';");
        out.println("</script>");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
