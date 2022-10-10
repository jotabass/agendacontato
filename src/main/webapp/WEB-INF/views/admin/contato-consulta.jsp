<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta de Contatos</title>

<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
	
<link
	href="//cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css"
	rel="stylesheet">

</head>
<body>

	<!-- inserindo o componente navbar -->
	<jsp:include page="/WEB-INF/views/components/navbar.jsp"></jsp:include>

	<div class="container mt-4 mb-5">
		
		<h5>Consulta de Contatos</h5>
		<p>Listagem de contatos cadastrados.</p>
		<hr/>
		
		<!-- inserindo o componente messages -->
		<jsp:include page="/WEB-INF/views/components/messages.jsp"></jsp:include>
		
		<table id="tabela_contatos" class="table table-sm">
			<thead>
				<tr>
					<th>Nome do contato</th>
					<th>Telefone</th>
					<th>Email</th>
					<th>Data de nascimento</th>
					<th>Operações</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${lista_contatos}" var="contato">
					
					<tr>
						<td>${contato.nome}</td>
						<td>${contato.telefone}</td>
						<td>${contato.email}</td>
						<td>
							<fmt:formatDate value="${contato.dataNasc}" pattern="dd/MM/yyyy"/>
						</td>
						<td>
							<a href="/projetomvc01/admin/editar-contato?id=${contato.idContato}" 
								class="btn btn-sm btn-primary">
								Editar
							</a>
							<a href="/projetomvc01/admin/excluir-contato?id=${contato.idContato}"
								onclick="return confirm('Deseja realmente excluir o contato ${contato.nome}?');" 
								class="btn btn-sm btn-danger">
								Excluir
							</a>
						</td>
					</tr>
					
				</c:forEach>		
				
				
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						Quantidade de contatos: ${lista_contatos.size()}
					</td>
				</tr>
			</tfoot>
		</table>
		
	</div>	
	
	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

	<!-- JQuery -->
	<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
	
	<!-- JQuery DataTables -->
	<script src="//cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>

	<script>
		$(document).ready( function () {
	    	$('#tabela_contatos').DataTable({
	    		language: {
	    			url : "//cdn.datatables.net/plug-ins/1.12.1/i18n/pt-BR.json"
	    		}
	    	});
		});
	</script>

</body>
</html>









