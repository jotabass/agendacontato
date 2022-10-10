<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alterar Senha de acesso</title>

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
		
		<h5>Alterar senha de acesso</h5>
		<p>Utilize o formulário para modificar sua senha de acesso ao sistema.</p>
		<hr/>
		
		<form id="formEdicao" method="post" action="atualizar-senha">
		
			<div class="row mb-3">
				<div class="col-md-4">
					<label>Informe a nova senha:</label>
					<input type="password" name="novasenha" id="novasenha" class="form-control"/>
				</div>
				<div class="col-md-4">
					<label>Confirme a nova senha:</label>
					<input type="password" name="novasenhaconfirmacao" id="novasenhaconfirmacao" class="form-control"/>
				</div>
			</div>
			
			<div class="row mb-3">
				<div class="col-md-4">
					<input type="submit" value="Atualizar Senha" class="btn btn-primary"/>
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
	
	<script>
		$(document).ready(function(){
			
			//validação do formulário
			$("#formEdicao").validate({
				rules: {
					"novasenha" : { required: true, minlength: 8, maxlength: 20 },
					"novasenhaconfirmacao" : { required : true, equalTo: "#novasenha" }
				}
			});			
		});
	</script>

</body>
</html>


