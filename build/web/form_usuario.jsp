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
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.6/jquery.inputmask.min.js"></script>
        <title>Cadastro de Usuários</title>
    </head>
    <body>
        <div class="container">
            <%@include file="banner.jsp"%>
            <%@include file="menu.jsp" %>
            <h1>Cadastrar Usuário</h1>

            <form action="gerenciar_usuario.do" method="POST">
                <input type="hidden" name="idUsuario" value="${u.idUsuario}"/>
                <div class="row">
                    <div class="form-group col-sm-6">
                        <label form="perfil" class="control-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name ="nome" required="" value="${u.nome}" maxlength="30"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="login" class="control-label">Login</label>
                        <input type="text" class="form-control" id="login" name ="login" required="" value="${u.login}" maxlength="30"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="senha" class="control-label">Senha</label>
                        <input type="password" class="form-control" id="senha" name ="senha" value="${u.senha}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="status" class="control-label">Status</label>
                        <select class="form-control" name="status">
                            <option value="1"<c:if test="${u.status==1}"> selected=""</c:if>>Ativo</option>
                            <option value="2"<c:if test="${u.status==2}"> selected=""</c:if>>Inativo</option>
                            </select>    
                        </div>
                        <div class="form-group col-sm-6">
                            <script>
  // Certifique-se de que o jQuery está carregado antes deste script

  $(document).ready(function() {
    // Aplicar a máscara de CPF ao campo
    $('#cpf').inputmask('999.999.999-99', { reverse: true });
  });
</script>

                            <label form="cpf" class="control-label">CPF</label>
                            <input type="text" class="form-control" id="cpf" name ="cpf" required="" value="${u.cpf}" maxlength="14"/>
                            
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="rg" class="control-label">RG</label>
                        <input type="text" class="form-control" id="rg" name ="rg" value="${u.rg}" required="" maxlength="7"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="orgaoE" class="control-label">Orgão Expedidor</label>
                        <input type="text" class="form-control" id="orgaoE" name ="orgaoE" value="${u.orgaoE}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="sexo" class="control-label">Sexo</label>
                        <select class="form-control" name="sexo">
                            <option value="1"<c:if test="${u.sexo==1}"> selected=""</c:if>>Masculino</option>
                            <option value="2"<c:if test="${u.sexo==2}"> selected=""</c:if>>Feminino</option>
                            </select>    
                        </div>

                        <div class="form-group col-sm-6">
                            <label form="nomeP" class="control-label">Nome do Pai</label>
                            <input type="text" class="form-control" id="nomeP" name ="nomeP" value="${u.nomeP}" required="" maxlength="45"/>
                    </div>

                    <div class="form-group col-sm-6">
                        <label form="nomeM" class="control-label">Nome da Mãe</label>
                        <input type="text" class="form-control" id="nomeM" name ="nomeM" value="${u.nomeM}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="dataNasc" class="control-label">Data de Nascimento</label>
                        <input type="text" class="form-control" id="dataNasc" name ="dataNasc" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${u.dataNasc}"/>" required=""/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="senha" class="control-label">Matricula</label>
                        <input type="text" class="form-control" id="matricula" name ="matricula" value="${u.matricula}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="endereco4" class="control-label">Endereco</label>
                        <input type="text" class="form-control" id="endereco" name ="endereco" value="${u.endereco}" required="" maxlength="90"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="cep" class="control-label">CEP</label>
                        <input type="text" class="form-control" id="cep" name ="cep" value="${u.cep}" required="" maxlength="10"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="bairro" class="control-label">Bairro</label>
                        <input type="text" class="form-control" id="bairro" name ="bairro" value="${u.bairro}" required="" maxlength="45"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="numero" class="control-label">Número</label>
                        <input type="number" class="form-control" id="numero" name ="numero" value="${u.numero}" required="" maxlength="10"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="complemento" class="control-label">Complemento</label>
                        <input type="text" class="form-control" id="complemento" name ="complemento" value="${u.complemento}" required="" maxlength="100"/>
                    </div>
                    <div class="form-group col-sm-6">
                        <label form="cargo" class="control-label">Cargo</label>
                        <input type="text" class="form-control" id="cargo" name ="cargo" value="${u.cargo}" required="" maxlength="45"/>
                    </div>

                    <div class="form-group col-sm-6">
                        <label form="foto" class="control-label">Foto</label>
                        <input type="text" class="form-control" id="foto" name ="foto" value="${u.foto}" required="" maxlength="45"/>
                    </div>



                    <div class="form-group col-sm-6">
                        <label form="perfil" class="control-label">Perfil</label>
                        <select class="form-control" name="idPerfil" required="">
                            <option value="">Selecione o Perfil</option>
                            <jsp:useBean class="persistencia.PerfilDAO" id="perfil"/>
                            <c:forEach var="p" items="${perfil.lista}">
                                <option value="${p.idPerfil}"
                                        <c:if test="${p.idPerfil==u.perfil.idPerfil}">selected=""</c:if>
                                        >   ${p.nome}</option>
                            </c:forEach>
                        </select>    
                    </div>
                    <br>
                    <div class="row">
                        <button class="btn btn-success">Gravar</button>
                        <a href="listar_usuario.jsp" class="btn btn-warning">Voltar</a>
                    </div>
                </div>

            </form>
        </div>
    </body>
</html>