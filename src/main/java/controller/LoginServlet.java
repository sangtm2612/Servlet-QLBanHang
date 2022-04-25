package controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.compiler.NewlineReductionServletWriter;

import dao.UserDAO;
import entities.User;
import utils.EncryptUtil;
import utils.ValidateUtil;

@WebServlet({"/login", "/register"})
public class LoginServlet extends HttpServlet {
	
    private UserDAO uDao;   
	
    public LoginServlet() {
    	uDao = new UserDAO();
//    		User user = new User();
//    		user.setSdt("0988615185");
//    		user.setIsAdmin(1);
//    		user.setPassword(EncryptUtil.encrypt("1"));
//    		uDao.create(user);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		request.getRequestDispatcher("/views/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		List<User> listU = uDao.all();
		User user = null;
		String sdtString = request.getParameter("sdt").trim();
		String passString = request.getParameter("pass").trim();
		if(sdtString.equals("") || passString.equals("")) {
			session.setAttribute("error", "Không được để trống số điện thoại và mật khẩu!");
			response.sendRedirect("/SANGTM_PH17730_ASM/users/index");
			return;
		}
		if (!ValidateUtil.validate("(84|0[3|5|7|8|9])+([0-9]{8})\\b", sdtString)) {
			session.setAttribute("error", "Số điện thoại không hợp lệ!");
			response.sendRedirect("/SANGTM_PH17730_ASM/users/index");
			return;
		}
//		if (!ValidateUtil.validate("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", passString)) {
//			session.setAttribute("error", "Mật khẩu không hợp lệ!");
//			response.sendRedirect("/SANGTM_PH17730_ASM/users/index");
//			return;
//		}
		for (User u : listU) {
			if (u.getSdt().equals(sdtString)) {
				user = uDao.findBySDT(sdtString);
			}
		}
		if (user == null) {
			session.setAttribute("error", "Tài khoản không tồn tại!");
			response.sendRedirect("/SANGTM_PH17730_ASM/users/index");
			return;
		}else {
			try {
				if (EncryptUtil.check(passString, user.getPassword())) {
					session.setAttribute("user", user);
					response.sendRedirect("/SANGTM_PH17730_ASM/products/index");
					return;
				} else {
					session.setAttribute("error", "Sai mật khẩu!");
					response.sendRedirect("/SANGTM_PH17730_ASM/users/index");
					return;
				}
			} catch (Exception e) {
				session.setAttribute("error", "Đăng nhập thất bại");
				response.sendRedirect("/SANGTM_PH17730_ASM/users/index");
				e.printStackTrace();
			}
		}
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
