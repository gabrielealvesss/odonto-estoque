<%@page import="persistencia.MenuDAO"%>
<%@page import="java.util.ArrayList"%>
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
            function confirmarExclusao(id, nome) {
                if (confirm('Deseja realmente excluir o Menu ' + nome + '?')) {
                    location.href = 'gerenciar_menu.do?acao=deletar&idMenu=' + id;
                }
            }
        </script>    
        <title>Menus</title>
    </head>
    <body>
        <div class="container-fluid">

            <%@include file="banner.jsp" %>
            <%@include file="menu.jsp" %>
           
            <h1>Lista de Menus</h1>
           
            <a href="form_menu.jsp" class="btn btn-primary">Novo Cadastro</a>
     
            <table class="table table-hover table-striped table-bordered display"
                   id="listarMenu">
                <thead>
                    <tr>
                 
                        <th> Nome Menu</th>
                        <th>Link</th>
                        <th>Exibir</th>
                        <th> Status</th>
                          <th>Opções</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                       
                        <th> Nome Menu</th>
                        <th>Link</th>
                        <th>Exibir</th>
                         <th> Status</th>
                          <th>Opções</th>
                    </tr>
                </tfoot>
               
               
                 <tbody>
                     
                      <jsp:useBean class="persistencia.MenuDAO" id="mDAO"/>
                     
                     <c:forEach var="m" items="${mDAO.lista}">

                        <tr>
                           <td style="display: none;">${m.idMenu}</td>
                            <td>${m.nome}</td>
                             <td>${m.link}</td>
                             
                             
                              <td>
                                  <c:if test="${m.exibir == 1}">
                                       Sim
                                   </c:if>
                                        <c:if test="${m.exibir == 2}">
                                       Não
                                   </c:if>
                                   
                                   
                               </td>
                              <td> <c:choose>
                                    <c:when test="${m.status eq 1}">
                                        Ativo
                                    </c:when>
                                    <c:when test="${m.status eq 2}">
                                        Desativado
                                    </c:when>
                                </c:choose>
                              </td>                              
                              <td>
                                <a class="btn btn-primary" href="gerenciar_menu.do?acao=alterar&idMenu=${m.idMenu}">
                                    <i class="glyphicon glyphicon-pencil"></i>
                                </a>    

                                <button class="btn btn-danger" onclick="confirmarExclusao(${m.idMenu}, '${m.nome}')">
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
                                    $(document).ready(function () {
                                        $("#listarMenu").dataTable({
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

