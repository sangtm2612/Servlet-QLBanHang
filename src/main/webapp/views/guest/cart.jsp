<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<c:if test="${!empty sessionScope.error }">
		<div class="alert alert-danger mt-3">
			${ sessionScope.error }
			<c:remove var="error" scope="session" />
		</div>
	</c:if>
	<h2 class="text-center mt-3 fw-bold">GIỎ HÀNG</h2>
	<a class="btn btn-primary offset-10 mb-4"
		href="/SANGTM_PH17730_ASM/home/card/clear">Làm mới giỏ hàng</a>
	<table class="table text-center">
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
			<c:forEach items="${sessionScope.listOrderDetail }" var="oDetail">
				<tr>
					<td><img height="100" alt="" src="${oDetail.product.img }">
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
	<hr>
	<div class="row">
		<form class="row mt-2" action="/SANGTM_PH17730_ASM/home/order"
			method="post">
			<div class="col-5">
				<h3>Thông tin khách hàng</h3>
				<div class="mb-3">
					<label for="fullname" class="form-label">Họ tên</label> <input
						type="text" class="form-control" id="fullname" name="hoTen">
				</div>
				<div class="mb-3">
					<label for="sdt" class="form-label">Số điện thoại</label> <input
						type="text" class="form-control" id="sdt" name="sdt">
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Email:</label> <input
						type="email" class="form-control" id="email" name="email">
				</div>
				<div class="mb-3">
					<label for="dc" class="form-label">Địa chỉ:</label> <input
						type="text" class="form-control" id="dc" name="diaChi">
				</div>
			</div>
			<div class="col-6 ">
				<div class="mb-3 offset-3">
					<h3>Thông tin hóa đơn</h3>
					<label for="hi" class="form-label" name="note">Ghi chú:</label>
					<textarea id="hi" class="form-control" rows="4"></textarea>
				</div>
				<div class="mb-3 offset-3">
					<h4 class="col-auto">
						Thành tiền: <span class="text-danger fw-bold"><fmt:formatNumber
								type="number" pattern="##,###₫" value="${total}" /></span>
					</h4>
					<button type="button" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#exampleModal">Đặt
						hàng</button>
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">Xác nhận đặt hàng?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Hủy</button>
									<button type="submit" class="btn btn-primary">Xác nhận</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>