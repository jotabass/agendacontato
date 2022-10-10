<!-- menu do sistema -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Agenda de Contatos</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="/projetomvc01/admin/pagina-inicial">Página
						inicial</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" role="button"
					data-bs-toggle="dropdown" aria-expanded="false"> Gerenciar
						contatos </a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item"
							href="/projetomvc01/admin/cadastrar-contatos">Cadastrar
								Contatos</a></li>
						<li><a class="dropdown-item"
							href="/projetomvc01/admin/consultar-contatos">Consultar
								Contatos</a></li>
					</ul></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" role="button"
					data-bs-toggle="dropdown" aria-expanded="false"> Gerenciar
						minha conta </a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item"
							href="/projetomvc01/admin/alterar-senha">Alterar senha de
								acesso</a></li>
					</ul></li>
			</ul>

			<div class="d-flex">
				<div class="text-white">
					<strong>${usuario_auth.nome}</strong> (${usuario_auth.email})
					&nbsp;&nbsp;&nbsp; <a href="/projetomvc01/logout"
						class="btn btn-outline-light btn-sm"
						onclick="return confirm('Deseja realmente encerrar sua sessão?');">
						Sair da Agenda </a>
				</div>
			</div>

		</div>
	</div>
</nav>

