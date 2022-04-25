package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategoryDAO;
import dao.CustomerDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entities.Order;
import entities.Orderdetail;
import entities.Product;

/**
 * Servlet implementation class BillServlet
 */
@WebServlet({"/bill/index", "/bill/search", "/bill/cancel", "/bill/confirm" })
public class BillServlet extends HttpServlet {

	private ProductDAO pDao;
	private CategoryDAO cDao;
	private CustomerDAO customerDAO;
	private OrderDAO orderDAO;
	private OrderDetailDAO orderDetailDAO;
	private List<Product> listP;
	private List<Orderdetail> listOrderdetails;
	private UserDAO uDao;
	
	
	
    public BillServlet() {
        pDao = new ProductDAO();
        cDao = new CategoryDAO();
        orderDAO = new OrderDAO();
        orderDetailDAO = new OrderDetailDAO();
        customerDAO = new CustomerDAO();
        listP = new ArrayList<Product>();
        listOrderdetails = new ArrayList<Orderdetail>();
        uDao = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String uriString = request.getRequestURI();
		if (uriString.contains("index")) {
			index(request, response);
		} else if (uriString.contains("search")) {
			search(request, response);
		} else if (uriString.contains("cancel")) {
			cancelBill(request, response);
		} else if (uriString.contains("confirm")) {
			confirm(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
	public void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("formInp", "/views/admin/bill/bill.jsp");
		request.setAttribute("data", "/views/admin/bill/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.setAttribute("listBill", orderDAO.all());
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	public void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String maString = request.getParameter("ma");
		int id = Integer.parseInt(maString);
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("formInp", "/views/admin/bill/bill.jsp");
		request.setAttribute("data", "/views/admin/bill/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.setAttribute("listBill", orderDAO.all());
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	public void cancelBill(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String maString = request.getParameter("id");
		int id = Integer.parseInt(maString);
		Order order = orderDAO.getOrder(id);
		order.setTrangThai(-1);
		orderDAO.update(order);
		session.setAttribute("message", "Hủy hóa đơn thành công!");
		response.sendRedirect("/SANGTM_PH17730_ASM/bill/index");
	}
	
	public void confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listBill", orderDAO.all());
		HttpSession session = request.getSession();
		String maString = request.getParameter("id");
		int id = Integer.parseInt(maString);
		Order order = orderDAO.getOrder(id);
		order.setTrangThai(1);
		orderDAO.update(order);
		session.setAttribute("message", "Xác nhận thành công!");
		response.sendRedirect("/SANGTM_PH17730_ASM/bill/index");
	}

}
