
<%-- 
    Document   : index
    Created on : 08/09/2023, 14:35:45
    Author     : pfela
--%>

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
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css">
        <title>Gerenciar Perfil</title>
        <script type="text/javascript">
            function confirmarExclusao(idMenu, nome, idPerfil) {
                if (confirm('Deseja realmente desvincular o Menu ' + nome + '?')) {
                    location.href = 'gerenciar_perfil_menu.do?acao=desvincular&idMenu=' + idMenu+ '&idPerfil='+idPerfil;
                }
            }
        </script>    
    </head>
    <body>
        <div class="container-fluid">
            
            <%@include file="banner.jsp" %>
            <%@include file="menu.jsp" %>
            <h4>Novo Perfil</h4>
           
            <form action="gerenciar_perfil_menu.do" method="POST">
                <input type="hidden" name="idPerfil" name="idPerfil" value="${perfilv.idPerfil}"/>
                <div class="row">
                    <div class="form-group col-sm-6">
                        <label for="perfil" class="control-label">${perfilv.nome}</label>
                    </div>
                </div>
                    <div class="form-group col-sm-6">
                        <label for="menu" class="control-label">Menus</label>
                        <select name="idMenu" required="" id="idMenu" class="form-control">
                            <option value=""> Selecione o menu</option>
                            <c:forEach var="m" items="${perfilv.naoMenus}">
                                <option value="${m.idMenu}">${m.nome}</option>
                            </c:forEach>
                           
                            </select>    
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-sm-8">
                            <button class="btn btn-success">Vincular</button>
                            <a href="gerenciar_perfil.do?acao=listar" class="btn btn-warning">Voltar</a>
                        </div>
                    </div>
            </form>    
                  
                    
                    
            <table class="table table-hover table-striped table-bordered display"
                   id="listarMenu">
                <thead>
                    <tr>
                 
                        <th> Nome Menu</th>
                        <th>Link</th>
                        <th>Exibir</th>
                        <th> Status</th>
                          <th>Desvincular</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                       
                        <th> Nome Menu</th>
                        <th>Link</th>
                        <th>Exibir</th>
                         <th> Status</th>
                          <th>Desvincular</th>
                    </tr>
                </tfoot>
               
               
                 <tbody>
                     
                     <c:forEach var="m" items="${perfilv.menus}">

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
                                <button class="btn btn-danger" onclick="confirmarExclusao(${m.idMenu}, '${m.nome}', ${perfilv.idPerfil})">
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

