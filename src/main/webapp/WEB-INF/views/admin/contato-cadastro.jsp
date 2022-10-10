<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Contatos</title>

<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
	
<style>
	label.error { color: #d9534f; }
	input.error { border: 2px solid #d9534f; }
</style>

</head>
<body>

	<!-- inserindo o componente navbar -->
	<jsp:include page="/WEB-INF/views/components/navbar.jsp"></jsp:include>

	<div class="container mt-4">
		
		<h5>Cadastro de Contatos</h5>
		<p>Preencha o formulário para incluir um contato.</p>
		<hr/>
		
		<form id="formContato" method="post" action="cadastrar-contato">
		
			<div class="row mb-3">
				<div class="col-md-8">
					<label>Nome do contato:</label>
					<input type="text" name="nome" class="form-control"/>
				</div>
				<div class="col-md-4">
					<label>Telefone do contato:</label>
					<input type="text" name="telefone" id="telefone" class="form-control"/>
				</div>
			</div>
			
			<div class="row mb-3">
				<div class="col-md-8">
					<label>Email:</label>
					<input type="text" name="email" class="form-control"/>
				</div>
				<div class="col-md-4">
					<label>Data de Nascimento:</label>
					<input type="text" name="datanasc" id="datanasc" class="form-control"/>
				</div>
			</div>
			
			<div class="row mb-3">
				<div class="col-md-12">
					<input type="submit" class="btn btn-success" value="Realizar Cadastro"/>
					<input type="reset" class="btn btn-secondary" value="Limpar"/>
				</div>
			</div>
		
		</form>
		
		<!-- inserindo o componente messages -->
		<jsp:include page="/WEB-INF/views/components/messages.jsp"></jsp:include>
		
	</div>

	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

	<!-- JQuery -->
	<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
	
	<!-- JQuery validate -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/additional-methods.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/localization/messages_pt_BR.min.js"></script>
	
	<!-- JQuery masked input -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.min.js"></script>
	
	<script>
		$(document).ready(function(){
			
			//validação do formulário
			$("#formContato").validate({
				rules: {
					"nome" : { required: true, minlength: 8, maxlength: 150 },
					"telefone" : { required: true },
					"email" : { required: true, email: true },
					"datanasc" : { required : true }
				}
			});
			
			//aplicar as máscaras dos campos
			$("#telefone").mask("(99) 99999-9999");
			$("#datanasc").mask("99/99/9999");
			
		});
	</script>

</body>
</html>












