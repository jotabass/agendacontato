<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bem vindo a Agenda de Contatos</title>

<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body>

	<!-- inserindo o componente navbar -->
	<jsp:include page="/WEB-INF/views/components/navbar.jsp"></jsp:include>

	<div class="container mt-4">
		<h5>Seja bem vindo a Agenda de Contatos!</h5>
		<hr/>
		
		<div class="row mt-2">
			<div class="col-md-4">
				<table class="table table-sm">
					<thead>
						<tr>
							<th>Data de cadastro</th>
							<th>Quantidade de contatos</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${consulta}" var="item">
							<tr>
								<td>${item.dataCadastro}</td>
								<td>${item.quantidade}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-md-8">
				<div id="grafico"></div>
			</div>
		</div>
		
	</div>

	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

	<!-- referencias para o highcharts -->
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/highcharts-3d.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/export-data.js"></script>

	<!-- montando o gráfico -->
	<script>
		
		var array = [];
		
		<c:forEach items="${consulta}" var="item">
			array.push(['${item.dataCadastro}', ${item.quantidade}]);
		</c:forEach>
    	    	
    	new Highcharts.Chart({
            chart: {
                type: 'pie',
                renderTo: 'grafico'
            },
            plotOptions: {
                pie: {
                    innerSize: '60%'
                }
            },
            title: {
                text: 'Quantidade de contatos cadastrados por data'
            },
            credits: {
                enabled: false
            },
            series: [{
                data: array
            }]
        });
	
	</script>

</body>
</html>
