<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-bordered text-center">
	<thead class="table-danger">
		<tr>
			<th>Họ tên</th>
			<th>Giới tính</th>
			<th>Số điện thoại</th>
			<th>Email</th>
			<th colspan="2">Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listUser }" var="user">
			<tr>
				<td>${user.hoTen }</td>
				<td>${user.gioiTinh == 1 ? "Nam" : "Nữ" }</td>
				<td>${user.sdt }</td>
				<td>${user.email }</td>
				<td><a class="btn btn-primary" href="/SANGTM_PH17730_ASM/users/edit?id=${user.id }">Sửa</a></td>
				<td><a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModalUser${user.id }" >Xóa</a>
					<div class="modal fade" id="exampleModalUser${user.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					        Bạn có muốn xóa tài khoản này?
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
					        <a class="btn btn-danger" href="/SANGTM_PH17730_ASM/users/delete?id=${user.id }">Xác nhận</a>
					      </div>
					    </div>
					  </div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>