package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import dao.CategoryDAO;
import dao.CustomerDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entities.Customer;
import entities.Order;
import entities.Orderdetail;
import entities.Product;
import utils.ValidateUtil;

@WebServlet({"/home/product", "/home/filter", "/home/cart", "/home/card/clear", "/home/buy", "/home/order", "/home/bill/history", "/home/bill/view", "/home/bill/cancel"})
public class GuestServlet extends HttpServlet {

	private ProductDAO pDao;
	private CategoryDAO cDao;
	private CustomerDAO customerDAO;
	private OrderDAO orderDAO;
	private OrderDetailDAO orderDetailDAO;
	private List<Product> listP;
	private List<Orderdetail> listOrderdetails;
	private UserDAO uDao;
	
	final static Logger logger = Logger.getLogger(GuestServlet.class);
	
	
    public GuestServlet() {
        super();
        pDao = new ProductDAO();
        cDao = new CategoryDAO();
        orderDAO = new OrderDAO();
        orderDetailDAO = new OrderDetailDAO();
        customerDAO = new CustomerDAO();
        listP = new ArrayList<Product>();
        listOrderdetails = new ArrayList<Orderdetail>();
        uDao = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String uriString = request.getRequestURI();
		if (uriString.contains("product")) {
			product(request, response);
		} else if (uriString.contains("filter")) {
			filter(request, response);
		}  else if (uriString.contains("cart")) {
			cart(request, response);
		}  else if (uriString.contains("history")) {
			history(request, response);
		}  else if (uriString.contains("view")) {
			view(request, response);
		} else if (uriString.contains("cancel")) {
			cancel(request, response);
		} else if (uriString.contains("clear")) {
			clear(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String uriString = request.getRequestURI();
		if (uriString.contains("buy")) {
			buy(request, response);
		} else if (uriString.contains("order")) {
			order(request, response);
		}
	}

	public void product(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("contentt", "/views/guest/home.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.setAttribute("listCategory", cDao.all());
		request.setAttribute("listProduct", pDao.all());
		request.setAttribute("danhMuc", "Sản phẩm");
		request.getRequestDispatcher("/views/guest/layout.jsp").forward(request, response);
		
	}
	
	public void filter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("categoryId"));
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("contentt", "/views/guest/home.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.setAttribute("danhMuc", cDao.getCategory(id).getTen());
		request.setAttribute("listCategory", cDao.all());
		request.setAttribute("listProduct", pDao.allProductsByIdCategory(id));
		request.getRequestDispatcher("/views/guest/layout.jsp").forward(request, response);
	}
	
	public void cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("contentt", "/views/guest/cart.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		session.setAttribute("listOrderDetail", listOrderdetails);
		session.setAttribute("total", getThanhToan());
		request.getRequestDispatcher("/views/guest/layout.jsp").forward(request, response);
	}
	
	public void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Orderdetail orderdetail = new Orderdetail();
		int id = Integer.parseInt(request.getParameter("id"));
		Product product = pDao.getProduct(id);
		int soLuong = Integer.parseInt(request.getParameter("soluong"));
		int donGia = Integer.parseInt(request.getParameter("donGia"));
		if (product.getSoLuong() < soLuong) {
			session.setAttribute("error", product.getTen() + " chỉ còn " + product.getSoLuong() + " sản phẩm");
			response.sendRedirect("/SANGTM_PH17730_ASM/home/product");
			return;
		}
		orderdetail.setDonGia(donGia);
		if (listOrderdetails.size() != 0) {
			for (Orderdetail o : listOrderdetails) {
				if (o.getProduct().getId() == product.getId()) {
					o.setSoLuong(o.getSoLuong() + soLuong);
					session.setAttribute("listOrderDetail", listOrderdetails);
					session.setAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công");
					response.sendRedirect("/SANGTM_PH17730_ASM/home/product");
					return;
				}
			}
		}
		orderdetail.setSoLuong(soLuong);
		orderdetail.setProduct(product);
		listOrderdetails.add(orderdetail);	
		session.setAttribute("listOrderDetail", listOrderdetails);
		session.setAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công");
		response.sendRedirect("/SANGTM_PH17730_ASM/home/product");
	}
	
	public void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (listOrderdetails.size() == 0) {
			session.setAttribute("error", "Vui lòng thêm sản phẩm vào giỏ hàng!");
			response.sendRedirect("/SANGTM_PH17730_ASM/home/product");
			return;
		}
		Customer customer = new Customer();
		try {
			BeanUtils.populate(customer, request.getParameterMap());
			if(customer.getDiaChi().trim().equals("") && customer.getEmail().trim().equals("") && customer.getHoTen().trim().equals("") && customer.getSdt().trim().equals("")) {
				session.setAttribute("error", "Vui lòng nhập đủ thông tin!");
				response.sendRedirect("/SANGTM_PH17730_ASM/home/cart");
				return;
			}
			if (!ValidateUtil.validate("(84|0[3|5|7|8|9])+([0-9]{8})\\b", customer.getSdt())) {
				session.setAttribute("error", "Số điện thoại không hợp lệ!");
				response.sendRedirect("/SANGTM_PH17730_ASM/home/cart");
				return;
			}
			if (!ValidateUtil.validate("^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$", customer.getEmail())) {
				session.setAttribute("error", "Email không hợp lệ!");
				response.sendRedirect("/SANGTM_PH17730_ASM/home/cart");
				return;
			}
			Customer c = customerDAO.create(customer);
			Order order = new Order();
			order.setNgayTao(new Date());
			order.setThanhTien(getThanhToan());
			order.setTrangThai(0);
			order.setGhiChu("a");
			order.setOrderdetails(listOrderdetails);
			order.setCustomer(c);
			Order order2 = orderDAO.create(order);
			for (Orderdetail o : listOrderdetails) {
				o.setOrder(order2);
				Orderdetail odOrderdetail = orderDetailDAO.create(o);
			}
//			session.setAttribute("message", "Thêm sản phẩm vào giỏ hàng thành công");
			response.sendRedirect("/SANGTM_PH17730_ASM/home/bill/view?id=" + order.getId());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void history(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sdtString = request.getParameter("sdt");
		HttpSession session = request.getSession();
		session.setAttribute("sdtCus", sdtString);
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("contentt", "/views/guest/history/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.setAttribute("listBill", orderDAO.selectOrderBySdt(sdtString));
		request.getRequestDispatcher("/views/guest/layout.jsp").forward(request, response);
	}
	
	public void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		Order order = orderDAO.getOrder(id);
		request.setAttribute("bill", order);
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("contentt", "/views/guest/history/orderDetail.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.getRequestDispatcher("/views/guest/layout.jsp").forward(request, response);
	}
	
	public void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idString = request.getParameter("id");
		Order order = orderDAO.getOrder(Integer.parseInt(idString));
		order.setTrangThai(-1);
		orderDAO.update(order);
		response.sendRedirect("/SANGTM_PH17730_ASM//home/bill/history?sdt=" + session.getAttribute("sdtCus"));
	}
	
	public void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		listOrderdetails = new ArrayList<Orderdetail>();
		session.setAttribute("listOrderDetail", listOrderdetails);
		session.setAttribute("total", 0);
		response.sendRedirect("/SANGTM_PH17730_ASM/home/cart");
	}
	
	public int getThanhToan() {
		int total = 0;
		for (Orderdetail o : listOrderdetails) {
			total += (o.getDonGia() * o.getSoLuong());
		}
		return total;
	}

}
