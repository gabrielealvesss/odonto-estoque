<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List" %>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="model.Produto"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="datatables/jquery.dataTables.min.css"/>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
           function gerarGrafico() {
                
                    location.href = 'gerenciar_produto.do?acao=gerarGrafico';
                
            }
        </script>  
    <title>Produtos</title>

    <style>
        .estoque-baixo {
            color: red;
        }
    </style>

   <script type="text/javascript">
       
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(desenharGrafico);
 
    function desenharGrafico() {
        
        console.log('Dados do gráfico:', arrayDados);
        var dados = new google.visualization.DataTable();
        dados.addColumn('string', 'Produto');
        dados.addColumn('number', 'Quantidade');

        var arrayDados = <%=new Gson().toJson(request.getAttribute("dadosGrafico"))%>;

        for (var i = 0; i < arrayDados.length; i++) {
            var produto = arrayDados[i];
            dados.addRow([produto.nome, produto.quantidade]);
        }

        var opcoes = {
            title: 'Quantidade de Produtos',
            width: 400,
            height: 300
        };

        var grafico = new google.visualization.BarChart(document.getElementById('grafico'));
        grafico.draw(dados, opcoes);
    }
</script>

</head>
<body>
    <div class="container">
        <%@include file="banner.jsp"%>
        <%@include file="menu.jsp" %>
        <h1>Lista de Produtos</h1>

        <a href="form_produto.jsp" class="btn btn-primary"> Novo Cadastro </a>
        <table class="table table-hover table-striped table-bordered display" id="listaProduto">

            <!-- Títulos das Colunas e Rodapé -->
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Data de validade</th>
                    <th>Quantidade</th>
                    <th>Categoria</th>
                    <th>Opções</th>
                </tr>
            </thead>
            <tfoot>
                <tr>
                    <th>Nome</th>
                    <th>Data de validade</th>
                    <th>Quantidade</th>
                    <th>Categoria</th>
                    <th>Opções</th>
                </tr>
            </tfoot>

            <!-- Corpo da Tabela -->
            <jsp:useBean class="persistencia.ProdutoDAO" id="pDAO"/>
            <tbody>
                <c:forEach var="p" items="${pDAO.lista}">
                    <tr>
                        <td style="display: none;">${p.idProduto}</td>
                        <td>${p.nome}</td>
                        <td>
                            <fmt:formatDate value="${p.validade}" pattern="dd/MM/yyyy" />
                        </td>
                        <td class="${p.quantidade < 20 ? 'estoque-baixo' : ''}">${p.quantidade}</td>
                        <td>${p.categoria.nomeCategoria}</td>
                        <td>
                            <a class="btn btn-primary" href="gerenciar_produto.do?acao=alterar&idProduto=${p.idProduto}"> 
                                <i class="glyphicon glyphicon-pencil"></i>
                            </a>
                            <button class="btn btn-danger" onclick="confirmarExclusao(${p.idProduto}, '${p.nome}')">
                                <i class="glyphicon glyphicon-trash"></i>
                            </button>
                                
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
<button class="btn btn-danger" onclick="gerarGrafico();">
                                <i class="glyphicon glyphicon-trash">Gerar Grafico</i>
                            </button>
        <!-- Gráfico -->
        <div id="grafico"></div>

        <!-- Scripts JS -->
        <script type="text/javascript" src="datatables/jquery.js"></script>
        <script type="text/javascript" src="datatables/jquery.dataTables.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#listaProduto").dataTable({
                    "bJQueryUI": true,
                    "oLanguage": {
                        "sProcessing": "Processando...",
                        "sZeroRecords": "Não foram encontrados resultados",
                        "sInfo": "Mostrar de _START_ até _END_ de _TOTAL_ registros",
                        "sInfoEmpty": "Mostrando de 0 ate 0 de 0 registro",
                        "sInfoFiltered": "",
                        "sInfoPostFix": "",
                        "sSearch": "Pesquisar",
                        "sUrl": "",
                        "oPaginate": {
                            "sFirst": "Primeiro",
                            "sPrevious": "Anterior",
                            "sNext": "Proximo",
                            "sLast": "Último",
                        }
                    }
                });
            });
        </script>
    </div>
</body>
</html>
