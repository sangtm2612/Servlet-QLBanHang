<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<h2 class="text-center text-danger fw-bold my-3">Hóa đơn khách hàng</h2>
	<form action="/bill/history" method="get" class= "row">
		<div class="mb-3 col-auto offset-8">
			<label for="exampleInputEmail1" class="form-label col-auto">Tìm kiếm:</label> 
		</div>
		<div class="mb-3 col-auto">
			<input type="text" class="form-control form-control-sm col-5" id="exampleInputEmail1" name="sdt" placeholder="Theo số điện thoại">
		</div>
		<div class="mb-1 col-auto">
			<button type="submit" class="btn btn-primary btn-sm">Tìm kiếm</button>
		</div>
	</form>
	<hr>
</div>