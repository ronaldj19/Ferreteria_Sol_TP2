
package pe.com.sol.portal.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import pe.com.sol.constantes.Constantes;
import pe.com.sol.portal.session.UserSessionBean;
import pe.com.sol.portal.web.bean.Producto;
import pe.com.sol.portal.web.form.DocumentoForm;
import pe.com.sol.portal.web.form.ProductoForm;
import pe.com.sol.portal.web.service.ClienteService;
import pe.com.sol.portal.web.service.ParametroService;
import pe.com.sol.portal.web.service.ProductoService;
import pe.com.sol.utils.CommonUtils;

/**
 * @author Alonso Gutierrez
 * 
 */
public class TransaccionController extends MultiActionController{
	
	private static Logger logger = Logger.getLogger(TransaccionController.class);
	
	private static final String TIPO_DOCUMENTO = "tipo_documento";
	
	private static final String TITULO_DOCUMENTO_VENTA = "VENTA DE PRODUCTOS";
	
	private static final String TITULO_DOCUMENTO_COMPRA = "COMPRA DE PRODUCTOS";
	
	private static final String PARAMETRO_COMPRA = "COMPRA";
	
	private static final String PARAMETRO_VENTA = "VENTA";
	
	UserSessionBean userSessionBean;
	ClienteService clienteService;
	ProductoService productoService;
	ParametroService parametroService;
		
	public ModelAndView iniciar(HttpServletRequest request, 
			HttpServletResponse response) throws Exception
	{
		logger.debug("=================Documento Init===============");
		
		ModelAndView model = new ModelAndView(Constantes.JSP_INIT_DOCUMENTO_CV);
		DocumentoForm documentoForm = new DocumentoForm();
		String parametro = request.getParameter(Constantes.PARAMETER);
		String igv = "";
		String tipoCambio = "";
		
		try {
			
			if (StringUtils.equals(parametro, PARAMETRO_VENTA))
			{
				model.addObject(TIPO_DOCUMENTO, PARAMETRO_VENTA);
				model.addObject(Constantes.TITULO, TITULO_DOCUMENTO_VENTA);
								
			}else
			{
				model.addObject(TIPO_DOCUMENTO, PARAMETRO_COMPRA);
				model.addObject(Constantes.TITULO, TITULO_DOCUMENTO_COMPRA);
			}
			
			
			documentoForm.setTipoBusqueda("R");
			documentoForm.setFecha(CommonUtils.getToDay());
			
			igv = parametroService.getParametroValor(Constantes.PARAMETER_IGV);
			tipoCambio = parametroService.getParametroValor(Constantes.PARAMETER_TIPO_CAMBIO);
			
			logger.debug("IGV: " + igv);
			logger.debug("Tipo Cambio: " + tipoCambio);
			
			documentoForm.setParametroIgv(igv);
			documentoForm.setParametroTipoCambio(tipoCambio);
			
			model.addObject(Constantes.SHOW_DIV, Constantes.SHOW_DIV_NONE);
			userSessionBean.remove("DetalleDocumento");
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}
		
		model.addObject("documentoForm", documentoForm);
		
		return model;
	}
	

	
	public ModelAndView buscarProductoPopup(HttpServletRequest request, 
			HttpServletResponse response, DocumentoForm documentoForm) throws Exception
	{
		logger.debug("=================BuscarProductoPopup Init===============");
		ModelAndView model = new ModelAndView(Constantes.JSP_INIT_DOCUMENTO_CV);
		
		String parametro = request.getParameter(Constantes.PARAMETER);
				
		logger.debug("Parametro: " + parametro);
		logger.debug("Tipo: " + documentoForm.getTipoBusqueda());
		logger.debug("Nombre: " + documentoForm.getNombreProducto());
		logger.debug("Codigo: " + documentoForm.getCodigoProducto());
		
		List<Producto> lstProducto = new ArrayList<Producto>();
		
		try {
			model.addObject("popup", "POPUP2");
			
			Producto producto = new Producto();
			producto.setCodigo(documentoForm.getCodigoProducto()+"");
			producto.setNombre(documentoForm.getNombreProducto()+"");
			lstProducto = productoService.getProducto(producto, documentoForm.getTipoBusqueda(), 5);
			model.addObject(Constantes.TITULO, TITULO_DOCUMENTO_COMPRA);
			
			logger.debug("Lista: " + lstProducto.size());
			
			model.addObject("lstProducto1", lstProducto);
			
			model.addObject(Constantes.SHOW_DIV, Constantes.SHOW_DIV_BLOCK);
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(),e);
		}
		
		model.addObject(TIPO_DOCUMENTO, parametro);
		model.addObject("documentoForm", documentoForm);
						
		return model;
	}

	
	public boolean existsProducto(List<ProductoForm> lista, String id){
		
		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
			ProductoForm form = (ProductoForm) iterator.next();
			if(StringUtils.equals(form.getId(), id))
			{
				return true;
			}
			
		}
		
		return false;
	}
	
	
	
	/**
	 * @param userSessionBean the userSessionBean to set
	 */
	public void setUserSessionBean(UserSessionBean userSessionBean) {
		this.userSessionBean = userSessionBean;
	}
	
	/**
	 * @param clienteService the clienteService to set
	 */
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	
	/**
	 * @param productoService the productoService to set
	 */
	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	
	/**
	 * @param parametroService the parametroService to set
	 */
	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}
}
