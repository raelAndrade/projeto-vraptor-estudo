<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Produtos</title>
</head>
<body>

<span style="color: red;">${mensagem}</span>

<table>
	<c:forEach var="produto" items="${produtoList }">
		<tr>
			<td>${produto.nome }</td>
			<td>${produto.descricao }</td>
			<td>${produto.preco }</td>
<%-- 		<td>${produto.cor }</td> --%>
		</tr>
	</c:forEach>
</table>


</body>
</html>