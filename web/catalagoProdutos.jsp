<%-- 
    Document   : Produto
    Created on : 12/06/2021, 19:30:25
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <title>Cadastrar Produtos</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <img class="img" src="img/acai.png" alt="AcaiBanner" width="140%" height="250"> 
        </div>
        
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.html">Açai+Sabores</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.html">Home</a></li>
                    <li><a href="#">Produtos</a></li>
                    <li><a href="contato.jsp">Contato</a></li>
                    <li><a href="Cliente/gerenciarCliente.jsp">Gerenciar Conta</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Cadastrar</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </ul>
            </div>
        </nav>
        
        <h3>${menssagem}</h3>
        
        <div class="container">
            <h2 align="center">Lista de Produtos Cadastrados!!!</h2>           
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>IdProduto</th>
                        <th>Nome do Produto</th>
                        <th>Descrição</th>
                        <th>Preço</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaprodutocompra}" var="produto">
                        <tr>
                            <td>${produto.idProduto}</td>
                            <td>${produto.nomeProduto}</td>
                            <td>${produto.descricao}</td>
                            <td>${produto.preco}</td>
                            <td><a href="${pageContext.request.contextPath}/ComprarProduto?idProduto=${produto.idProduto}"> Comprar </a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>