package controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import dao.UserDAO;
import entities.User;
import utils.EncryptUtil;
import utils.ValidateUtil;
import utils.fileUtil;

@MultipartConfig
@WebServlet({
	"/users/index",
	"/users/create",
	"/users/store",
	"/users/edit",
	"/users/update",
	"/users/delete",
})
public class UserServlet extends HttpServlet {
	private UserDAO uDao;
	HttpSession session;
	
    public UserServlet() {
    	uDao = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String uriString = request.getRequestURI();
		if (uriString.contains("index")) {
			index(request, response);
		} else if (uriString.contains("create")) {
			create(request, response);
		} else if (uriString.contains("edit")) {
			edit(request, response);
		} else if (uriString.contains("delete")) {
			delete(request, response);
		} else {
//			404
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String uriString = request.getRequestURI();
		if (uriString.contains("store")) {
			store(request, response);
		} else if (uriString.contains("update")) {
			update(request, response);
		} else {
//			404
		}
	}
	
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("formInp", "/views/admin/user/create.jsp");
		request.setAttribute("data", "/views/admin/user/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.setAttribute("listUser", uDao.all());
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("formInp", "/views/admin/user/create.jsp");
		request.setAttribute("data", "/views/admin/user/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.setAttribute("listUser", uDao.all());
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		List<User> listU = uDao.all();
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			if (user.getHoTen().trim().equals("") || user.getDiaChi().trim().equals("") || user.getEmail().trim().equals("") || user.getSdt().trim().equals("") || user.getPassword().trim().equals("")) {
				session.setAttribute("error", "Vui lòng nhập đủ thông tin!");
				response.sendRedirect("/SANGTM_PH17730_ASM/users/create");
				return;
			}
			for (User u : listU) {
				if (user.getSdt().equals(u.getSdt())) {
					request.setAttribute("sdt", "Số điện thoại đã tồn tại!");
					response.sendRedirect("/SANGTM_PH17730_ASM/users/create");
					return;
				}
			}
			for (User u : listU) {
				if (user.getEmail().equals(u.getEmail())) {
					session.setAttribute("email", "Email đã tồn tại!");
					response.sendRedirect("/SANGTM_PH17730_ASM/users/create");
					return;
				}
			}
			if (!ValidateUtil.validate("(84|0[3|5|7|8|9])+([0-9]{8})\\b", user.getSdt())) {
				request.setAttribute("sdt", "Số điện thoại không hợp lệ!");
				response.sendRedirect("/SANGTM_PH17730_ASM/users/create");
				return;
			}
//			if (Pattern.matches("", user.getSdt())) {
//				request.setAttribute("email", "Email không hợp lệ!");
//				response.sendRedirect("/SANGTM_PH17730_ASM/users/create");
//				return;
//			}
			user.setIsAdmin(0);
			String passString = request.getParameter("password");
			user.setPassword(EncryptUtil.encrypt(passString));
			uDao.create(user);
			session.setAttribute("message", "Thêm thành công!");
			response.sendRedirect("/SANGTM_PH17730_ASM/users/index");
		} catch (Exception e) {
			session.setAttribute("error", "Thêm thất bại");
			response.sendRedirect("/SANGTM_PH17730_ASM/users/create");
			e.printStackTrace();
		}
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		request.setAttribute("user", uDao.getUser(id));
		request.setAttribute("listUser", uDao.all());
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("formInp", "/views/admin/user/edit.jsp");
		request.setAttribute("data", "/views/admin/user/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			session = request.getSession();
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			User u = uDao.getUser(id);
			try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			user.setPassword(u.getPassword());
			uDao.update(user);
			session.setAttribute("message", "Sửa thành công!");
			response.sendRedirect("/SANGTM_PH17730_ASM/users/index");
			} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			session.setAttribute("error", "Sửa thất bại!");
			response.sendRedirect("/SANGTM_PH17730_ASM/users/index");
			e.printStackTrace();
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		User user = uDao.getUser(id);
		uDao.delete(user); 
		session.setAttribute("message", "Xóa thành công!");
		response.sendRedirect("/SANGTM_PH17730_ASM/users/index");
	}

}
