<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Consulta_produto" %>
<%@ page import="persistencia.PacienteDAO" %>
<%@ page import="model.Paciente" %>
<%@ page import="model.Consulta" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css" />
    <script type="text/javascript">
        function excluir(index, item) {
            if (confirm("Tem certeza que deseja excluir o item " + item + "?")) {
                window.location.href = "gerenciar_carrinho.do?acao=del&index=" + index;
            }
        }
    </script>
    <title>Finalizar Consulta</title>
</head>
<body>
    <div class="container">
        <%@ include file="banner.jsp" %>
        <%@ include file="menu.jsp" %>
        <h1>Finalizar Consulta</h1>

        <%
            Consulta c = new Consulta();
            Paciente p = new Paciente();

            try {
                c = (Consulta) session.getAttribute("consulta");
            } catch (Exception e) {
                out.print("Erro:" + e);
            }
        %>

        <br /><br />

        Vendedor: <%= c.getVendedor().getNome() %>
        <br />
        Cliente: <%= c.getPaciente().getNome() %>
        <br><%= c.getData() %></br>
        <br><%= c.getHora() %></br>
        <br><%= c.getValorTotal() %></br>
        <br><%= c.getProcedimento() %></br>
        <br><%= c.getAnamnese() %></br>
        <br><%= c.getCodigo()%></br>
        
        <br />
        <table class="table table-striped table-bordered table-hover display" id="listaConsulta">
            <thead>
                <!-- Seu código do cabeçalho da tabela aqui -->
            </thead>
            <tbody>
                <%
                    double totalItens = 0;
                    int cont = 0;
                    for (Consulta_produto cp : c.getCarrinho()) {
                %>
                <tr>
                    <td align="center"><%= cont + 1 %></td>
                    <td><%= cp.getProduto().getNome() %></td>
                    <td><%= cp.getQtd() %></td>
                    <td><%= cp.getValor() %></td>
                    <td><%= cp.getQtd() * cp.getValor() %></td>
                    <td align="center">
                        <a href="javascript:void(0);" onclick="excluir(<%= cont %>, <%= cont + 1 %>)" class="btn btn-danger">
                            <i class="glyphicon glyphicon-trash"></i>
                        </a>
                    </td>
                </tr>
                <%
                    totalItens += cp.getQtd() * cp.getValor();
                    cont++;
                    }
                %>
                <!-- Total da consulta exibido apenas no final -->
                <tr>
                    <td colspan="4" align="right">Total da Consulta:</td>
                    <td><%= totalItens %></td>
                    <td>&nbsp;</td>
                </tr>
            </tbody>
        </table>

        <a href="listar_consulta.jsp" class="btn btn-warning">Cancelar</a>
        <a href="form_consulta.jsp?acao=c" class="btn btn-success">Continuar Consulta</a>
        <a href="gerenciar_consulta2.do" class="btn btn-success">Confirmar Consulta</a>
    </div>
    <script type="text/javascript" src="datatables/jquery.js"></script>
    <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#listaConsulta").dataTable({
                "bJQueryUI": true,
                "oLanguage": {
                    "sProcessing": "Processando...",
                    "sZeroRecords": "Não foram encontrados resultados",
                    "sInfo": "Mostrar de _START_ até _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando de 0 até 0 de 0 registro",
                    "sInfoFiltered": "",
                    "sInfoPostFix": "",
                    "sSearch": "Pesquisar",
                    "sUrl": "",
                    "oPaginate": {
                        "sFirst": "Primeiro",
                        "sPrevious": "Anterior",
                        "sNext": "Próximo",
                        "sLast": "Último",
                    }
                }
            })
        })
    </script>
</body>
</html>
