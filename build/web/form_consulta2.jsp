<%@page import="persistencia.ConsultaDAO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Consulta_produto"%>
<%@page import="persistencia.PacienteDAO"%>
<%@page import="model.Paciente"%>
<%@page import="model.Consulta"%>
<%@page import="model.Log"%>
<%@page import="model.Usuario"%>
<%@page import="persistencia.LogDAO"%>
<%@page import="persistencia.ProdutoDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css"/>
        <title>Cadastrar Nova Consulta</title>
    </head>
    <body>
        <div class="container">
            <%@include file="banner.jsp"%>
            <%@include file="menu.jsp" %>
            <h1>Cadastrar Consulta</h1>

            <%
                Consulta c = new Consulta();
                c = (Consulta) session.getAttribute("consulta");
                Paciente p = new Paciente();
                Usuario u = (Usuario) session.getAttribute("ulogado");

                try {
                    String acao = request.getParameter("acao");
                    PacienteDAO pDAO = new PacienteDAO();
                    if (acao.equals("novo")) {
                        c.setVendedor(u);
                        c.setCarrinho(new ArrayList<Consulta_produto>());
                        session.setAttribute("consulta", c);
                    } else if (acao.equals("alterar")) {
                        //int idConsulta = Integer.parseInt(request.getParameter("idConsulta"));//
                        ConsultaDAO cDAO = new ConsultaDAO();
                        c = cDAO.getCarregaPorID(c.getIdConsulta());
                        
                        session.setAttribute("consulta", c);
                    
                    }else{
                        c = (Consulta) session.getAttribute("consulta");
                        
                    }
                }catch (Exception e) {
                    out.print("Erro:" + e);
                }
            %>
        

            Vendedor: <%=c.getVendedor().getNome()%>
            <br/>
            Cliente: <%=c.getPaciente().getNome()%>
            <br />
            <h4> Catálogo: (<%= c.getCarrinho().size()%> itens do carrinho)</h4>

            <form id="addProdutosForm" action="gerenciar_carrinho.do" method="POST">
                <input type="hidden" name="acao" value="addMultiplos">

                <jsp:useBean class="persistencia.ProdutoDAO" id="produto"/>
                <c:forEach var="p" items="${produto.lista}">
                    <div id="prod${p.idProduto}">
                        ${p.nome}
                        <input type="number" name="quantidade_${p.idProduto}" value="1" style="width: 40px">
                    </div>
                </c:forEach>

                <button type="button" class="btn btn-default" onclick="adicionarAoCarrinho()">Adicionar ao Carrinho</button>
            </form>

            <a href="listar_paciente.jsp" class="btn btn-warning">Cancelar</a>
            <a href="form_finalizar_consulta.jsp" class="btn btn-success">Finalizar consulta</a>

            <script>
                function adicionarAoCarrinho() {
                    var form = document.getElementById("addProdutosForm");
                    var produtos = document.querySelectorAll('[id^="prod"]');
                    
                    produtos.forEach(function (produto) {
                        var idProduto = produto.id.replace("prod", "");
                        var quantidade = document.querySelector('input[name="quantidade_' + idProduto + '"]').value;
                        if (quantidade > 0) {
                            var input = document.createElement("input");
                            input.type = "hidden";
                            input.name = "produtos[" + idProduto + "]";
                            input.value = quantidade;
                            form.appendChild(input);
                        }
                    });

                    form.submit();
                }
            </script>
        </div>
    </body>
</html>
