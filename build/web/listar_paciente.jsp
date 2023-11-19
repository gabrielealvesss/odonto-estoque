

<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css"/>
        <title>Usuários</title>
        <script type="text/javascript">
            function confirmarExclusao(id,nome){
                if(confirm('Deseja Realmente excluir o Paciente '+nome+'?')){
                    location.href='gerenciar_paciente.do?acao=deletar&idPaciente='+id;
                }
            }
        </script>
    </head>
    <body>
        <div class="container">
            <%@include file="banner.jsp"%>
            <%@include file="menu.jsp" %>
            <h1>Lista de pacientes</h1>
            
            <a href="form_paciente.jsp" class="btn btn-primary"> Novo Cadastro </a>
            <table class="table table-hover table-striped table-bordered display" id="listaPaciente">
                
                <thead>
                <tr>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Email</th>
                    <th>CPF</th>
                    <th>Data de Nascimento</th>
                    <th>Opções</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Email</th>
                    <th>CPF</th>
                    <th>Data de Nascimento</th>
                    <th>Opções</th>
                </tr>
                </tfoot>
                
                <jsp:useBean class="persistencia.PacienteDAO" id="pDAO"/>
                <tbody>
                <c:forEach var="p" items="${pDAO.lista}">
                <tr>
                    <td style="display: none;">${p.idPaciente}</td>
                    <td>${p.nome}</td>
                    <td>${p.telefone}</td>
                    <td>${p.email}</td>
                    <td>${p.cpf}</td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${p.dataNasc}"/></td>
                    <td>
                        <a class="btn btn-primary" href="gerenciar_paciente.do?acao=alterar&idPaciente=${p.idPaciente}"> 
                            <i class="glyphicon glyphicon-pencil"></i>
                        </a>
                            <button class="btn btn-danger" onclick="confirmarExclusao(${p.idPaciente},'${p.nome}')">
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
                        $("#listaPaciente").dataTable({
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
