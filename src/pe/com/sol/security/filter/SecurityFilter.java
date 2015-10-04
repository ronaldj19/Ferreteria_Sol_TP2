
package pe.com.sol.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pe.com.sol.portal.session.UserSessionBean;

/**
 * @author Eduardo Zuñiga
 * 
 */
public class SecurityFilter implements Filter {
	
	private static final Logger logger=Logger.getLogger(SecurityFilter.class);
	
	private List paginasWebEstatica;
		
	public SecurityFilter() {
		
		super();
		paginasWebEstatica=new ArrayList();
		paginasWebEstatica.add("/FERRETERIA-SOL/");
	}

	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	
	public void doFilter(ServletRequest request, 
						 ServletResponse response, 
						 FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest httpReq = ((HttpServletRequest) request);
		HttpServletResponse httpRes = ((HttpServletResponse) response);
		UserSessionBean userSessionBean = (UserSessionBean) httpReq.getSession().
											getAttribute("scopedTarget.userSessionBean");
		String url = httpReq.getRequestURI();
		
		if (userSessionBean == null ||
				userSessionBean.getUsuario() == null) {
			if (url.indexOf("preLogin.htm") < 0){
				if (url.indexOf("init.htm") < 0 && url.indexOf("login.htm") < 0) {
					logger.info("No se inicio la session al pedir la pagina "+url);
					httpRes.sendRedirect("/FERRETERIA-SOL/security/init.htm");
					return;
				}
			}
		}			
				
		filterChain.doFilter(request, response);
	}

	/*
	private boolean webEstatica(String url) {
		
		int size=paginasWebEstatica.size();
		String copiaUrl=new String(url);
		String pagina;
		for (int i = 0; i <size ; i++) {
			pagina=(String)paginasWebEstatica.get(i);
			if (copiaUrl.indexOf(pagina)==0){
				copiaUrl=copiaUrl.replaceAll(pagina, "");
				if (copiaUrl.indexOf("/")<0){
					return true;
				}
			}
		} 
		return false;
	}
	*/

	public void destroy() {
		
		paginasWebEstatica=null;
	}

}