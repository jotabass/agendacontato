<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agenda de Contatos - Esqueci minha senha</title>

<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
label.error {
	color: #d9534f;
}

input.error {
	border: 2px solid #d9534f;
}
</style>


</head>
<body class="bg-secondary">

	<div class="row">
		<div class="col-md-4 offset-md-4">

			<div class="card mt-5">
				<div class="card-body text-center">

					<h2>Esqueci minha senha</h2>
					<p>Preencha o formulário para recuperar sua senha:</p>
					<hr />

					<form id="formPassword" method="post" action="recuperar-senha">

						<div class="text-start mb-2">
							<label>Email de acesso:</label> <input type="text" name="email"
								placeholder="Digite aqui" class="form-control" />
						</div>

						<div class="d-grid mb-2">
							<input type="submit" value="Confirmar e recuperar senha"
								class="btn btn-success" />
						</div>

						<div class="d-grid mb-2">
							<a href="/projetomvc01/" class="btn btn-primary"> Voltar </a>
						</div>

						<div class="mb-2 text-center">
							<small>Treinamento Java WebDeveloper - COTI Informática</small>
						</div>

					</form>
						<div class="text-success text-center">
						<strong>${mensagem_sucesso}</strong>
					</div>
					
					<div class="text-danger text-center">
						<strong>${mensagem_erro}</strong>
					</div>

				</div>
			</div>

		</div>

	</div>

	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
	<!-- JQuery -->
	<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>



	<!-- JQuery validate -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/additional-methods.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/localization/messages_pt_BR.min.js"></script>


	<script>
		$(document).ready(function() {
			$("#formPassword").validate({
				rules : {
					"email" : {
						required : true,
						email : true
					}
				}
			});
		});
	</script>



</body>
</html>

