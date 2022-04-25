package controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.CategoryDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entities.Category;
import entities.Product;
import entities.User;
import utils.excelUtil;
import utils.fileUtil;
import utils.readExcel;

@MultipartConfig
@WebServlet({
	"/products/index",
	"/products/create",
	"/products/store",
	"/products/edit",
	"/products/update",
	"/products/delete",
	"/products/export",
	"/products/import",
})
public class ProductServlet extends HttpServlet {
	private ProductDAO pDao;
	private CategoryDAO cDao;
	HttpSession session;
	
    public ProductServlet() {
    	pDao = new ProductDAO();
    	cDao = new CategoryDAO();
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
		} else if (uriString.contains("export")) {
			export(request, response);
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
		} else if (uriString.contains("import")) {
			importEx(request, response);
		} else {
//			404
		}
	}
	
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listCategory", cDao.all());
		request.setAttribute("listProduct", pDao.all());
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("formInp", "/views/admin/product/create.jsp");
		request.setAttribute("data", "/views/admin/product/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listCategory", cDao.all());
		request.setAttribute("listProduct", pDao.all());
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("formInp", "/views/admin/product/create.jsp");
		request.setAttribute("data", "/views/admin/product/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	public void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String soLuongString = request.getParameter("soLuong").trim();
		String donGia = request.getParameter("donGia").trim();
		Category category = cDao.getCategory(categoryId);
		try {
			Product p = new Product();
			File file = fileUtil.saveFileUpload(fileUtil.createForder("product"),  request.getPart("img"));
			BeanUtils.populate(p, request.getParameterMap());
			if (p.getTen().trim().equals("") || soLuongString.trim().equals("") || donGia.equals("") || request.getPart("img").getSubmittedFileName().equals("")) {
				session.setAttribute("error", "Vui lòng nhập đủ thông tin!");
				response.sendRedirect("/SANGTM_PH17730_ASM/products/create");
				return;
			}
			p.setImg("/SANGTM_PH17730_ASM/img/product/" + file.getName());
			if (request.getPart("img").getSubmittedFileName().equals("")) {
				p.setImg("");
			}
			p.setCategory(category);
			p.setUser(user);
			pDao.create(p);
			session.setAttribute("message", "Thêm thành công!");
			response.sendRedirect("/SANGTM_PH17730_ASM/products/index");
		} catch (Exception e) {
			session.setAttribute("error", "Thêm thất bại!");
			response.sendRedirect("/SANGTM_PH17730_ASM/products/index");
			e.printStackTrace();
		}
	}
	
	public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		request.setAttribute("product", pDao.getProduct(id));
		request.setAttribute("listProduct", pDao.all());
		request.setAttribute("listCategory", cDao.all());
		request.setAttribute("head", "/views/layout/header.jsp");
		request.setAttribute("navbar", "/views/layout/navbar.jsp");
		request.setAttribute("formInp", "/views/admin/product/edit.jsp");
		request.setAttribute("data", "/views/admin/product/table.jsp");
		request.setAttribute("foot", "/views/layout/footer.jsp");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			session = request.getSession();
			User user = (User) session.getAttribute("user");
			String idString = request.getParameter("id");
			int id = Integer.parseInt(idString);
			Product p = pDao.getProduct(id);
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			Category category = cDao.getCategory(categoryId);
			try {
			Product product = new Product();
			BeanUtils.populate(product, request.getParameterMap());
			File file = fileUtil.saveFileUpload(fileUtil.createForder("product"),  request.getPart("img"));
			if(request.getPart("img").getSubmittedFileName().equals("")) {
				product.setImg(p.getImg());
			} else {
				product.setImg("/SANGTM_PH17730_ASM/img/product/" + file.getName());
			}
			product.setCategory(category);
			product.setId(p.getId());
			product.setUser(user);
			pDao.update(product);
			session.setAttribute("message", "Sửa thành công!");
			response.sendRedirect("/SANGTM_PH17730_ASM/products/index");
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("/SANGTM_PH17730_ASM/products/index");
			session.setAttribute("error", "Sửa thất bại!");
			e.printStackTrace();
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		User user = (User) session.getAttribute("user");
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		Product product = pDao.getProduct(id);
		product.setUser(user);
		pDao.delete(product);
		session.setAttribute("message", "Xóa thành công!");
		response.sendRedirect("/SANGTM_PH17730_ASM/products/index");
	}
	
	public void export(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			excelUtil.writeExcel(pDao.all(), "C:/Users/TranSang/Desktop/sanpham.xlsx");
			response.sendRedirect("/SANGTM_PH17730_ASM/products/index");
			session.setAttribute("message", "Xuất excel thành công!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute("error", "Xuất excel thất bại!");
		}
	}
	
	public void importEx(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		try {
			File file = fileUtil.saveFileUploadExcel(request.getPart("excel"));
			List<Product> products = readExcel.readExcel(file.getAbsolutePath());
			for (Product product : products) {
				product.setUser(user);
			}
			pDao.bashInsert(products);
			session.setAttribute("message", "Nhập excel thành công!");
			response.sendRedirect("/SANGTM_PH17730_ASM/products/index");
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			session.setAttribute("error", "Nhập excel thất bại!");
			response.sendRedirect("/SANGTM_PH17730_ASM/products/index");
			e.printStackTrace();
		}
	}
	
	

}
