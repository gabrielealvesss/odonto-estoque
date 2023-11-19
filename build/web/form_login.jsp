<%-- 
    Document   : form_login
    Created on : 22/10/2023, 21:35:36
    Author     : Luan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1,
              user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css"/>
    </head>
    <body>
        <h1>Formulário de Login</h1>
        <% 
        String mensagem = (String)request.getSession().getAttribute("mensagem");
        if(mensagem !=null){
            request.getSession().removeAttribute("mensagem");
        
        %>
        <div class="alert alert-info"><%=mensagem%></div>
        <%
        
        } 
        
        %>
        <form action="gerenciar_login.do" method="POST">
            
            <div class="row">
                <div class="form-group col-sm-8">
                    <label for="login" class="control-label">Login</label>
                    <input type="text" name="login" id="login" value="" required="">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-sm-8">
                    <label for="senha" class="control-label">Senha</label>
                    <input type="password" name="senha" id="senha" value="" required="">
                </div>
            </div>
            <div class="row">
                <button class="btn btn-success"> Entrar</button>
            </div>
                         
            
        </form>
    </body>
</html>
