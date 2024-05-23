<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css">
<title>Jogador</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<hr>

	<div class="form-style" align="center">
		<h1>Jogador</h1>
		<form action="jogador" method="post">
			<table>
				<tr>
					<td colspan="3">
						<input type="number" min="0" id="idJogador" name="idJogador" placeholder="Código"
						value='<c:out value="${jogador.idJogador}"></c:out>'>
					</td>
					<td>
						<input type="submit" id="enviar" name="enviar" value="Buscar">
					</td>
				</tr>
				<tr>
					<td colspan="4"><input type="text" id="nomeJogador" name="nomeJogador" placeholder="Nome"
					value='<c:out value="${ jogador.nomeJogador }"></c:out>'></td>
				</tr>
				<tr>
					<td colspan="4"><input type="date" id="dataNasc" name="dataNasc" placeholder="Data Nascimento" 
					value='<c:out value="${ jogador.dataNasc}"></c:out>'></td>
				</tr>
				<tr>
					<td colspan="4"><input type="number" step="0.01" min="0" id="altura" name="altura" placeholder="Altura"
					value='<c:out value="${ jogador.altura }"></c:out>'></td>
				</tr>
				<tr>
					<td colspan="4"><input type="number" step="0.01" min="0" id="peso" name="peso" placeholder="Peso"
					value='<c:out value="${ jogador.peso }"></c:out>'></td>
				</tr>
				<tr>
					<td colspan="4">
						<select id="codTime" name="codTime">
							<option value ="0">Escolha um Time</option>
							<c:forEach var="t" items="${times}">
								<c:if test="${(empty jogador) || (t.codTime ne jogador.time.codTime)}">
									<option value="${t.codTime}">
										<c:out value="${t}"/>
									</option>
								</c:if>
								<c:if test="${t.codTime eq jogador.time.codTime}">
								<option value="${t.codTime}" selected="selected">
									<c:out value="${t}" />
								</option>
							</c:if>
							</c:forEach>
						</select>
					</td>
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
		<c:if test="${not empty jogadores }">
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>DataNasc</th>
						<th>Altura</th>
						<th>Peso</th>
						<th>Time</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="j" items="${jogadores}">
						<tr>
							<td><c:out value="${j.idJogador}"/></td>
							<td><c:out value="${j.nomeJogador}"/></td>
							<td><c:out value="${j.dataNasc}"/></td>
							<td><c:out value="${j.altura}"/></td>
							<td><c:out value="${j.peso}"/></td>
							<td><c:out value="${j.time}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
</body>
</html>