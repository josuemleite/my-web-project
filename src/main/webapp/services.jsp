<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<%@include file="base-head.jsp"%>
	</head>
	<body>
		<%@include file="modal.html"%>
		<%@include file="nav-menu.jsp"%>
			
		<div id="container" class="container-fluid">
			<div id="alert" style="${not empty message ? 'display: block;' : 'display: none;'}" class="alert alert-dismissable ${alertType eq 1 ? 'alert-success' : 'alert-danger'}">
			  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			  ${message}
			</div>
		
	 	 	<div id="top" class="row">
	 			<div class="col-md-3">
			        <h3>Serviços</h3>
			    </div>
			 
			    <div class="col-md-6">
			        <div class="input-group h2">
			            <input name="data[search]" class="form-control" id="search" type="text" placeholder="Pesquisar serviços">
			            <span class="input-group-btn">
			                <button class="btn btn-danger" type="submit">
			                    <span class="glyphicon glyphicon-search"></span>
			                </button>
			            </span>
			        </div>
			    </div>
			 
			    <div class="col-md-3">
			        <a href="/crud-manager/service/form" class="btn btn-danger pull-right h2"><span class="glyphicon glyphicon-plus" /></span>&nbspAdicionar Serviço</a>
			    </div>
	     	</div>
	 
	     	<hr />
	     	
	     	<div id="list" class="row">
	     		<div class="table-responsive col-md-12">
			        <table class="table table-striped table-hover" cellspacing="0" cellpadding="0">
			            <thead>
			                <tr>
			                    <th>Tipo de serviço</th>
			                    <th>Valor por hora (R$)</th>
			                    <th>Expertise</th>
			                    <th>Usuário</th>
			                    <th>Portfolio</th>
			                    <th>Editar</th>
			                    <th>Excluir</th>
			                 </tr>
			            </thead>
			            <tbody>
			            	<c:forEach var="service" items="${services}">
								<tr>
				                    <td>${service.getTypeOfService()}</td>
				                    <td>${service.getValuePerHour()}</td>
				                    <td>${service.getExpertiseLevel()}</td>
				                    <td>${service.getUser().getName()}</td>	
				                    <td>${service.getPortfolio()}</td>	                    
				                    <td class="actions">
				                        <a class="btn btn-info btn-xs" 
				                           href="${pageContext.request.contextPath}/service/update?serviceId=${service.getId()}" >
				                           <span class="glyphicon glyphicon-edit"></span>
				                        </a>
				                    </td>
				                    <td class="actions">
				                        <a class="btn btn-danger btn-xs modal-remove"
				                           service-id="${service.getId()}" 
				                           service-type="${service.getTypeOfService()}" data-toggle="modal" 
				                           data-target="#delete-modal"  href="#"><span 
				                           class="glyphicon glyphicon-trash"></span></a>
				                    </td>
				                </tr>
							</c:forEach>
			            </tbody>
			         </table>
			 
			     </div>
	     	</div>
	 
	     	<div id="bottom" class="row">
	     		<div class="col-md-12">
			        <ul class="pagination">
			            <li class="disabled"><a>&lt; Anterior</a></li>
			            <li class="disabled"><a>1</a></li>
			            <li><a href="#">2</a></li>
			            <li><a href="#">3</a></li>
			            <li class="next"><a href="#" rel="next">Próximo &gt;</a></li>
			        </ul><!-- /.pagination -->
			    </div>
	     	</div>
		</div>
		
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
			    setTimeout(function() {
			        $("#alert").slideUp(500);
			    }, 3000);
			    
			    $(".modal-remove").click(function () {
		            var serviceType = $(this).attr('service-type');
		            var serviceId = $(this).attr('service-id');
		            $(".modal-body #hiddenValue").text("o serviço '"+serviceType+"'");
		            $("#id").attr( "value", serviceId);
		            $("#form").attr("action","service/delete");
		        })
			});
		</script>
	</body>
</html>
