<%-- 
    Document   : cadastrarCliente
    Created on : 15/04/2022, 15:07:30
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <title>Cadastrar Cliente</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container-fluid">
            <center>
            <img class="img" src="${pageContext.request.contextPath}/img/acai.png" alt="AcaiBanner" width="95%" height="250"> 
            </center>
        </div>
        
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.html">Açai+Sabores</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.html">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/ListarProduto">Produtos</a></li>
                    <li><a href="${pageContext.request.contextPath}/contato.jsp">Contato</a></li>
                    <li><a href="${pageContext.request.contextPath}/Cliente/gerenciarCliente.jsp">Gerenciar Conta</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/Cliente/cadastrarCliente.jsp"><span class="glyphicon glyphicon-user"></span> Cadastrar</a></li>
                    <li><a href="${pageContext.request.contextPath}/logarCliente.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </ul>
            </div>
        </nav>
        
        <div class="container">
            <h2 align="center">Cadastrar Cliente</h2>
            <form method="get" action="${pageContext.request.contextPath}/CadastrarCliente" name="frmDadosCliente">
                <input type="hidden" class="form-control" name="idCliente" value="${cliente.idCliente}">
                <div class="form-group">
                    <label>CPF : </label>
                    <input type="text" class="form-control" name="cpf" value="${cliente.cpf}">
                </div>
                <div class="form-group">
                    <label>Senha : </label>
                    <input type="text" class="form-control" name="senha" value="${cliente.senha}">
                </div>
                <div class="form-group">
                    <label>Nome Cliente : </label>
                    <input type="text" class="form-control" name="nomeCliente" value="${cliente.nomeCliente}">
                </div>
                <div class="form-group">
                    <label>Telefone :</label>
                    <input type="text" class="form-control" name="telefone" value="${cliente.telefone}">
                </div>
                <div class="form-group">
                    <label>Data de Nascimento :</label>
                    <input type="date" class="form-control" name="dataNasc" value="${cliente.dataNasc}">
                </div>
                <div class="col-lg-12" style="text-align: center;">
                <input type="submit" class="btn btn-info" value="Salvar">
                </div>
            </form>
        </div>
        
        <h3 align="center">${menssagem}</h3>
        
        <div class="container">
            <h2 align="center">Lista de Clientes Cadastrados!!!</h2>           
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>IdCliente</th>
                        <th>CPF</th>
                        <th>Nome do Cliente</th>
                        <th>Telefone</th>
                        <th>Data de Nascimento</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listacliente}" var="cliente">
                        <tr>
                            <td>${cliente.idCliente}</td>
                            <td>${cliente.cpf}</td>
                            <td>${cliente.nomeCliente}</td>
                            <td>${cliente.telefone}</td>
                            <td>${cliente.dataNasc}</td>
                            <td><a href="${pageContext.request.contextPath}/ExcluirCliente?idCliente=${cliente.idCliente}"> Excluir </a></td>
                            <td><a href="${pageContext.request.contextPath}/CarregarCliente?idCliente=${cliente.idCliente}"> Alterar </a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
