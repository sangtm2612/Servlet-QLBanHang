<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<h3 class="my-3">Hóa đơn chi tiết</h3>
	<div class="row px-4 mt-3">
		<div class="col-auto me-auto">
			<p>
				<i class="fa fa-user" aria-hidden="true"></i> ${bill.customer.hoTen }
				<br> <i class="fa fa-mobile" aria-hidden="true"></i>
				${bill.customer.sdt } <br> <i class="fa fa-envelope"
					aria-hidden="true"></i> ${bill.customer.email } <br> <i
					class="fa fa-map-marker" aria-hidden="true"></i>
				${bill.customer.diaChi }
			</p>
		</div>
		<div class="col-auto">
			<p>
				Mã hóa đơn: ${bill.id } <br> Ngày tạo:
				<fmt:formatDate pattern="dd-MM-yyyy" value="${bill.ngayTao}" />
				<br>Trạng thái: <span class="fw-bold ${ bill.trangThai == 0 ? 'text-secondary' : bill.trangThai == -1 ? 'text-danger' : bill.trangThai == 1 ? 'text-success' : ''}">${ bill.trangThai == 0 ? 'Chờ xác nhận' : bill.trangThai == -1 ? 'Đã hủy hóa đơn' : 'Hoàn thành' }</span>
			</p>
		</div>
	</div>
	<div>
		<table class="table text-center table-striped">
			<thead class="">
				<tr>
					<th></th>
					<th>Sản phẩm</th>
					<th>Giá</th>
					<th>Số lượng</th>
					<th>Tổng tiền</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bill.orderdetails }" var="oDetail">
					<tr>
						<td><img height="75" alt="" src="${oDetail.product.img }">
						</td>
						<td>${ oDetail.product.ten }</td>
						<td><fmt:formatNumber type="number" pattern="##,###₫"
								value="${oDetail.donGia}" /></td>
						<td>${oDetail.soLuong }</td>
						<td><fmt:formatNumber type="number" pattern="##,###₫"
								value="${oDetail.donGia * oDetail.soLuong}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<p class="mt-2 offset-9" style="font-size: 22px">Thanh toán: <span class="text-danger fw-bold" style="font-size: 28px"><fmt:formatNumber type="number" pattern="##,###₫" value="${bill.thanhTien}" /></span> </p>
	</div>
</div>