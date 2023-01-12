<%-- 
    Document   : cadastrarCliente
    Created on : 15/04/2022, 15:07:30
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <img class="img" src="${pageContext.request.contextPath}/img/acai.png" alt="AcaiBanner" width="95%" height="250"> 
        </div>
        
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.html">Açai+Sabores</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.html">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/catalagoProdutos.jsp">Produtos</a></li>
                    <li><a href="${pageContext.request.contextPath}/contato.jsp">Contato</a></li>
                    <li><a href="${pageContext.request.contextPath}/Cliente/gerenciarCliente.jsp">Gerenciar Conta</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/Cliente/cadastrarCliente.jsp"><span class="glyphicon glyphicon-user"></span> Cadastrar</a></li>
                    <li><a href="${pageContext.request.contextPath}/logarCliente.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </ul>
            </div>
        </nav>
        <h3 align="center">${saudacao}</h3>
        <h3 align="center"><a href="LogarCliente?acao=logout">Sair</a></h3>
    </body>
</html>

