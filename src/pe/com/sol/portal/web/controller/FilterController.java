
package pe.com.sol.portal.web.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import pe.com.sol.portal.session.UserSessionBean;
import pe.com.sol.portal.web.bean.Producto;
import pe.com.sol.portal.web.service.ProductoService;

/**
 * @author Alonso Gutierrez
 * 
 */
public class FilterController extends MultiActionController {

	private static Logger logger = Logger.getLogger(FilterController.class);
	
	UserSessionBean userSessionBean;
	ProductoService productoService;
	
	public ModelAndView search(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		ModelAndView modelMap = new ModelAndView("jsonView");

		String query = request.getParameter("query");
		logger.debug("Query: " + query);
		modelMap.addObject("query", query);	
				
		List<Producto> lstProducto = productoService.getFilterProducto(query);
		
		logger.debug("==============================================================");
		logger.debug("Productos: " + lstProducto.size());
		
		logger.debug("==============================================================");
		Object suggestions[] = new Object[lstProducto.size()];
		Object data[] = new Object[lstProducto.size()];
		int index = 0;
		for (Iterator iterator = lstProducto.iterator(); iterator.hasNext();) {
			Producto producto = (Producto) iterator.next();
			
			suggestions[index] = producto.getNombre();
			data[index] = producto.getId() + "," + producto.getNombre() + "," + 
									producto.getMarca().getNombre() + "," + 
									producto.getPrecioVenta() + "," + producto.getCodigo() +
									"," + producto.getStock();
			index++;
		}
		
		modelMap.addObject("suggestions", suggestions );
		modelMap.addObject("data", data );
						
		return modelMap;
				
	}
	
	/**
	 * @param userSessionBean the userSessionBean to set
	 */
	public void setUserSessionBean(UserSessionBean userSessionBean) {
		this.userSessionBean = userSessionBean;
	}
	
	/**
	 * @param productoService the productoService to set
	 */
	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}
	
}
