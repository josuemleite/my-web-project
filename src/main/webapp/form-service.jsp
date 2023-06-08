<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<%@include file="base-head.jsp"%>
	</head>
	<body>
		<%@include file="nav-menu.jsp"%>
			
		<div id="container" class="container-fluid">
			<h3 class="page-header">Adicionar Serviço</h3>

			<form action="${pageContext.request.contextPath}/service/${action}" method="POST">
				<input type="hidden" value="${service.getId()}" name="serviceId">
				<div class="row">
					<div class="form-group col-md-3">
					<label for="type_of_service">Tipo de serviço</label>
						<input type="text" class="form-control" id=type_of_service name="type_of_service" 
							   autofocus="autofocus" placeholder="Tipo de serviço" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o tipo de serviço.')"
							   oninput="setCustomValidity('')"
							   value="${service.getTypeOfService()}">
					</div>
					
					<div class="form-group col-md-2">
					<label for="value_per_hour">Valor por hora</label>
						<input type="number" min="0" step=".01" class="form-control" id="value_per_hour" name="value_per_hour" 
							   autofocus="autofocus" placeholder="Valor por hora do serviço em real" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o valor por hora do serviço.')"
							   oninput="setCustomValidity('')"
							   value="${service.getValuePerHour()}">
					</div>

					<div class="form-group col-md-2">
					<label for="expertise_level">Expertise</label>
						<input type="text" class="form-control" id="expertise_level" name="expertise_level" 
							   autofocus="autofocus" placeholder="Nível de expertise" 
							   required oninvalid="this.setCustomValidity('Por favor, informe o nível de expertise do prestador de serviço.')"
							   oninput="setCustomValidity('')"
							   value="${service.getExpertiseLevel()}">
					</div>
					
					<div class="form-group col-md-2">
					<label for="portfolio">Portfolio</label>
						<input type="text" class="form-control" id="portfolio" name="portfolio" 
							   autofocus="autofocus" placeholder="Endereço do portfolio" 
							   oninput="setCustomValidity('')"
							   value="${service.getPortfolio()}">
					</div>

					<div class="form-group col-md-3">
						<label for="user">Usuário</label>
						<select id="user" class="form-control selectpicker" name="user" 
							    required oninvalid="this.setCustomValidity('Por favor, informe o usuário prestador do serviço.')"
							    oninput="setCustomValidity('')">
						  <option value="" disabled ${not empty user ? "" : "selected"}>Selecione um usuário</option>
						  <c:forEach var="user" items="${users}">
						  	<option value="${user.getId()}"  ${service.getUser().getId() == user.getId() ? "selected" : ""}>
						  		${user.getName()}
						  	</option>	
						  </c:forEach>
						</select>
					</div>
				</div>
				<hr />
				<div id="actions" class="row pull-right">
					<div class="col-md-12">
						<a href="${pageContext.request.contextPath}/companies" class="btn btn-default">Cancelar</a>
						<button type="submit" class="btn btn-primary">${not empty service ? "Alterar Serviço" : "Criar Serviço"}</button>
					</div>
				</div>
			</form>
		</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>
