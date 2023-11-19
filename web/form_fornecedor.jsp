<%-- 
    Document   : index
    Created on : 08/09/2023, 14:35:45
    Author     : pfela
--%>

<%@page import="persistencia.FornecedorDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Fornecedor"%>
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
            <h4>Novo fornecedor</h4>

            <form action="gerenciar_fornecedor.do" method="POST">
                <input type="hidden" name="idFornecedor" value="${f.idFornecedor}" />
                <div class="form-group">
                    <label for="nome" class="control-label">Nome</label>
                    <input type="text" id="nome" name="nome" class="form-control" required="" value="${f.nome}" maxlength="45" />
                </div>
                <div class="form-group">
                    <label for="cep" class="control-label">CEP</label>
                    <input type="text" id="cep" name="cep" class="form-control" required="" value="${f.cep}" maxlength="45" />
                </div>
                <div class="form-group">
                    <label for="endereco" class="control-label">Endereço</label>
                    <input type="text" id="endereco" name="endereco" class="form-control" required="" value="${f.endereco}" maxlength="45" />
                </div>
                <div class="form-group">
                    <label for="telefone" class="control-label">Telefone</label>
                    <input type="text" id="telefone" name="telefone" class="form-control" required="" value="${f.telefone}" maxlength="45" />
                </div>
                <!-- Outros campos do formulário aqui -->
                <div class="form-group">
                    <button class="btn btn-success">Salvar</button>
                    <a href="gerenciar_fornecedor.do?acao=listar" class="btn btn-warning">Voltar</a>
                </div>
            </form>



        </div>
    </body>
</html>

