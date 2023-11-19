<%@page import="controller.GerenciarLogin"%>
<%@page import="model.Usuario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
 Usuario ulogado = GerenciarLogin.verificarAcesso(request, response);
 request.setAttribute("ulogado", ulogado);
%>
<div class="pull-right"> Bem-vindo, <c:if test="${ulogado!=null}">${ulogado.nome}</c:if>
    <a href="gerenciar_login.do">Sair</a>
</div>
<div class="menu">
    <ul>
    <c:if test="${ulogado!=null && ulogado.perfil!=null}">
        <c:forEach var="menu" items="${ulogado.perfil.menus}">
            <c:if test="${menu.exibir==1}">
                <li><a href="${menu.link}"> ${menu.nome} </a></li>
            </c:if>
        </c:forEach>
        
    </c:if>     
                
    </ul>
</div>

<link rel="stylesheet" href="estilos/menu.css" type="text/css"/>