<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
<h2 class="my-3">Hóa đơn khách hàng</h2>
<table class="table table-bordered text-center">
	<thead class="table-danger">
		<tr>
			<th>Mã hóa đơn</th>
			<th>Tên khách hàng</th>
			<th>Số điện thoại</th>
			<th>Ngày tạo</th>
			<th>Thành tiền</th>
			<th>Ghi chú</th>
			<th>Trạng thái</th>
			<th colspan="2">Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${listBill }" var="bill">
			<tr>
				<td>${ bill.id }</td>
				<td>${ bill.customer.hoTen }</td>
				<td>${ bill.customer.sdt }</td>
				<td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${bill.ngayTao}" /></td>
				<td class="text-danger fw-bold"><fmt:formatNumber type="number" pattern="##,###₫" value="${bill.thanhTien}" /></td>
				<td>${ bill.ghiChu }</td>
				<td class="fw-bold ${ bill.trangThai == 0 ? 'text-secondary' : bill.trangThai == -1 ? 'text-danger' : bill.trangThai == 1 ? 'text-success' : ''}">${ bill.trangThai == 0 ? 'Chờ xác nhận' : bill.trangThai == -1 ? 'Đã hủy hóa đơn' : 'Hoàn thành' }</td>
				<td><a class="btn btn-primary" href="/SANGTM_PH17730_ASM/home/bill/view?id=${bill.id }">Xem chi tiết</a></td>
				<td><a class="btn btn-danger ${bill.trangThai == -1 || bill.trangThai == 1? 'disabled' : '' }" data-bs-toggle="modal" data-bs-target="#exampleModal${bill.id }">
					Hủy hóa đơn
				</a>
					<div class="modal fade" id="exampleModal${bill.id }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body">
					        Bạn có muốn hủy hóa đơn này?
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
					        <a class="btn btn-danger" href="/SANGTM_PH17730_ASM/home/bill/cancel?id=${bill.id }">Xác nhận</a>
					      </div>
					    </div>
					  </div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>  
