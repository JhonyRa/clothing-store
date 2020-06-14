<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<c:import url="/common/head.jsp" />

<body>
	<div class="container-fluid">

		<div class="row">
	
			<c:import url="/common/side-bar.jsp" />
			
			<div class="col-9">
				
				<div class="row">
					<div class="col-12">
						<h3>Catálogo</h3>
					</div>
				</div>
				
				
				<div class="row">
					<section class="container row">
						<div class="col-lg-4 col-md-6 mb-4">
							<c:forEach items="${ clothes }" var="clothe">
								<form method="post">
									<div class="card" style="width: 18rem;">
										<a href="#"><img class="card-img-top"
											src="assets/img/catalog/${ clothe.imageName }" alt=""></a>
										<div class="card-body">
											<h4 class="card-title">
												${ clothe.name }
											</h4>
											<h5><fmt:formatNumber value="${ clothe.price }" type="currency"
												currencySymbol="R$" />
											</h5>
										</div>
									</div>
									<div class="btn-group">
										<button type="submit" name="add" value="${ clothe.code }" class="btn btn-primary">Adicionar</button>
									</div>
								</form>
							</c:forEach>
						</div>
						
						<div class="col-3 text-right">
							<h5>Filtros</h5>

							<form method="get">
								<div class="form-group">
									<label for="category">Categorias</label> <select multiple
										name="category" class="form-control" id="categories">
										<c:forEach items="${ categories }" var="category">
											<option value="${ category.code }">${ category.name }</option>
										</c:forEach>
									</select>
								</div>

								<div class="btn-group">
									<button class="btn btn-warning" type="reset">Limpar Seleção</button>
									<button class="btn btn-primary">Filtrar</button>
								</div>

							</form>

						</div>
					</section>
				</div>
			</div>
		</div>
	</div>

	<c:import url="/common/footer.jsp" />

</body>
</html>