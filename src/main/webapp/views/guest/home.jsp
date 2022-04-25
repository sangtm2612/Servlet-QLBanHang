<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<div class="mt-3">
	<c:if test="${!empty sessionScope.error }">
		<div class="alert alert-danger">
				${ sessionScope.error }
			<c:remove var="error" scope="session"/>
		</div>
	</c:if>
	<c:if test="${!empty sessionScope.message }">
		<div class="alert alert-success">
				${ sessionScope.message }
			<c:remove var="message" scope="session"/>
		</div>
	</c:if>
	<div>
		<h4 class="fw-bold text-danger">${danhMuc }</h4>
	</div>
	<hr class="bg-black" style="height: 3px">
		<div class="row ">
	<c:forEach items="${listProduct }" var="product">
			<div class="card col-2 m-3 text-center">
				<img src="${product.img }" 
					class="card-img-top mt-2" alt="...">	
				<div class="card-body">
					<h5 class="card-title">${ product.ten }</h5>
					<p class="card-text text-danger fw-bold"><fmt:formatNumber type="number" pattern="##,###₫" value="${product.donGia}" /></p>
					<form action="/SANGTM_PH17730_ASM/home/buy?id=${product.id }&donGia=${product.donGia }" method="post">
						<div class="col-12 mb-3">
							<input type="number" name="soluong" placeholder="Số lượng" min="1" max="99"
								class="form-control form-control-sm">
						</div>
						<button type="submit" class="btn btn-primary">Mua hàng</button>
					</form>
				</div>
			</div>
	</c:forEach>
		</div>
</div>

