

<%@page import="persistencia.PerfilDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Perfil"%>
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
            <h4>Novo Perfil</h4>
           
            <form action="gerenciar_perfil.do" method="POST">
                <input type="hidden" name="idPerfil" name="idPerfil" value="${p.idPerfil}"/>
                <div class="row">
                    <div class="form-group col-sm-6">
                        <label form="nome" class="control-label">Nome</label>
                        <input type="text" id="nome" name="nome" class="form-control" required="" value="${p.nome}" maxlength="45"/>
                                      <div class="form-group col-sm-6">
                        <label form="status" class="control-label">Status</label>
                        <select class="form-control" name="status">
                            <option value="1"<c:if test="${p.status==1}"> selected=""</c:if>>Ativo</option>
                            <option value="2"<c:if test="${p.status==2}"> selected=""</c:if>>Inativo</option>
                            </select>    
                        </div>
                    <div class="row">
                        <div class="form-group col-sm-8">
                            <button class="btn btn-success">Gravar</button>
                            <a href="gerenciar_perfil.do?acao=listar" class="btn btn-warning">Voltar</a>
                        </div>
                    </div>
            </form>    
                  
            
        </div>
    </body>
</html>

