<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Log"%>
<%@page contentType="text/html; charset=UTF-8"%> <!-- Diretiva contentType removida daqui -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>

<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css"/>
        <title>Usuários</title>
    </head>
    <body>
        <div class="container">
            <%@include file="banner.jsp"%>
            <%@include file="menu.jsp" %>
            <h2>LOGS DO SISTEMA</h2>
            
            <table class="table table-hover table-striped table-bordered display" id="listaLog">
                
                <thead>
                    <tr>
                        <th>Usuario</th>
                        <th>ação</th>
                        <th>página</th>
                        <th>Data</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <th>Usuario</th>
                        <th>Login</th>
                        <th>Status</th>
                        <th>Data</th>
                    </tr>
                </tfoot>
                
                <jsp:useBean class="persistencia.LogDAO" id="gdao" />
                <%
                    // Carrega a lista de logs do banco de dados
                    gdao.carregarLista();
                    
                    // Ordena a lista de logs por data (do mais recente para o mais antigo)
                    Collections.sort(gdao.getLista(), new Comparator<Log>() {
                        @Override
                        public int compare(Log log1, Log log2) {
                            return log2.getData().compareTo(log1.getData());
                        }
                    });
                %>
                <tbody>
                    <c:forEach var="l" items="${gdao.getLista()}">
                        <tr>
                            <td>${l.getNomeUsuario()}</td>
                            <td>${l.getAcao()}</td>
                            <td>${l.getTabela()}</td>             
                            <td><fmt:formatDate value="${l.getData()}" pattern="dd/MM/yyyy HH:mm:ss"/></td>  
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <script type="text/javascript" src="datatables/jquery.js"></script>
            <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
            <script type="text/javascript">
                $(document).ready(function(){
                    $("#listaLog").dataTable({
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
