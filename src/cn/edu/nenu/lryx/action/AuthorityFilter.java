package cn.edu.nenu.lryx.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**   
* @Title: AuthorityFilter.java 
* @Package cn.edu.nenu.lryx.action 
* @Description: TODO
* @author py pengyang813@foxmail.com
* @date 2016年3月18日 下午9:25:40 
* @version V1.0   
*/

public class AuthorityFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hrequest =  (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		HttpSession session = hrequest.getSession();
		Object  u = session.getAttribute("admin"); 
		if(u == null){
			hresponse.sendRedirect("../login.jsp");
		}else {
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
