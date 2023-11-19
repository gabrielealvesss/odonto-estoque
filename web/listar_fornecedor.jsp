
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
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css">
        <script type="text/javascript">
            function confirmarExclusao(id, nome) {
                if (confirm('Deseja realmente desativar o Fornecedor ' + nome + '?')) {
                    location.href = 'gerenciar_fornecedor.do?acao=desativar&idFornecedor=' + id;
                }
            }
        </script>    
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-fluid">

            <%@include file="banner.jsp" %>
            <%@include file="menu.jsp" %>
            Lista de Fornecedores
            <a href="form_fornecedor.jsp" class="btn btn-primary">Novo Cadastro</a>

            <table class="table table-hover table-striped table-bordered display"
                   id="listarFornecedor">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>CEP</th>
                        <th>Endereço</th>
                        <th>Telefone</th>
                        <th>Opções</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                       <th>Nome</th>
                        <th>CEP</th>
                        <th>Endereço</th>
                        <th>Telefone</th>
                        <th>Opções</th>
                    </tr>
                </tfoot>
                <tbody>
                    <c:forEach var="f" items="${listaFornecedor}">

                        <tr>
                            <td style="display: none;">${f.idFornecedor}</td>
                            <td>${f.nome}</td>
                            <td>${f.cep}</td>
                            <td>${f.endereco}</td>
                            <td>${f.telefone}</td>
                            <td>
                                <a class="btn btn-primary" href="gerenciar_fornecedor.do?acao=alterar&idFornecedor=${f.idFornecedor}">
                                    <i class="glyphicon glyphicon-pencil"></i>
                                </a>    

                                <button class="btn btn-danger" onclick="confirmarExclusao(${f.idFornecedor}, '${f.nome}')">
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
                                        $("#listarFornecedor").dataTable({
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

