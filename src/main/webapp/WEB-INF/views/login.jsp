<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Agenda de Contatos - Acesse sua conta</title>

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

					<img
						src="https://www.cotiinformatica.com.br/imagens/logo-coti-informatica.png" />

					<h3>Acesse sua conta</h3>
					<p>Entre com suas credenciais de acesso:</p>
					<hr />

					<form id="formLogin" method="post" action="autenticar-usuario">

						<div class="text-start mb-2">
							<label>Email de acesso:</label> <input type="text" name="email"
								placeholder="Digite aqui" class="form-control" />
						</div>

						<div class="text-start mb-2">
							<label>Senha de acesso:</label> <input type="password"
								name="senha" placeholder="Digite aqui" class="form-control" />
							<div>
								<a href="/projetomvc01/esqueci-minha-senha">Esqueci minha
									senha?</a>
							</div>
						</div>

						<div class="d-grid mb-2">
							<input type="submit" value="Acessar Agenda"
								class="btn btn-success" />
						</div>

						<div class="d-grid mb-2">
							<a href="/projetomvc01/crie-sua-conta" class="btn btn-primary">
								Não possui conta? Cadastre-se aqui </a>
						</div>

						<div class="mb-2 text-center">
							<small>Treinamento Java WebDeveloper - COTI Informática</small>
						</div>

					</form>

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
			$("#formLogin").validate({
				rules : {
					"email" : {
						required : true,
						email : true
					},
					"senha" : {
						required : true,
						minlength : 8,
						maxlength : 20
					}
				}
			});
		});
	</script>

</body>
</html>




