package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;

@WebFilter( filterName = "filter2", urlPatterns = {
	"/users/*",
	"/products/*",
	"/categories/*",
	"/bill/*",
})
public class IsAdminFilter extends HttpFilter implements Filter {
       
    public IsAdminFilter() {
    }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		httpRequest.setCharacterEncoding("UTF-8");
		httpResponse.setCharacterEncoding("UTF-8");
		User user = (User) session.getAttribute("user");
		if (user.getIsAdmin() == 0) {
			httpResponse.sendRedirect("/SANGTM_PH17730_ASM/home/product");	
			return;
		}
		chain.doFilter(request, response);
		httpRequest.setCharacterEncoding("UTF-8");
		httpResponse.setCharacterEncoding("UTF-8");
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
