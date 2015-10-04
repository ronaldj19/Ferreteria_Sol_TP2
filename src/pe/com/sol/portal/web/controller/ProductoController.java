
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
import pe.com.sol.portal.web.bean.Marca;
import pe.com.sol.portal.web.bean.Parametro;
import pe.com.sol.portal.web.bean.Producto;
import pe.com.sol.portal.web.form.ProductoForm;
import pe.com.sol.portal.web.service.MarcaProdService;
import pe.com.sol.portal.web.service.ProductoService;
import pe.com.sol.utils.PropertiesUtils;

/**
 * @author Alonso Gutierrez
 * 
 */
public class ProductoController extends MultiActionController{
	
	private static Logger logger = Logger.getLogger(ProductoController.class);
	
	private static final String UPDATE_PRODUCTO = "update_producto";
	
	private static final String TITULO_NEW_PRODUCTO = "AGREGAR UN NUEVO PRODUCTO";
	
	private static final String TITULO_EDIT_PRODUCTO = "ACTUALIZAR UN PRODUCTO";
	
	UserSessionBean userSessionBean;
	ProductoService productoService;
	MarcaProdService marcaService;
	
	PropertiesUtils properties = new PropertiesUtils();
		
	public ModelAndView iniciar(HttpServletRequest request, 
			HttpServletResponse response) throws Exception
	{
		logger.debug("=================Producto Init===============");
		
		ModelAndView model = new ModelAndView(Constantes.JSP_INIT_PRODUCTO);
		ProductoForm productoForm = new ProductoForm();
		List<Producto> lstProducto = new ArrayList<Producto>();
		Producto producto = (Producto)request.getAttribute("productoBean");
		String tipoBusqueda = "";
		int cantidad = Integer.parseInt(properties.getProperty(Constantes.NUM_REGISTROS_PAGINA));
		
		try {
			
			productoForm.setTipoBusqueda(Constantes.BUSQUEDA_X_CODIGO);
			model.addObject("busqueda", "C");
			
			if(producto != null)
			{
				tipoBusqueda = (String)request.getAttribute("tipoBusqueda");
				if (StringUtils.equals(tipoBusqueda, Constantes.BUSQUEDA_X_NOMBRE))
				{
					productoForm.setTipoBusqueda(Constantes.BUSQUEDA_X_NOMBRE);
					productoForm.setNombre(StringUtils.defaultString(producto.getNombre(), ""));
				}			
			}else
			{
				model.addObject("total_paginas", "1");
			}
			
			lstProducto = productoService.getProducto(producto, tipoBusqueda, 0);
			
			logger.debug("Size: " + lstProducto.size());
			
			if (CollectionUtils.isEmpty(lstProducto))
			{
				model.addObject(Constantes.MENSAJE_GENERAL, "(!) No existe registros para esta consulta. ");
			}
			
			if (StringUtils.equals(tipoBusqueda, Constantes.BUSQUEDA_X_NOMBRE))
			{
				model.addObject("busqueda", "N");
				model.addObject("total_paginas", getTotalPaginas(lstProducto.size()));
				model.addObject("registros_prod", lstProducto.size());
				model.addObject("lstParametro", getPaginas(getTotalPaginas(lstProducto.size())));
				
			}
			
			if (lstProducto.size() > cantidad)
			{
				lstProducto = lstProducto.subList(0, cantidad);
			}
			
			model.addObject("pagina_actual", "1");
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
		logger.debug("=================Producto UPDATE===============");
		
		ModelAndView model = new ModelAndView(Constantes.JSP_UPDATE_PRODUCTO);
		String parametro = request.getParameter(Constantes.PARAMETER);
		String idProd = request.getParameter(Constantes.PARAMETER_ID);
		
		try {
			logger.debug("Parametro: " + parametro);
			logger.debug("IdProducto: " + idProd);
			
			model.addObject(UPDATE_PRODUCTO, parametro);
			model.addObject("lstPais", productoService.getPais());
			
			if(StringUtils.equals(parametro, Constantes.PARAMETER_NEW))
			{
				String codProducto = productoService.getCodigoProducto();
												
				model.addObject(Constantes.TITULO, TITULO_NEW_PRODUCTO);
				productoForm = new ProductoForm();
				productoForm.setCodigo(StringUtils.leftPad(codProducto, 5, "0"));
				productoForm.setStock(Constantes.NUMERIC_INT_DEFAULT);
				productoForm.setPrecioVenta(Constantes.NUMERIC_DECIMAL_DEFAULT);
				productoForm.setPrecioCompra(Constantes.NUMERIC_DECIMAL_DEFAULT);
				
				setListMarca(model);
				
			}else if(StringUtils.equals(parametro, Constantes.PARAMETER_EDIT))
			{
				model.addObject(Constantes.TITULO, TITULO_EDIT_PRODUCTO);
								
				productoForm = getProductoForm(productoService.getProducto(Integer.parseInt(idProd)));
				userSessionBean.put("foto_bd", productoForm.getFile());
				logger.debug("SaveFile: " + productoForm.getFile());
				setListMarca(model);
				
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
	
	public ModelAndView saveProducto(HttpServletRequest request, 
			HttpServletResponse response, ProductoForm productoForm) throws Exception
	{
		logger.debug("=================Producto SAVE===============");
		ModelAndView model = new ModelAndView(Constantes.JSP_UPDATE_PRODUCTO);
		String parametro = request.getParameter(Constantes.PARAMETER);
		
		/**Se carga la foto**/
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("foto");
		
		try {
			logger.info("Parametro: " + parametro);
			logger.info("File: " + file);
						
			if (StringUtils.isEmpty(file.getOriginalFilename()) && 
					StringUtils.equals(parametro, Constantes.PARAMETER_EDIT))
			{
				productoForm.setFile((byte[])userSessionBean.get("foto_bd"));
				logger.debug("SaveFile1: " + productoForm.getFile());
				userSessionBean.remove("foto_bd");
			}else
			{
				productoForm.setFile(file.getBytes());
			}
			
			logger.info("FileForm: " + productoForm.getFile());
			
			model.addObject("productoForm", productoForm);
			productoService.updateProducto(getProductoBean(productoForm, parametro));
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
			model.addObject(UPDATE_PRODUCTO, parametro);
			model.addObject(Constantes.PARAMETER, parametro);
			model.addObject("mensaje", "(X) Ocurrio un error al guardar el registro.");
			return model;
		}
						
		return iniciar(request, response);
	}
	
	public ModelAndView saveMarcaProducto(HttpServletRequest request, 
			HttpServletResponse response, ProductoForm productoForm) throws Exception
	{
		logger.debug("=================Marca SAVE ===============");
		ModelAndView model = new ModelAndView(Constantes.JSP_UPDATE_PRODUCTO);
		String parametro = request.getParameter(Constantes.PARAMETER);
						
		try {
			logger.info("Parametro: " + parametro);
			
			model.addObject(UPDATE_PRODUCTO, parametro);
			model.addObject("productoForm", productoForm);
			
			if(StringUtils.equals(parametro, Constantes.PARAMETER_NEW))
			{
				model.addObject(Constantes.TITULO, TITULO_NEW_PRODUCTO);
			}else
			{
				model.addObject(Constantes.TITULO, TITULO_EDIT_PRODUCTO);
			}
			
			marcaService.updateMarca(getMarcaBean(productoForm));
			
			setListMarca(model);
					
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
			model.addObject(Constantes.MENSAJE_GENERAL, "(X) Ocurrio un error al guardar el registro.");
			
		}
		
		return model;
	}
	
	public ModelAndView buscarProductoPag(HttpServletRequest request, 
			HttpServletResponse response, ProductoForm productoForm) throws Exception
	{
		logger.debug("=================Buscar Producto Paginacion===============");
		ModelAndView model = new ModelAndView(Constantes.JSP_INIT_PRODUCTO);
		List<Producto> lstProducto = new ArrayList<Producto>();
		String totalRegistros = request.getParameter("reg_total");
		String parametroABC = productoForm.getNombre().toLowerCase();
		String num_pag = request.getParameter("num_pag");
		int cantidad = Integer.parseInt(properties.getProperty(Constantes.NUM_REGISTROS_PAGINA));
		
		try {
			
			logger.info("Total Reg.: " + totalRegistros);
			logger.info("ParametroABC: " + parametroABC);
			logger.info("NumPagina: " + num_pag);
				
			int inicio = Integer.parseInt(num_pag);
			int fin = cantidad;
			
			if (!StringUtils.equals(num_pag, "1"))
			{
				fin = cantidad * inicio;
				inicio = (fin - cantidad) + 1;
			}
							
			logger.debug("---Varias Veces---");
			lstProducto = productoService.getProductoABC(parametroABC,inicio+"",fin+"");
			
			logger.debug("Lista1: " + lstProducto.size());
			
			
			if (CollectionUtils.isEmpty(lstProducto))
			{
				model.addObject("mensaje", "(!) No se encontro registros para este tipo de busqueda.");
			}
			
			userSessionBean.put("param_abc", parametroABC);

			model.addObject("busqueda", "N");
			model.addObject("total_paginas", getTotalPaginas(Integer.parseInt(totalRegistros)));
			model.addObject("lstParametro", getPaginas(getTotalPaginas(Integer.parseInt(totalRegistros))));
			model.addObject("lstProducto", lstProducto);	
			model.addObject("registros_prod", totalRegistros);
			model.addObject("pagina_actual", num_pag);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}
				
		model.addObject("productoForm", productoForm);
		
		return model;
	}
	
	private List<Parametro> getPaginas(int paginas)
	{
		List<Parametro> lstPaginas = new ArrayList<Parametro>();
		Parametro pag;
		
		for(int i=1; i<=paginas; i++)
		{
			pag = new Parametro();
			pag.setValor(i+"");
			lstPaginas.add(pag);
		}
		
		return lstPaginas;
	}
	
	private int getTotalPaginas(int numPaginas)
	{
		int cantidad = Integer.parseInt(properties.getProperty(Constantes.NUM_REGISTROS_PAGINA));
		int paginas = numPaginas/cantidad;
		if (numPaginas%cantidad!=0)
		{
			paginas = paginas + 1;
		}
		return paginas;
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
		form.setFile(bean.getFile());
		form.setIdMarca(bean.getMarca().getId()+"");
				
		return form;
	}
	
	private Producto getProductoBean(ProductoForm form, String parametro)
	{
		Producto producto = new Producto();
		logger.debug("CodProd: " + form.getCodigo());
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
		producto.getMarca().setId(Integer.parseInt(form.getIdMarca()));
		
		return producto;
	}
	
	private Marca getMarcaBean(ProductoForm form)
	{
		Marca marca = new Marca();
		
		marca.setId(0);
		marca.setNombre(form.getNombreMarca());
		marca.setProcedencia(form.getProcedencia());
		marca.setDescripcion(form.getDescripcionMarca());
		
		return marca;
	}
	
	private void setListMarca(ModelAndView model)
	{
		List<Marca> lista = marcaService.getMarca(null, null);
		logger.debug("Lista: " + lista.size());
		model.addObject("lstMarca", lista);
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

	/**
	 * @param marcaService the marcaService to set
	 */
	public void setMarcaService(MarcaProdService marcaService) {
		this.marcaService = marcaService;
	}
	
}
