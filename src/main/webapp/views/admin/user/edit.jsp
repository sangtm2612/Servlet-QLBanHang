<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row mb-4 mt-2">
	<form class="col-6 mt-2" action="/SANGTM_PH17730_ASM/users/update?id=${user.id }" method="post">
		<div class="mb-3">
			<label for="fullname" class="form-label">Họ tên</label> <input
				type="text" class="form-control" id="fullname" value="${user.hoTen }" name="hoTen">
		</div>
		<div class="mb-3">
			<label class="form-label">Giới tính</label> <br> <input
				type="radio" class="form-check-input" id="nam" value="1"
				name="gioiTinh" ${user.gioiTinh == 1 ? "checked" : "" }> <label for="nam"
				class="form-check-label me-4">Nam</label> <input type="radio"
				class="form-check-input" id="nu" value="0" name="gioiTinh" ${user.gioiTinh == 0 ? "checked" : "" }>
			<label for="nu" class="form-check-label">Nữ</label>
		</div>
		<div class="mb-3">
			<label for="sdt" class="form-label">Số điện thoại</label> <input
				type="text" class="form-control" value="${user.sdt }" id="sdt" name="sdt">
		</div>
		<div class="mb-3">
			<label for="diachi" class="form-label">Địa chỉ</label> <input
				type="text" class="form-control" value="${user.diaChi }" id="diachi" name="diaChi">
		</div>
		<div class="mb-3">
			<label for="email" class="form-label">Email</label> <input
				type="text" class="form-control" id="email" value="${user.email }" name="email">
		</div>
		<div class="mb-3">
			<label for="pass" class="form-label">Mật khẩu</label> <input
				type="password" class="form-control" id="pass" name="password">
		</div>
		<button type="submit" class="btn btn-primary">Cập nhật</button>
		<button type="reset" class="btn btn-primary">Xóa form</button>
	</form>
	<div class="col-3 offset-md-1">
		<img alt="" src="" >
	</div>
</div>