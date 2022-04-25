<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<table class="table table-bordered text-center">
	<thead class="table-danger">
		<tr>
			<th>Tên sản phẩm</th>
			<th>Số lượng</th>
			<th>Đơn giá</th>
			<th>Ảnh</th>
			<th colspan="2">Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listProduct }" var="product">
			<tr>
				<td>${ product.ten }</td>
				<td>${ product.soLuong }</td>
				<td><fmt:formatNumber type="number" pattern="##,###₫" value="${product.donGia}" /></td>
				<td> <img height="200" alt="" src="${product.img }"> </td>
				<td><a class="btn btn-primary" href="/SANGTM_PH17730_ASM/products/edit?id=${product.id }">Sửa</a></td>
				<td><a class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal${product.id }">Xóa
				
				</a>
					<div class="modal fade" id="exampleModal${product.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					        Bạn có muốn xóa sản phẩm ${product.ten }?
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
					        <a class="btn btn-danger" href="/SANGTM_PH17730_ASM/products/delete?id=${product.id }">Xác nhận</a>
					      </div>
					    </div>
					  </div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>