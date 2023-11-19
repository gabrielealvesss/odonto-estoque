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
        <title>Cadastro de Pacientes</title>
    </head>
    <body>
        <div class="container">
            <%@include file="banner.jsp"%>
            <%@include file="menu.jsp" %>
            <h1>Cadastrar Paciente</h1>

            <form action="gerenciar_paciente.do" method="POST">
                <input type="hidden" name="idPaciente" value="${p.idPaciente}"/>
                <div class="row">
                    <div class="form-group col-sm-6">
                        <label form="perfil" class="control-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name ="nome" required="" value="${p.nome}" maxlength="30"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="telefone" class="control-label">Telefone</label>
                        <input type="text" class="form-control" id="telefone" name ="telefone" required="" value="${p.telefone}" maxlength="30"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="dataNasc" class="control-label">Data de Nascimento</label>
                        <input type="text" class="form-control" id="dataNasc" name ="dataNasc" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${p.dataNasc}"/>" required=""/>
                    </div>
                        <div class="form-group col-sm-6">
                            <label form="cpf" class="control-label">CPF</label>
                            <input type="text" class="form-control" id="cpf" name ="cpf" required="" value="${p.cpf}" maxlength="14"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="rg" class="control-label">RG</label>
                        <input type="text" class="form-control" id="rg" name ="rg" value="${p.rg}" required="" maxlength="7"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="orgaoE" class="control-label">Orgão Expedidor</label>
                        <input type="text" class="form-control" id="orgaoE" name ="orgaoE" value="${p.orgaoE}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="sexo" class="control-label">Sexo</label>
                        <select class="form-control" name="sexo">
                            <option value="1"<c:if test="${p.sexo==1}"> selected=""</c:if>>Masculino</option>
                            <option value="2"<c:if test="${p.sexo==2}"> selected=""</c:if>>Feminino</option>
                            </select>    
                        </div>

                        <div class="form-group col-sm-6">
                            <label form="nomeP" class="control-label">Nome do Pai</label>
                            <input type="text" class="form-control" id="nomeP" name ="nomeP" value="${p.nomeP}" required="" maxlength="45"/>
                    </div>

                    <div class="form-group col-sm-6">
                        <label form="nomeM" class="control-label">Nome da Mãe</label>
                        <input type="text" class="form-control" id="nomeM" name ="nomeM" value="${p.nomeM}" required="" maxlength="45"/>
                    </div>

                    <div class="form-group col-sm-6">
                        <label form="endereco4" class="control-label">Endereco</label>
                        <input type="text" class="form-control" id="endereco" name ="endereco" value="${p.endereco}" required="" maxlength="90"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="cep" class="control-label">CEP</label>
                        <input type="text" class="form-control" id="cep" name ="cep" value="${p.cep}" required="" maxlength="10"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="bairro" class="control-label">Bairro</label>
                        <input type="text" class="form-control" id="bairro" name ="bairro" value="${p.bairro}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="numero" class="control-label">Número</label>
                        <input type="number" class="form-control" id="numero" name ="numero" value="${p.numero}" required="" maxlength="10"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="complemento" class="control-label">Complemento</label>
                        <input type="text" class="form-control" id="complemento" name ="complemento" value="${p.complemento}" required="" maxlength="100"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="email" class="control-label">Email</label>
                        <input type="text" class="form-control" id="email" name ="email" value="${p.email}" required="" maxlength="100"/>
                    </div>
                     <div class="form-group col-sm-6">
                        <label form="tipo" class="control-label">Tipo de pessoa </label>
                        <select class="form-control" name="tipo">
                            <option value="1"<c:if test="${p.tipo==1}"> selected=""</c:if>>Fisíca</option>
                            <option value="2"<c:if test="${p.tipo==2}"> selected=""</c:if>>Juridica</option>
                            </select>    
                        </div>
                    <br>
                    <div class="row">
                        <button class="btn btn-success">Gravar</button>
                        <a href="listar_paciente.jsp" class="btn btn-warning">Voltar</a>
                    </div>
                </div>

            </form>
        </div>
    </body>
</html>