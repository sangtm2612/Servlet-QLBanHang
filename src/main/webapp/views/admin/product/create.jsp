<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row mb-4">
	<c:if test="${!empty sessionScope.message }">
		<div class="alert alert-success mt-3">
			${ sessionScope.message }
			<c:remove var="message" scope="session" />
		</div>
	</c:if>
	<c:if test="${!empty sessionScope.error }">
		<div class="alert alert-danger mt-3">
			${ sessionScope.error }
			<c:remove var="error" scope="session" />
		</div>
	</c:if>
	<form class="col-6 mt-2" action="/SANGTM_PH17730_ASM/products/store"
		method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="danhmuc" class="form-label">Danh mục</label> <select
				name=categoryId class="form-select">
				<c:forEach items="${listCategory }" var="c">
					<option value="${c.id }">${c.ten }</option>
				</c:forEach>
			</select>
		</div>
		<div class="mb-3">
			<label for="name" class="form-label">Tên sản phẩm</label> <input
				type="text" class="form-control" id="name" name="ten">
		</div>
		<div class="mb-3">
			<label for="soLuong" class="form-label">Số lượng</label> <input
				type="number" class="form-control" id="soLuong" name="soLuong">
		</div>
		<div class="mb-3">
			<label for="donGia" class="form-label">Đơn giá</label> <input
				type="number" class="form-control" id="donGia" name="donGia">
		</div>
		<div class="mb-3">
			<label for="img" class="form-label">Ảnh</label> <input type="file"
				class="form-control" id="img" name="img">
		</div>
		<button type="submit" class="btn btn-primary">Thêm sản phẩm</button>
		<button type="reset" class="btn btn-primary">Xóa form</button>
		<a class="btn btn-primary" href="/SANGTM_PH17730_ASM/products/export">Xuất
			excel</a> <a class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#exampleModal"> Nhập excel </a>
	</form>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form action="/SANGTM_PH17730_ASM/products/import" method="post" enctype="multipart/form-data">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Nhập file
							excel</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label for="exampleInputEmail1" class="form-label">File
								excel:</label> <input type="file" class="form-control"
								id="exampleInputEmail1" name="excel">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Hủy</button>
						<button type="submit" class="btn btn-primary">Đọc file</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="col-3 offset-md-1">
		<img alt="" src="">
	</div>
</div>