package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import dao.CategoryDAO;
import entities.Category;
import entities.User;


@WebServlet({
	"/categories/index",
	"/categories/create",
	"/categories/store",
	"/categories/edit",
	"/categories/update",
	"/categories/delete",
})
public class CategoryServlet extends HttpServlet {
	private CategoryDAO cDao;
	private HttpSession session;


    public CategoryServlet() {
        cDao = new CategoryDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
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
		response.setCharacterEncoding("UTF-8");
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
		request.setAttribute("formInp", "/views/admin/category/create.jsp");
		request.setAttribute("data", "/views/admin/category/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.setAttribute("listCategory", cDao.all());
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("formInp", "/views/admin/category/create.jsp");
		request.setAttribute("data", "/views/admin/category/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.setAttribute("listCategory", cDao.all());
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		try {
			Category c = new Category();
			BeanUtils.populate(c, request.getParameterMap());
			if (c.getTen().trim().equals("")) {
				session.setAttribute("error", "Vui lòng nhập đủ thông tin!");
				response.sendRedirect("/SANGTM_PH17730_ASM/categories/create");
				return;
			}
			cDao.create(c);
			session.setAttribute("message", "Thêm thành công!");
			response.sendRedirect("/SANGTM_PH17730_ASM/categories/index");
		} catch (Exception e) {
			session.setAttribute("error", "Thêm thất bại");
			response.sendRedirect("/SANGTM_PH17730_ASM/categories/create");
			e.printStackTrace();
		}
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		request.setAttribute("cate", cDao.getCategory(id));
		request.setAttribute("listCategory", cDao.all());
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("formInp", "/views/admin/category/edit.jsp");
		request.setAttribute("data", "/views/admin/category/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			session = request.getSession();
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			Category u = cDao.getCategory(id);
			try {
			Category c = new Category();
			BeanUtils.populate(c, request.getParameterMap());
			cDao.update(c);
			session.setAttribute("message", "Sửa thành công!");
			response.sendRedirect("/SANGTM_PH17730_ASM/categories/index");
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			session.setAttribute("error", "Sửa thất bại!");
			e.printStackTrace();
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		Category c = cDao.getCategory(id);
		if (c.getProducts().size() == 0) {
			cDao.delete(c);
			session.setAttribute("message", "Xóa thành công!");
			response.sendRedirect("/SANGTM_PH17730_ASM/categories/index");
		} else {
			session.setAttribute("error", "Danh mục có sản phẩm, không được phép xóa!");
			response.sendRedirect("/SANGTM_PH17730_ASM/categories/index");
		}
	}

}
