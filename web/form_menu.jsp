<%--
    Document   : index
    Created on : 08/09/2023, 14:35:45
    Author     : pfela
--%>

<%@page import="persistencia.MenuDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Menu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1,
              user-scalable=no" name="viewport"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css"/>
        <title>Cadastar Menu</title>
    </head>
    <body>
        <div class="container-fluid">
           
            <%@include file="banner.jsp" %>
            <%@include file="menu.jsp" %>
            <h4>Novo Menu</h4>
           
            <form action="gerenciar_menu.do" method="POST">
                <input type="hidden" name="idMenu" name="idMenu" value="${menu.idMenu}"/>
               
               
                <div class="row">                
<!---------------NOME MENU---------------------------------------------------------------------------------------------->
                    <div class="form-group col-sm-6">
                        <label form="nome" class="control-label">Nome Menu</label>
                        <input type="text" id="nome" name="nome" class="form-control" required="" value="${menu.nome}" maxlength="45"/>
                    </div>
<!------------------------------------LINK------------------------------------------------------------------------>
                    <div class="form-group col-sm-6">
                        <label form="nome" class="control-label">Link</label>
                        <input type="text" id="link" name="link" class="form-control" required="" value="${menu.link}" maxlength="200"/>                  
                        </div>
                       
<!---------------------------------------EXIBIR------------------------------------------------>
                     <div class="form-group col-sm-6">
                        <label form="nome" class="control-label">Exibir</label>
                        <select class="form-control" name="exibir">
                            <option value="1"
                                <c:if test="${menu.exibir==1}" >
                                    selected=""
                                </c:if>
                            >Sim </option>
                                <option value="2"
                                    <c:if test="${menu.exibir==2}" >
                                      selected=""
                                    </c:if>
                                >Não </option>
                        </select>
                     </div>
                </div>
                       
<!---------------------------------------STATUS---------------------------------------------------------------------------------------->
                    <div class="form-group col-sm-6">                
                        <label form="status" class="control-label">Status</label>
                        <select class="form-control" name="status">
                            <option value="1"<c:if test="${m.status==1}"> selected=""</c:if>>Ativo</option>
                            <option value="2"<c:if test="${m.status==2}"> selected=""</c:if>>Desativado</option>
                            </select>    
                        </div>
<!---------------------------------------BOTÃO GRAVAR------------------------------------>
                        <div class="row">
                           <div class="form-group col-sm-8">

                             <button class="btn btn-success">Gravar</button>
                               <a href="listar_menu.jsp" class="btn btn-warning"> Voltar</a>
                           </div>
                       </div>
               </form>    
          </div>            
       </body>
   </html>