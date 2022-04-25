<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row mb-4">
		<c:if test="${!empty sessionScope.message }">
			<div class="alert alert-success mt-3">
				${ sessionScope.message }
				<c:remove var="message" scope="session"/>
			</div>
		</c:if>
		<c:if test="${!empty sessionScope.error }">
			<div class="alert alert-danger mt-3">
				${ sessionScope.error }
				<c:remove var="error" scope="session"/>
			</div>
		</c:if>
	<form class="col-6 mt-2" action="/SANGTM_PH17730_ASM/categories/store" method="post">
		<div class="mb-3">
			<label for="category" class="form-label">Tên danh mục</label> <input
				type="text" class="form-control" id="category" name="ten">
		</div>
		<button type="submit" class="btn btn-primary">Thêm danh mục</button>
		<button type="reset" class="btn btn-primary">Xóa form</button>
	</form>
</div>