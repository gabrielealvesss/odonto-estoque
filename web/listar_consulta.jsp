<%-- 
    Document   : listar_usuario
    Created on : 25/09/2023, 23:41:52
    Author     : Luan
--%>

<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css"/>
        <title>Consultas</title>
    </head>
    <body>
        <div class="container">
            <%@include file="banner.jsp"%>
            <%@include file="menu.jsp" %>
            <h1>Lista de Consultas</h1>
            
            <a href="form_consulta.jsp" class="btn btn-primary"> Nova Consulta </a>
            <table class="table table-hover table-striped table-bordered display" id="listaConsulta">
                
                <thead>
                <tr>
                    <th>codigo</th>
                    <th>data</th>
                    <th>hora</th>
                    <th>Paciente</th>
                    <th>Dentista</th>
                    <th>Opções</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th>codigo</th>
                    <th>data</th>
                    <th>hora</th>
                    <th>Paciente</th>
                    <th>Dentista</th>
                    <th>Opções</th>
                </tr>
                </tfoot>
                
                <jsp:useBean class="persistencia.ConsultaDAO" id="cDAO"/>
                <tbody>
                <c:forEach var="c" items="${cDAO.lista}">
                <tr>
                    <td>${c.codigo}</td>
                    <td><fmt:formatDate value="${c.data}" pattern="dd/MM/yyyy"/></td>
                    <td><fmt:formatDate value="${c.hora}" pattern="HH:mm"/></td>
                    <td>${c.paciente.nome}</td>
                    <td>${c.vendedor.nome}</td>
                    <td>
                        <a class="btn btn-primary" href="gerenciar_consulta.do?acao=alterar&idConsulta=${c.idConsulta}"> 
                            <i class="glyphicon glyphicon-pencil"></i>
                        </a>
                         <a href="form_consulta2.jsp?acao=alterar" class="btn btn-primary">
                                <i class="glyphicon glyphicon"> Finalizar Consulta</i>
                         </a>    
                    </td>
                    
                </tr>
                </c:forEach>
                </tbody>
            </table>
                <script type="text/javascript" src="datatables/jquery.js"></script>
                <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
                <script type="text/javascript">
                    $(document).ready(function(){
                        $("#listaConsulta").dataTable({
                            "bJQueryUI": true,
                            "oLanguage":{
                                "sProcessing":"Processando...",
                                "sZeroRecords": "Não foram encontrados resultados",
                                "sInfo": "Mostrar de _START_ até _END_ de _TOTAL_ registros",
                                "sInfoEmpty":"Mostrando de 0 ate 0 de 0 registro",
                                "sInfoFiltered":"",
                                "sInfoPostFix":"",
                                "sSearch":"Pesquisar",
                                "sUrl":"",
                                "oPaginate":{
                                    "sFirst":"Primeiro",
                                    "sPrevious":"Anterior",
                                    "sNext":"Proximo",
                                    "sLast":"Último",
                                }
                            }
                        })
                    })
                </script>
        </div>
    </body>
</html>
