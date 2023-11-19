<%-- 
    Document   : form_usuario
    Created on : 26/09/2023, 09:36:17
    Author     : Luan
--%>


<%@page import="model.Consulta_produto"%>
<%@page import="persistencia.ConsultaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="persistencia.PacienteDAO"%>
<%@page import="model.Paciente"%>
<%@page import="model.Consulta"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css"/>
        <title>Cadastro de Produto</title>
    </head>
    <body>
        <div class="container">
            <%@include file="banner.jsp"%>
            <%@include file="menu.jsp" %>
            <h1>Cadastrar Produto</h1>

            <form action="gerenciar_consulta.do" method="POST">
                <input type="hidden" name="idConsulta" value="${c.idConsulta}"/>
                <div class="row">
                    <div class="form-group col-sm-6">
                        <label form="codigo" class="control-label">Codigo da consulta</label>
                        <input type="text" class="form-control" id="codigo" name ="codigo" value="${c.codigo}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="data" class="control-label">Data da consulta</label>
                        <input type="date" class="form-control" id="data" name="data" value="${c.data}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="hora" class="control-label">Hora da consulta</label>
                        <input type="time" class="form-control" id="hora" name ="hora" value="${c.hora}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label for="procedimento" class="control-label">procedimento</label>
                        <input type="text" class="form-control" id="procedimento" name="procedimento" value="${c.procedimento}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="anamnese" class="control-label">Anamnese</label>
                        <input type="text" class="form-control" id="anamnese" name ="anamnese" value="${c.anamnese}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label for="valorTotal" class="control-label">Valor do produto</label>
                        <fmt:setLocale value="pt_BR"/>
                        <input type="text" class="form-control" id="valorTotal" name="valorTotal" value="<fmt:formatNumber pattern="#,##0.00" value="${c.valorTotal}"/>" required="" maxlength="45"/>
                    </div>

                    <div class="form-group col-sm-6">
                        <label form="paciente" class="control-label">Paciente</label>
                        <select class="form-control" name="idPaciente" required="">
                            <option value="">Selecione o Paciente</option>
                            <jsp:useBean class="persistencia.PacienteDAO" id="paciente"/>
                            <c:forEach var="p" items="${paciente.lista}">
                                <option value="${p.idPaciente}"
                                        <c:if test="${p.idPaciente==c.paciente.idPaciente}">selected=""</c:if>
                                        >   ${p.nome}</option>
                            </c:forEach>
                        </select>    
                    </div>
                             <div class="form-group col-sm-6">
                        <label form="usuario" class="control-label">Usuario</label>
                        <select class="form-control" name="idUsuario" required="">
                            <option value="">Selecione o Usuario</option>
                            <jsp:useBean class="persistencia.UsuarioDAO" id="usuario"/>
                            <c:forEach var="u" items="${usuario.lista}">
                                <option value="${u.idUsuario}"
                                        <c:if test="${u.idUsuario==c.vendedor.idUsuario}">selected=""</c:if>
                                        >   ${u.nome}</option>
                            </c:forEach>
                        </select>    
                    </div>
                            
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
                            
                    <br>
                    <div class="row">
                        <button class="btn btn-success">Gravar</button>
                        <a href="listar_consulta.jsp" class="btn btn-warning">Voltar</a>
                    </div>
                </div>

            </form>
        </div>
    </body>
</html>