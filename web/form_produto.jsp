<%-- 
    Document   : form_usuario
    Created on : 26/09/2023, 09:36:17
    Author     : Luan
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css"/>
        <title>Cadastro de Produto</title>
    </head>
    <body>
        <div class="container">
            <%@include file="banner.jsp"%>
            <%@include file="menu.jsp" %>
            <h1>Cadastrar Produto</h1>

            <form action="gerenciar_produto.do" method="POST">
                <input type="hidden" name="idProduto" value="${p.idProduto}"/>
                <div class="row">
                    <div class="form-group col-sm-6">
                        <label form="codigo" class="control-label">Codigo do produto</label>
                        <input type="text" class="form-control" id="codigo" name ="codigo" value="${p.codigo}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="perfil" class="control-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name ="nome" required="" value="${p.nome}" maxlength="30"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="marca" class="control-label">Marca</label>
                        <input type="text" class="form-control" id="marca" name ="marca" value="${p.marca}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="quantidade" class="control-label">Quantidade</label>
                        <input type="text" class="form-control" id="quantidade" name ="quantidade" value="${p.quantidade}" required="" maxlength="4"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="validade" class="control-label">Data de validade</label>
                        <input type="text" class="form-control" id="validade" name ="validade" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${p.validade}"/>" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label for="valor" class="control-label">Valor do produto</label>
                        <fmt:setLocale value="pt_BR"/>
                        <input type="text" class="form-control" id="valor" name="valor" value="<fmt:formatNumber pattern="#,##0.00" value="${p.valor}"/>" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="dataEntrada" class="control-label">Data de Entrada</label>
                        <input type="text" class="form-control" id="dataEntrada" name ="dataEntrada" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${p.dataEntrada}"/>" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="fornecedor" class="control-label">Fornecedor</label>
                        <select class="form-control" name="idFornecedor" required="">
                            <option value="">Selecione o Fornecedor</option>
                            <jsp:useBean class="persistencia.FornecedorDAO" id="fornecedor"/>
                            <c:forEach var="f" items="${fornecedor.lista}">
                                <option value="${f.idFornecedor}"
                                        <c:if test="${f.idFornecedor==p.fornecedor.idFornecedor}">selected=""</c:if>
                                        >   ${f.nome}</option>
                            </c:forEach>
                        </select>    
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="categoria" class="control-label">Categoria</label>
                        <select class="form-control" name="idCategoria" required="">
                            <option value="">Selecione a Categoria</option>
                            <jsp:useBean class="persistencia.CategoriaDAO" id="categoria"/>
                            <c:forEach var="c" items="${categoria.lista}">
                                <option value="${c.idCategoria}"
                                        <c:if test="${c.idCategoria==p.categoria.idCategoria}">selected=""</c:if>
                                        >   ${c.nomeCategoria}</option>
                            </c:forEach>
                        </select>    
                    </div>

                    <br>
                    <div class="row">
                        <button class="btn btn-success">Gravar</button>
                        <a href="listar_produto.jsp" class="btn btn-warning">Voltar</a>
                    </div>
                </div>

            </form>
        </div>
    </body>
</html>