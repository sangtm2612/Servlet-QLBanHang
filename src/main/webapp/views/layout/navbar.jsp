<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid px-2">
	<a class="navbar-brand " href="#pages/home"> <i
		class="bi bi-mortarboard-fill"></i> FPT Polytechnic
	</a>
	<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
		data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNavDropdown">
		<ul class=" navbar-nav me-auto">
		<c:if test="${sessionScope.user.isAdmin != 1 }">
			<li class="nav-item collapse navbar-collapse" id="navbarNavDropdown">
				<span class="dropdown"> <a
					class="nav-link dropdown-toggle nav-link ps-0" ng-model="taikhoan"
					id="navbarDropdownMenuLink" h ref="#" role="button"
					data-bs-toggle="dropdown" aria-expanded="false"> Danh mục </a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<c:forEach items="${listCategory }" var="cate">
							<li><a class="dropdown-item"
								href="/SANGTM_PH17730_ASM/home/filter?categoryId=${cate.id }">${cate.ten }</a></li>
						</c:forEach>
					</ul>
			</span>
			</li>
			
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="/SANGTM_PH17730_ASM/home/product">Sản phẩm</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/SANGTM_PH17730_ASM/home/cart">Giỏ hàng</a></li>
				<li class="nav-item"><a class="nav-link" href=""
					data-bs-toggle="modal" data-bs-target="#exampleModal3">Lịch sử</a></li>
			</c:if>


			<c:if test="${sessionScope.user.isAdmin == 1 }">
				<li class="nav-item"><a class="nav-link"
					href="/SANGTM_PH17730_ASM/categories/index">Quản lý danh mục</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/SANGTM_PH17730_ASM/products/index">Quản lý sản phẩm</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/SANGTM_PH17730_ASM/users/index">Quản lý tài khoản</a></li>
					<li class="nav-item"><a class="nav-link"
					href="/SANGTM_PH17730_ASM/bill/index">Quản lý hóa đơn</a></li>
			</c:if>
		</ul>
		<span class="nav-item dropdown"> <a
			class="nav-link dropdown-toggle text-white ps-0" ng-model="taikhoan"
			id="navbarDropdownMenuLink" href="#" role="button"
			data-bs-toggle="dropdown" aria-expanded="false"> ${!empty sessionScope.user ? sessionScope.user.sdt : "Tài khoản"}
		</a>
			<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
				<c:if test="${empty sessionScope.user }">
					<li><a class="dropdown-item" href="/SANGTM_PH17730_ASM/login">Đăng
							nhập</a></li>
				</c:if>
				<c:if test="${!empty sessionScope.user }">
					<li><a class="dropdown-item" ng-click="logout()" href="">Đổi
							mật khẩu</a></li>
					<li><a class="dropdown-item" href="/SANGTM_PH17730_ASM/login">Đăng
							xuất</a></li>
				</c:if>

			</ul>
		</span>
	</div>
	<!-- Modal -->
			<div class="modal fade" id="exampleModal3" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<form action="/SANGTM_PH17730_ASM//home/bill/history" method="get">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div class="mb-3">
							<label for="sdt" class="form-label">Số điện thoại:</label> 
							<input type="text" class="form-control" placeholder="Nhập số điện thoại của bạn để tìm kiếm hóa đơn" id="sdt" name="sdt">
						</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Hủy</button>
							<button type="submit" class="btn btn-primary">Xác nhận</button>
						</div>
					</div>
					</form>
				</div>
			</div>
</div>