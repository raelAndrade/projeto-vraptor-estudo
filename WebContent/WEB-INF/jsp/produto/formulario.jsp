<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Novo Produto</title>
</head>
<body>
<table>
	<form action="<c:url value="/produto/adiciona"/>" method="post">
		<tr>
			<td>Nome</td><td><input name="produto.nome"/></td>
		</tr>
		<tr>
			<td>Descricao</td><td><input name="produto.descricao"/></td>
		</tr>
		<tr>
			<td>Preco</td><td><input name="produto.preco"/></td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td>Cor</td><td><input name="produto.cor"/></td> -->
<!-- 		</tr> -->
		<tr>
			<td><input type="submit" value="Adiciona"/></td>
		</tr>
	</form>
</table>
</body>
</html>