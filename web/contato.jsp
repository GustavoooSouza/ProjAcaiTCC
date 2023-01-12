<%-- 
    Document   : Produto
    Created on : 12/06/2021, 19:30:25
    Author     : Usuário
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <title>Contato</title>
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
        
        <section>
	<div class="rede" id="facebook">
            <p align="center"><img class="icone" src="img/face.png" height="50" width="50"> Nossa pagina no Facebook!!! </p>
	</div>
	<div class="rede" id="whatsapp">
            <p align="center"><img class="icone" src="img/whats.png" height="50" widht="50"> WhatsApp - 17-99999-9999 </p>
	</div>
	<div class="rede" id="email">
            <p align="center"><img class="icone" src="img/email.png" height="50" widht="50"> acai+sabores@hotmail.com </p>
	</div>
        </section>
        
    </body>
</html>