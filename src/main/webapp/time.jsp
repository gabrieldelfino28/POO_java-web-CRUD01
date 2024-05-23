<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css">
<title>Time</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<hr>

	<div class="form-style" align="center">
		<h1>Time</h1>
		<form action="time" method="post">
			<table>
				<tr>
					<td colspan="3">
						<input type="number" min="0" id="codTime" name="codTime" placeholder="Código"
						value='<c:out value="${time.codTime}"></c:out>'>
					</td>
					<td>
						<input type="submit" id="enviar" name="enviar" value="Buscar">
					</td>
				</tr>
				<tr>
					<td colspan="4"><input type="text" id="nomeTime" name="nomeTime" placeholder="Nome"
					value='<c:out value="${time.nomeTime}"></c:out>'></td>
				</tr>
				<tr>
					<td colspan="4"><input type="text" id="cidade" name="cidade" placeholder="Cidade " 
					value='<c:out value="${time.cidade}"></c:out>'></td>
				</tr>
				<tr>
					<td><input type="submit" id="enviar" name="enviar" value="Cadastrar"></td>
					<td><input type="submit" id="enviar" name="enviar" value="Alterar"></td>
					<td><input type="submit" id="enviar" name="enviar" value="Excluir"></td>
					<td><input type="submit" id="enviar" name="enviar" value="Listar"></td>
				</tr>
			</table>
		</form>
	</div>
	<br>
	<br>
	<div align="center">
		<c:if test="${not empty err}">
			<h2><b><c:out value="${err}"/></b></h2>
		</c:if>
		<c:if test="${not empty saida}">
			<p class="err"><b><c:out value="${saida}"/></b></p>
		</c:if>
	</div>
	<br>
	<br>
	<div class="form-style" align="center">
		<c:if test="${not empty times}">
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Cidade</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="t" items="${times}">
						<tr>
							<td><c:out value="${t.codTime}"/></td>
							<td><c:out value="${t.nomeTime}"/></td>
							<td><c:out value="${t.cidade}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>