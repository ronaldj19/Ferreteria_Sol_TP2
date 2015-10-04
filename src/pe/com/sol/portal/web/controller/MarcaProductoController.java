
package pe.com.sol.portal.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import pe.com.sol.constantes.Constantes;
import pe.com.sol.portal.session.UserSessionBean;
import pe.com.sol.portal.web.bean.Producto;
import pe.com.sol.portal.web.form.ProductoForm;
import pe.com.sol.portal.web.service.ProductoService;

/**
 * @author Alonso Gutierrez
 * 
 */
public class MarcaProductoController extends MultiActionController{
	
	private static Logger logger = Logger.getLogger(MarcaProductoController.class);
	
	private static final String UPDATE_PRODUCTO = "update_marca";
	
	private static final String TITULO_NEW_PRODUCTO = "AGREGAR UNA NUEVA MARCA";
	
	private static final String TITULO_EDIT_PRODUCTO = "ACTUALIZAR UNA MARCA";
	
	UserSessionBean userSessionBean;
	ProductoService productoService; 
		
	public ModelAndView iniciar(HttpServletRequest request, 
			HttpServletResponse response) throws Exception
	{
		logger.debug("=================Producto Init===============");
		
		ModelAndView model = new ModelAndView(Constantes.JSP_INIT_PRODUCTO);
		ProductoForm productoForm = new ProductoForm();
		List<Producto> lstProducto = new ArrayList<Producto>();
		Producto producto = (Producto)request.getAttribute("productoBean");
		String tipoBusqueda = "";
		try {
			
			productoForm.setTipoBusqueda(Constantes.BUSQUEDA_X_CODIGO);
			
			if(producto != null)
			{
				tipoBusqueda = (String)request.getAttribute("tipoBusqueda");
			}
			
			lstProducto = productoService.getProducto(producto, tipoBusqueda, 20);
			
			if (CollectionUtils.isEmpty(lstProducto))
			{
				model.addObject(Constantes.MENSAJE_GENERAL, "(!) No existe registros para esta consulta. ");
			}
			
			model.addObject("lstProducto", lstProducto);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}
		
		model.addObject("productoForm", productoForm);
		
		return model;
	}
	
	public ModelAndView actualizarProducto(HttpServletRequest request, 
			HttpServletResponse response, ProductoForm productoForm) throws Exception
	{
		logger.debug("=================Producto Init===============");
		
		ModelAndView model = new ModelAndView(Constantes.JSP_UPDATE_PRODUCTO);
		String parametro = request.getParameter(Constantes.PARAMETER);
		String idProd = request.getParameter(Constantes.PARAMETER_ID);
		
		try {
			logger.debug("Parametro: " + parametro);
			logger.debug("IdProducto: " + idProd);
			
			model.addObject(UPDATE_PRODUCTO, parametro);
			
			if(StringUtils.equals(parametro, Constantes.PARAMETER_NEW))
			{
				model.addObject(Constantes.TITULO, TITULO_NEW_PRODUCTO);
				productoForm = new ProductoForm();
				
			}else if(StringUtils.equals(parametro, Constantes.PARAMETER_EDIT))
			{
				model.addObject(Constantes.TITULO, TITULO_EDIT_PRODUCTO);
								
				productoForm = getProductoForm(productoService.getProducto(Integer.parseInt(idProd)));
				userSessionBean.put("foto_bd", productoForm.getFile());
				
			}else if(StringUtils.equals(parametro, Constantes.PARAMETER_DELETE))
			{
				productoService.deleteProducto(Integer.parseInt(idProd));
				logger.info("(!) Se eliminó con éxito.");
				return iniciar(request, response);
			}else
			{
				/**Busqueda de productos**/
				if (!StringUtils.isEmpty(parametro))
				{
					Producto producto = new Producto();
															
					if (StringUtils.equals(productoForm.getTipoBusqueda(), 
											Constantes.BUSQUEDA_X_CODIGO))
					{
						producto.setCodigo(productoForm.getCodigo().trim().toLowerCase());
					}else
					{
						producto.setNombre(productoForm.getNombre().trim().toLowerCase());
					}
					
					request.setAttribute("productoBean", producto);
					request.setAttribute("tipoBusqueda", productoForm.getTipoBusqueda());
					
					return iniciar(request, response);
				}
			}		
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}
		
		model.addObject("productoForm", productoForm);
		
		return model;
	}
	
	public ModelAndView saveMarca(HttpServletRequest request, 
			HttpServletResponse response, ProductoForm productoForm) throws Exception
	{
		logger.debug("=================Marca SAVE===============");
		ModelAndView model = new ModelAndView(Constantes.JSP_UPDATE_PRODUCTO);
		String parametro = request.getParameter(Constantes.PARAMETER);
						
		try {
			logger.info("Parametro: " + parametro);						
				
					
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
			model.addObject(Constantes.PARAMETER, parametro);
			model.addObject("productoForm", productoForm);
			model.addObject(Constantes.MENSAJE_GENERAL, "(X) Ocurrio un error al guardar el registro.");
			
		}
		
		model.addObject("productoForm", productoForm);
		
		return model;
	}
	
	public ModelAndView saveMarcaProducto(HttpServletRequest request, 
			HttpServletResponse response, ProductoForm productoForm) throws Exception
	{
		logger.debug("=================Persona Init UPLOAD===============");
		ModelAndView model = new ModelAndView(Constantes.JSP_UPDATE_PRODUCTO);
		String parametro = request.getParameter(Constantes.PARAMETER);
						
		try {
			logger.info("Parametro: " + parametro);						
				
					
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
			model.addObject(Constantes.PARAMETER, parametro);
			model.addObject("productoForm", productoForm);
			model.addObject(Constantes.MENSAJE_GENERAL, "(X) Ocurrio un error al guardar el registro.");
			
		}
		
		model.addObject("productoForm", productoForm);
		
		return model;
	}
	
	private ProductoForm getProductoForm(Producto bean)
	{
		ProductoForm form = new ProductoForm();
		
		form.setId(bean.getId()+"");
		form.setCodigo(bean.getCodigo());
		form.setNombre(bean.getNombre());
		form.setDescripcion(bean.getDescripcion());
		form.setPrecioVenta(bean.getPrecioVenta()+"");
		form.setPrecioCompra(bean.getPrecioCompra()+"");
		form.setStock(bean.getStock()+"");
		form.setIdMarca(bean.getMarca().getId()+"");
				
		return form;
	}
	
	private Producto getProductoBean(ProductoForm form, String parametro)
	{
		Producto producto = new Producto();
		
		if (StringUtils.equals(parametro, Constantes.PARAMETER_NEW))
		{
			producto.setId(0);
			producto.setCodigo(form.getCodigo());
		}else
		{
			producto.setId(Integer.parseInt(form.getId()));
		}
		
		producto.setNombre(form.getNombre());
		producto.setDescripcion(form.getDescripcion());
		producto.setPrecioVenta(Double.parseDouble(form.getPrecioVenta().trim()));
		producto.setPrecioCompra(Double.parseDouble(form.getPrecioCompra().trim()));
		producto.setStock(Integer.parseInt(form.getStock().trim()));
		producto.setFile(form.getFile());
		
		return producto;
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
