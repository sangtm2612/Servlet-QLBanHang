<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table table-bordered text-center">
	<thead class="table-danger">
		<tr>
			<th>Tên danh mục</th>
			<th colspan="2">Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listCategory }" var="cate">
			<tr>
				<td>${cate.ten }</td>
				<td><a class="btn btn-primary" href="/SANGTM_PH17730_ASM/categories/edit?id=${cate.id }">Sửa</a></td>
				<td><a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${cate.id }">Xóa</a></td>
				<div class="modal fade" id="exampleModal${cate.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
				        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				      </div>
				      <div class="modal-body">
				        Bạn có muốn xóa danh mục này?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
				        <a class="btn btn-danger"  href="/SANGTM_PH17730_ASM/categories/delete?id=${cate.id }">Xác nhận</a>
				      </div>
				    </div>
				  </div>
				</div>
			</tr>
		</c:forEach>
	</tbody>
</table>