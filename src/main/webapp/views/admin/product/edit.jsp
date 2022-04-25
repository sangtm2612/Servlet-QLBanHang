<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row mb-4">
	<form class="col-6 mt-2" action="/SANGTM_PH17730_ASM/products/update?id=${product.id }" method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="danhmuc" class="form-label">Danh mục</label>
			<select class="form-select" name="categoryId">
				<c:forEach items="${listCategory }" var="cate">
					<option value=${cate.id }  ${cate.id == product.category.id ? "selected" : "" }>${cate.ten }</option>
				</c:forEach>
			</select>
		</div>
		<div class="mb-3">
			<label for="name" class="form-label">Tên sản phẩm</label> <input
				type="text" class="form-control" id="name" name="ten" value="${product.ten }">
		</div>
		<div class="mb-3">
			<label for="soLuong" class="form-label">Số lượng</label> <input
				type="number" class="form-control" id="soLuong" name="soLuong" value="${product.soLuong }">
		</div>
		<div class="mb-3">
			<label for="number" class="form-label">Đơn giá</label> <input
				type="text" class="form-control" id="donGia" name="donGia" value="${product.donGia }">
		</div>
		<div class="mb-3">
			<label for="img" class="form-label">Ảnh</label> <input
				type="file" class="form-control" id="img" name="img">
		</div>
		<button type="submit" class="btn btn-primary">Cập nhật</button>
		<button type="reset" class="btn btn-primary">Xóa form</button>
		<a class="btn btn-primary" href="">Xuất excel</a>
	</form>
	<div class="col-3 offset-md-1">
		<img alt="" src="" >
	</div>
</div>