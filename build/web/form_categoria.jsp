<%-- 
    Document   : index
    Created on : 08/09/2023, 14:35:45
    Author     : pfela
--%>

<%@page import="persistencia.CategoriaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, 
              user-scalable=no", name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-fluid">

            <%@include file="banner.jsp" %>
            <%@include file="menu.jsp" %>
            <h4>Nova categoria</h4>

            <form action="GerenciarCategoria.do" method="POST">
                <input type="hidden" name="idCategoria" value="${c.idCategoria}" />
                <div class="form-group">
                    <label for="nomeCategoria" class="control-label">Nome</label>
                    <input type="text" id="nomeCategoria" name="nomeCategoria" class="form-control" required="" value="${c.nomeCategoria}" maxlength="45" />
                </div>
                <!-- Outros campos do formulário aqui -->
                <div class="form-group">
                    <button class="btn btn-success">Salvar</button>
                    <a href="GerenciarCategoria.do?acao=listar" class="btn btn-warning">Voltar</a>
                </div>
            </form>



        </div>
    </body>
</html>

