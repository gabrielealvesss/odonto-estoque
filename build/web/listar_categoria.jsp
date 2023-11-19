
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
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css">
        <script type="text/javascript">
            function confirmarExclusao(id, categoria){
                if(confirm('Deseja realmente desativar a categoria ' +categoria+'?')){
                    location.href='GerenciarCategoria.do?acao=desativar&idCategoria='+id;
                }
            }
        </script>    
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-fluid">
            
            <%@include file="banner.jsp" %>
            <%@include file="menu.jsp" %>
            Lista de categorias
            <a href="form_categoria.jsp" class="btn btn-primary">Novo Cadastro</a>
            
            <table class="table table-hover table-striped table-bordered display"
                   id="listarCategoria">
                <thead>
                <tr>
                    <th>Categoria</th>
                </tr>
                </thead>
                <tfoot>
                    <th>Categoria</th>
                </tfoot>
                <tbody>
               <c:forEach var="c" items="${listaCategoria}">
    <tr>
        <td style="display: none;">${c.idCategoria}</td>
        <td>${c.nomeCategoria}</td>
        <td>
            <a class="btn btn-primary" href="GerenciarCategoria.do?acao=alterar&idCategoria=${c.idCategoria}">
                <i class="glyphicon glyphicon-pencil"></i>
            </a>    
            <button class="btn btn-danger" onclick="confirmarExclusao(${c.idCategoria},'${c.nomeCategoria}')">
                <i class="glyphicon glyphicon-trash"></i>
            </button>
        </td>
    </tr>
</c:forEach>

                </tbody>
            </table>
            <script type="text/javascript" src="datatables/jquery.js"></script> 
            <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
            <script type="text/javascript">
                $(document).ready(function(){
                    $("#listarCategoria").dataTable({
                        "bJQueryUI": true,
                                                   "oLanguage": {
                                                       "sProcessing": "Processando...",
                                                       "sLengthMenu": "Mostrar _MENU_ registros",                        
                                                       "sZeroRecords": "Não foram encontrados resultados",
                                                       "sInfo": "Mostrar de _START_ até _END_ de _TOTAL_ registros",
                                                       "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                                                       "sInfoFiltered": "",
                                                       "sInfoPostFix": "",
                                                       "sSearch": "Pesquisar",
                                                       "sUrl": "",
                                                       "oPaginate": {
                                                           "sFirst": "Primeiro",
                                                           "sPrevious": "Anterior",
                                                           "sNext": "Próximo",
                                                           "sLast": "Último"
                                                       }
                                                   }
                    })
                })
            </script>    
            
        </div>
    </body>
</html>

