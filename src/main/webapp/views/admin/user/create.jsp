<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
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
	<form class="col-6 mt-2" action="/SANGTM_PH17730_ASM/users/store" method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="fullname" class="form-label">Họ tên</label> <input
				type="text" class="form-control" id="fullname" name="hoTen">
		</div>
		<div class="mb-3">
			<label class="form-label">Giới tính</label> <br> <input
				type="radio" class="form-check-input" id="nam" value="1"
				name="gioiTinh" checked> <label for="nam"
				class="form-check-label me-4">Nam</label> <input type="radio"
				class="form-check-input" id="nu" value="0" name="gioiTinh">
			<label for="nu" class="form-check-label">Nữ</label>
		</div>
		<c:if test="${!empty sessionScope.sdt }">
			<div class="alert alert-danger mb-3">
				${ sessionScope.sdt }
				<c:remove var="sdt" scope="session"/>
			</div>
		</c:if>
		<div class="mb-3">
			<label for="sdt" class="form-label">Số điện thoại</label> <input
				type="text" class="form-control" id="sdt" name="sdt">
		</div>
		<div class="mb-3">
			<label for="diachi" class="form-label">Địa chỉ</label> <input
				type="text" class="form-control" id="diachi" name="diaChi">
		</div>
		<c:if test="${!empty sessionScope.email }">
			<div class="alert alert-danger mb-3">
				${ sessionScope.email }
				<c:remove var="email" scope="session"/>
			</div>
		</c:if>
		<div class="mb-3">
			<label for="email" class="form-label">Email</label> <input
				type="text" class="form-control" id="email" name="email">
		</div>
		<div class="mb-3">
			<label for="pass" class="form-label">Mật khẩu</label> <input
				type="password" class="form-control" id="pass" name="password">
		</div>
		<button type="submit" class="btn btn-primary">Thêm tài khoản</button>
		<button type="reset" class="btn btn-primary">Xóa form</button>
	</form>
	<div class="col-3 offset-md-1">
		<img alt="" src="" >
	</div>
</div>