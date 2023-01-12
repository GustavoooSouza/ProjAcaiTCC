<%-- 
    Document   : cadastrarCliente
    Created on : 15/04/2022, 15:07:30
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <title>Gerenciar Conta</title>
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
                    <li class="active"><a href="index.jsp">Home</a></li>
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

        <div class="container">
            <h2 align="center">Seus dados cadastrados!!!</h2>           
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
                    <tr>
                        <td>${sessionScope.clientelogado.idCliente}</td>
                        <td>${sessionScope.clientelogado.cpf}</td>
                        <td>${sessionScope.clientelogado.nomeCliente}</td>
                        <td>${sessionScope.clientelogado.telefone}</td>
                        <td>${sessionScope.clientelogado.dataNasc}</td>
                    </tr>                   
                </tbody>
            </table>
        </div>

        <div class="container">
            <h2 align="center">Cadastrar Endereco</h2>
            <form method="get" action="${pageContext.request.contextPath}/CadastrarEndereco" name="frmDadosEndereco">
                <input type="hidden" class="form-control" name="idEndereco" value="">
                <input type="hidden" class="form-control" name="idCliente" value="${sessionScope.clientelogado.idCliente}">
                <div class="form-group">
                    <label>Bairro : </label>
                    <input type="text" class="form-control" name="bairro" value="${endereco.bairro}">
                </div>
                <div class="form-group">
                    <label>Rua : </label>
                    <input type="text" class="form-control" name="rua" value="${endereco.rua}">
                </div>
                <div class="form-group">
                    <label>Numero da Casa : </label>
                    <input type="text" class="form-control" name="numeroCasa" value="${endereco.numeroCasa}">
                </div>
                <div class="form-group">
                    <label>Complemento :</label>
                    <input type="text" class="form-control" name="Complemento" value="${endereco.complemento}">
                </div>
                <div class="col-lg-12" style="text-align: center;">
                    <input type="submit" class="btn btn-info" value="Cadastrar">
                </div>
            </form>
        </div>
    <center><h3>${menssagem}</h3></center>
    <div class="container">
        <h2 align="center">Lista de Endereços Cadastrados!!!</h2>           
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Bairro</th>
                    <th>Rua</th>
                    <th>Numero da Casa</th>
                    <th>Complemento</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listaendereco}" var="endereco">
                    <tr>
                        <td>${endereco.idEndereco}</td>
                        <td>${endereco.bairro}</td>
                        <td>${endereco.rua}</td>
                        <td>${endereco.numeroCasa}</td>
                        <td>${endereco.complemento}</td>
                        <td><a href="${pageContext.request.contextPath}/ExcluirEndereco?idEndereco=${endereco.idEndereco}"> Excluir </a></td>
                        <td><a href="${pageContext.request.contextPath}/CarregarEndereco?idEndereco=${endereco.idEndereco}"> Alterar </a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>




</body>
</html>
