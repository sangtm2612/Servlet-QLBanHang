<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row mb-4">
	<form class="col-6 mt-2" action="/SANGTM_PH17730_ASM/categories/update?id=${cate.id }" method="post">
		<div class="mb-3">
			<label for="cate" class="form-label">Tên danh mục</label> <input
				type="text" class="form-control" id="cate" value=${cate.ten } name="ten">
		</div>
		<button type="submit" class="btn btn-primary">Cập nhật</button>
		<button type="reset" class="btn btn-primary">Xóa form</button>
	</form>
	<div class="col-3 offset-md-1">
		<img alt="" src="" >
	</div>
</div>